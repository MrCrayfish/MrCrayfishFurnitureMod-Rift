package com.mrcrayfish.furniture.rift.block;

import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Fluids;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

/**
 * Author: MrCrayfish
 */
public interface IDefaultLiquidContainer extends ILiquidContainer
{
    @Override
    default boolean canContainFluid(IBlockReader reader, BlockPos pos, IBlockState state, Fluid fluid)
    {
        return !state.getValue(BlockStateProperties.WATERLOGGED) && fluid == Fluids.WATER;
    }

    @Override
    default boolean receiveFluid(IWorld world, BlockPos pos, IBlockState state, IFluidState fluidState)
    {
        if(!state.getValue(BlockStateProperties.WATERLOGGED) && fluidState.getFluid() == Fluids.WATER)
        {
            if(!world.isRemote())
            {
                world.setBlockState(pos, state.withProperty(BlockStateProperties.WATERLOGGED, true), 3);
                world.getPendingFluidTicks().scheduleUpdate(pos, fluidState.getFluid(), fluidState.getFluid().getTickRate(world));
            }
            return true;
        }
        return false;
    }
}
