package net.gggbackpacks.common.items;

import net.gggbackpacks.common.blocks.LeatherBackpackBlock;
import net.minecraft.item.*;

public class LeatherBackpackItem extends BlockItem{
    public LeatherBackpackItem(LeatherBackpackBlock block){
        super(block, new Settings().group(ItemGroup.MISC).maxCount(1));
    }
}
