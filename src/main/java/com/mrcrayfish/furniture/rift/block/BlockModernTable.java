package com.mrcrayfish.furniture.rift.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * Author: MrCrayfish
 */
public class BlockModernTable extends BlockFourLegTable
{
    public BlockModernTable()
    {
        super(Block.Builder.create(Material.WOOD, MapColor.WHITE_STAINED_HARDENED_CLAY).hardnessAndResistance(1.0F, 1.0F));
        this.setDefaultState(this.getStateContainer().getBaseState().withProperty(FORWARD, false).withProperty(BACK, false).withProperty(LEFT, false).withProperty(RIGHT, false));
    }
}
