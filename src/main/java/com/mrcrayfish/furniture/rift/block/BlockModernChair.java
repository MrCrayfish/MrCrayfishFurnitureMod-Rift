package com.mrcrayfish.furniture.rift.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;

/**
 * Author: MrCrayfish
 */
public class BlockModernChair extends BlockFurniture
{
    public BlockModernChair()
    {
        super(Block.Builder.create(Material.WOOD, MapColor.WHITE_STAINED_HARDENED_CLAY).hardnessAndResistance(1.0F, 1.0F));
        this.setDefaultState(this.getStateContainer().getBaseState().withProperty(HORIZONTAL_FACING, EnumFacing.NORTH));
    }
}
