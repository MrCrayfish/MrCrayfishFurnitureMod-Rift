package com.mrcrayfish.furniture.rift.block;

import com.mrcrayfish.furniture.rift.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * Author: MrCrayfish
 */
public class BlockTest extends Block
{
    public BlockTest()
    {
        super(Builder.create(Material.WOOD, MapColor.WOOD));
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> list)
    {
        if(group == Constants.TEST_GROUP)
        {
            list.add(new ItemStack(this));
        }
    }
}
