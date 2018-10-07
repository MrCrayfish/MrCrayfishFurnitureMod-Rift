package com.mrcrayfish.furniture.rift.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.rift.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

/**
 * Author: MrCrayfish
 */
public class BlockModernTV extends BlockFurniture
{
    public static final BooleanProperty MOUNTED = BooleanProperty.create("mounted");

    public final ImmutableMap<IBlockState, VoxelShape> SHAPES;

    public BlockModernTV()
    {
        super(Block.Builder.create(Material.IRON, EnumDyeColor.GRAY));
        this.setDefaultState(this.getStateContainer().getBaseState().withProperty(HORIZONTAL_FACING, EnumFacing.NORTH).withProperty(MOUNTED, false));
        SHAPES = this.generateShapes(this.getStateContainer().getValidStates());
    }

    private ImmutableMap<IBlockState, VoxelShape> generateShapes(ImmutableList<IBlockState> states)
    {
        final VoxelShape[] NORMAL_SHAPE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(7.0, 0.0, -4.0, 10.0, 17.0, 20.0));
        final VoxelShape[] MOUNTED_SHAPE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(13.0, 3.0, -4.0, 15.0, 17.0, 20.0));

        ImmutableMap.Builder<IBlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(IBlockState state : states)
        {
            EnumFacing facing = state.getValue(HORIZONTAL_FACING);
            boolean mounted = state.getValue(MOUNTED);
            VoxelShape shape = mounted ? MOUNTED_SHAPE[facing.getHorizontalIndex()] : NORMAL_SHAPE[facing.getHorizontalIndex()];
            builder.put(state, shape);
        }
        return builder.build();
    }

    @Override
    public VoxelShape getShape(IBlockState state, IBlockReader reader, BlockPos pos)
    {
        return SHAPES.get(state);
    }

    @Override
    public VoxelShape getCollisionShape(IBlockState state, IBlockReader reader, BlockPos pos)
    {
        return SHAPES.get(state);
    }

    @Nullable
    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context)
    {
        IBlockState state = super.getStateForPlacement(context);
        if(context.getFace().getHorizontalIndex() != -1)
        {
            state = state.withProperty(MOUNTED, true);
        }
        return state;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(MOUNTED);
    }
}
