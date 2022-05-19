package net.gggbackpacks.core.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.gggbackpacks.common.blocks.blockEntities.LeatherBackpackBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;

public class BlockEntities {
    private static final LinkedHashMap<BlockEntityType<?>, String> BLOCK_ENTITY_TYPES = new LinkedHashMap<>();
    //List the BlockEntities for each kind of backpack here
    public static final BlockEntityType<LeatherBackpackBlockEntity> LEATHER_BACKPACK_BLOCK_ENTITY = add("leather_backpack_entity", FabricBlockEntityTypeBuilder.create(LeatherBackpackBlockEntity::new, Blocks.LEATHER_BACKPACK_BLOCK).build());

    public static void register() {
        BLOCK_ENTITY_TYPES.keySet().forEach(blockEntityType -> Registry.register(Registry.BLOCK_ENTITY_TYPE, "gggbackpacks:"+BLOCK_ENTITY_TYPES.get(blockEntityType), blockEntityType));
    }

    private static <T extends BlockEntity> BlockEntityType<T> add(String name, BlockEntityType<T> type) {
        BLOCK_ENTITY_TYPES.put(type, name);
        return type;
    }
}
