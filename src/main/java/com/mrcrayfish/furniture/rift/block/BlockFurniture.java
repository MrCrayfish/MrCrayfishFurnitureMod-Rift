package com.mrcrayfish.furniture.rift.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockFurniture extends BlockHorizontal
{
    public BlockFurniture(Block.Builder builder)
    {
        super(builder);
        this.setDefaultState(this.getStateContainer().getBaseState().withProperty(HORIZONTAL_FACING, EnumFacing.NORTH));
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Nullable
    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.getDefaultState().withProperty(HORIZONTAL_FACING, context.getPlacementHorizontalFacing());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
    {
        builder.add(HORIZONTAL_FACING);
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockReader p_getBlockFaceShape_1_, IBlockState p_getBlockFaceShape_2_, BlockPos p_getBlockFaceShape_3_, EnumFacing p_getBlockFaceShape_4_)
    {
        return BlockFaceShape.UNDEFINED;
    }
}
