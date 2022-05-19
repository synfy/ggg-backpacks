package net.gggbackpacks.core.registry;

import net.gggbackpacks.common.blocks.LeatherBackpackBlock;
import net.gggbackpacks.common.items.LeatherBackpackItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class Blocks {
    public static final LinkedHashMap<Block,String> LEATHER_BLOCKS = new LinkedHashMap<>();
    //put all the backpack blocks here to b registered
    public static final Block LEATHER_BACKPACK_BLOCK = addToLeather("leather_backpack", new LeatherBackpackBlock(DyeColor.BROWN));
    public static final Block WHITE_LEATHER_BACKPACK_BLOCK = addToLeather("white_leather_backpack", new LeatherBackpackBlock(DyeColor.WHITE));
    public static final Block ORANGE_BACKPACK_BLOCK = addToLeather("orange_leather_backpack", new LeatherBackpackBlock(DyeColor.ORANGE));
    public static final Block MAGENTA_LEATHER_BACKPACK_BLOCK = addToLeather("magenta_leather_backpack", new LeatherBackpackBlock(DyeColor.MAGENTA));
    public static final Block LIGHT_BLUE_LEATHER_BACKPACK_BLOCK = addToLeather("light_blue_leather_backpack", new LeatherBackpackBlock(DyeColor.LIGHT_BLUE));
    public static final Block YELLOW_LEATHER_BACKPACK_BLOCK = addToLeather("yellow_leather_backpack", new LeatherBackpackBlock(DyeColor.YELLOW));
    public static final Block LIME_LEATHER_BACKPACK_BLOCK = addToLeather("lime_leather_backpack", new LeatherBackpackBlock(DyeColor.LIME));
    public static final Block PINK_LEATHER_BACKPACK_BLOCK = addToLeather("pink_leather_backpack", new LeatherBackpackBlock(DyeColor.PINK));
    public static final Block GREY_LEATHER_BACKPACK_BLOCK = addToLeather("grey_leather_backpack", new LeatherBackpackBlock(DyeColor.GRAY));
    public static final Block LIGHT_GREY_LEATHER_BACKPACK_BLOCK = addToLeather("light_grey_leather_backpack", new LeatherBackpackBlock(DyeColor.LIGHT_GRAY));
    public static final Block CYAN_LEATHER_BACKPACK_BLOCK = addToLeather("cyan_leather_backpack", new LeatherBackpackBlock(DyeColor.CYAN));
    public static final Block PURPLE_LEATHER_BACKPACK_BLOCK = addToLeather("purple_leather_backpack", new LeatherBackpackBlock(DyeColor.PURPLE));
    public static final Block BLUE_LEATHER_BACKPACK_BLOCK = addToLeather("blue_leather_backpack", new LeatherBackpackBlock(DyeColor.BLUE));
    public static final Block GREEN_LEATHER_BACKPACK_BLOCK = addToLeather("green_leather_backpack", new LeatherBackpackBlock(DyeColor.GREEN));
    public static final Block RED_LEATHER_BACKPACK_BLOCK = addToLeather("red_leather_backpack", new LeatherBackpackBlock(DyeColor.RED));
    public static final Block BLACK_LEATHER_BACKPACK_BLOCK = addToLeather("black_leather_backpack", new LeatherBackpackBlock(DyeColor.BLACK));
    public static final Block DNF_LEATHER_BACKPACK_BLOCK = addToLeather("dnf_leather_backpack", new LeatherBackpackBlock(DyeColor.ORANGE));
     public static void register() {
         LEATHER_BLOCKS.keySet().forEach(block -> Registry.register(Registry.BLOCK, new Identifier("gggbackpacks",LEATHER_BLOCKS.get(block)), block));
         for(Block block : LEATHER_BLOCKS.keySet()){
            // Gonna want to make our own tab to avoid cluttering misc
             //Also, may need to change something here when items are created for each backpack
            Registry.register(Registry.ITEM, new Identifier("gggbackpacks",LEATHER_BLOCKS.get(block)),getItem((LeatherBackpackBlock) block));
         }
     }

     private static LeatherBackpackItem getItem(LeatherBackpackBlock block){
         return new LeatherBackpackItem(block);
     }

     private static <T extends Block> T addToLeather(String name, T block) {
         LEATHER_BLOCKS.put(block,name);
         return block;
     }
}
