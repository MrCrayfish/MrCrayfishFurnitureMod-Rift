package com.mrcrayfish.furniture.rift.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.rift.entity.EntitySeat;
import com.mrcrayfish.furniture.rift.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Fluids;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ShapeUtils;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BlockModernChair extends BlockFurnitureWaterlogged
{
    public final ImmutableMap<IBlockState, VoxelShape> SHAPES;

    public BlockModernChair()
    {
        super(Block.Builder.create(Material.WOOD, EnumDyeColor.WHITE).hardnessAndResistance(1.0F, 1.0F));
        this.setDefaultState(this.getStateContainer().getBaseState().withProperty(HORIZONTAL_FACING, EnumFacing.NORTH).withProperty(WATERLOGGED, false));
        SHAPES = this.generateShapes(this.getStateContainer().getValidStates());
    }

    private ImmutableMap<IBlockState, VoxelShape> generateShapes(ImmutableList<IBlockState> states)
    {
        final VoxelShape[] CHAIR_SEAT = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2.0, 6.5, 2.0, 13.0, 9.5, 14.0));
        final VoxelShape[] CHAIR_BACK_REST = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(11.5, 9.5, 2.0, 13.0, 19.5, 14.0));
        final VoxelShape[] LEG_FRONT_LEFT_SHAPE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2.0, 0.0, 2.0, 3.5, 6.5, 3.5));
        final VoxelShape[] LEG_FRONT_RIGHT_SHAPE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(2.0, 0.0, 12.5, 3.5, 6.5, 14));
        final VoxelShape[] LEG_BOTTOM_LEFT_SHAPE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(3.5, 0.0, 2.0, 12.5, 1.5, 3.5));
        final VoxelShape[] LEG_BOTTOM_RIGHT_SHAPE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(3.5, 0.0, 12.5, 12.5, 1.5, 14.0));
        final VoxelShape[] LEG_BOTTOM_BACK_SHAPE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(12.5, 0.0, 2.0, 14.0, 1.5, 14.0));

        ImmutableMap.Builder<IBlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(IBlockState state : states)
        {
            EnumFacing facing = state.getValue(HORIZONTAL_FACING);

            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(CHAIR_SEAT[facing.getHorizontalIndex()]);
            shapes.add(CHAIR_BACK_REST[facing.getHorizontalIndex()]);
            shapes.add(LEG_FRONT_LEFT_SHAPE[facing.getHorizontalIndex()]);
            shapes.add(LEG_FRONT_RIGHT_SHAPE[facing.getHorizontalIndex()]);
            shapes.add(LEG_BOTTOM_LEFT_SHAPE[facing.getHorizontalIndex()]);
            shapes.add(LEG_BOTTOM_RIGHT_SHAPE[facing.getHorizontalIndex()]);
            shapes.add(LEG_BOTTOM_BACK_SHAPE[facing.getHorizontalIndex()]);
            builder.put(state, VoxelShapeHelper.combineAll(shapes));
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

    @Override
    public boolean onBlockActivated(IBlockState state, World world, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        return EntitySeat.create(world, pos, 5.5 * 0.0625, player);
    }

    /*
    @Override
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        //TODO add comparator output for sitting state
        //return SittableUtil.isSomeoneSitting(worldIn, pos.getX(), pos.getY(), pos.getZ()) ? 1 : 0;
        return 0;
    }*/
}
