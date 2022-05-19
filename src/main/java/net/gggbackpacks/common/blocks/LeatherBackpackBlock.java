package net.gggbackpacks.common.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.gggbackpacks.common.blocks.blockEntities.LeatherBackpackBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Wearable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.apache.commons.lang3.ObjectUtils;

import javax.annotation.Nullable;

public class LeatherBackpackBlock extends BlockWithEntity implements Waterloggable {

    private final DyeColor color;

    public LeatherBackpackBlock(DyeColor color) {
        super(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL).strength(0.4f).nonOpaque());
        this.color = color;
        setDefaultState(this.stateManager.getDefaultState()
            .with(Properties.WATERLOGGED, false)
                .with(Properties.HORIZONTAL_FACING,Direction.NORTH)
        );
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack){
        if(world.getBlockEntity(pos) instanceof LeatherBackpackBlockEntity leatherBackpack){
            Inventories.readNbt(stack.getOrCreateNbt(), leatherBackpack.getItems());
        }
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient()) {
            if(player.isSneaking()){
                if(world.getBlockEntity(pos) instanceof LeatherBackpackBlockEntity leatherBlockEntity){
                    ItemStack stack = new ItemStack(this);
                    NbtCompound tag = stack.getOrCreateNbt();

                    Inventories.writeNbt(tag, leatherBlockEntity.getItems());
                    leatherBlockEntity.wasPickedUp = true;

                    player.giveItemStack(stack);
                    world.removeBlock(pos,true);
                }
            }
            else {
                NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
                if (screenHandlerFactory != null) {
                    world.playSound(null, pos, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.BLOCKS, 1F,1F);
                    player.openHandledScreen(screenHandlerFactory);
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context){
        if(state.get(HorizontalFacingBlock.FACING) == Direction.NORTH) return northShape();
        else if(state.get(HorizontalFacingBlock.FACING) == Direction.EAST) return eastShape();
        else if(state.get(HorizontalFacingBlock.FACING) == Direction.SOUTH) return southShape();
        else return westShape();
    }

    public VoxelShape northShape(){
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.25, 0.875, 0.5625, 0.5625));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.15625, 0.875, 0.4375, 0.25));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.5625, 0.25, 0.875, 0.75, 0.5625));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.15625, 0, 0.5625, 0.21875, 0.625, 0.8125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.78125, 0, 0.5625, 0.84375, 0.625, 0.8125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0.5, 0.1875, 0.5625, 0.65625, 0.25));
        return shape;
    }

    public VoxelShape westShape(){
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.125, 0.5625, 0.5625, 0.875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.15625, 0, 0.125, 0.25, 0.4375, 0.875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.5625, 0.125, 0.5625, 0.75, 0.875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5625, 0, 0.78125, 0.8125, 0.625, 0.84375));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5625, 0, 0.15625, 0.8125, 0.625, 0.21875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.5, 0.4375, 0.25, 0.65625, 0.5625));
        return shape;
    }

    public VoxelShape southShape(){
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.4375, 0.875, 0.5625, 0.75));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.75, 0.875, 0.4375, 0.84375));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.5625, 0.4375, 0.875, 0.75, 0.75));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.78125, 0, 0.1875, 0.84375, 0.625, 0.4375));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.15625, 0, 0.1875, 0.21875, 0.625, 0.4375));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0.5, 0.75, 0.5625, 0.65625, 0.8125));
        return shape;
    }

    public VoxelShape eastShape(){
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0, 0.125, 0.75, 0.5625, 0.875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.75, 0, 0.125, 0.84375, 0.4375, 0.875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0.5625, 0.125, 0.75, 0.75, 0.875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.15625, 0.4375, 0.625, 0.21875));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.78125, 0.4375, 0.625, 0.84375));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.75, 0.5, 0.4375, 0.8125, 0.65625, 0.5625));
        return shape;
    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof LeatherBackpackBlockEntity leatherBlockEntity && !leatherBlockEntity.wasPickedUp) {
                ItemScatterer.spawn(world, pos, (LeatherBackpackBlockEntity)blockEntity);
                // update comparators ig lmao
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState()
                .with(Properties.WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER)
                .with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(Properties.WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.WATERLOGGED).add(Properties.HORIZONTAL_FACING);
    }

    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput((Inventory)world.getBlockEntity(pos));
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state){
        return new LeatherBackpackBlockEntity(pos, state);
    }

    public DyeColor getColor(){
        return this.color;
    }
}
