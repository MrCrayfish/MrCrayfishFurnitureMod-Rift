package com.mrcrayfish.furniture.rift.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public abstract class BlockFurnitureWaterlogged extends BlockFurniture implements IDefaultBucketPickupHandler, IDefaultLiquidContainer
{
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public BlockFurnitureWaterlogged(Builder builder)
    {
        super(builder);
    }

    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context)
    {
        IFluidState fluidState = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().withProperty(HORIZONTAL_FACING, context.getPlacementHorizontalFacing()).withProperty(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public IFluidState getFluidState(IBlockState state)
    {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(WATERLOGGED);
    }
}
