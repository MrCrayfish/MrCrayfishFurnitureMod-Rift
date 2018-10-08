package com.mrcrayfish.furniture.rift.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mrcrayfish.furniture.rift.entity.EntitySeat;
import com.mrcrayfish.furniture.rift.utils.StateHelper;
import com.mrcrayfish.furniture.rift.utils.VoxelShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BlockModernCouch extends BlockFurnitureWaterlogged
{
    public static final EnumProperty<CouchType> TYPE = EnumProperty.create("type", CouchType.class);

    public final ImmutableMap<IBlockState, VoxelShape> SHAPES;

    public BlockModernCouch(EnumDyeColor color)
    {
        super(Block.Builder.create(Material.WOOD, color).hardnessAndResistance(0.5F, 1.0F));
        this.setDefaultState(this.getStateContainer().getBaseState().withProperty(HORIZONTAL_FACING, EnumFacing.NORTH).withProperty(TYPE, CouchType.BOTH).withProperty(WATERLOGGED, false));
        SHAPES = this.generateShapes(this.getStateContainer().getValidStates());
    }

    private ImmutableMap<IBlockState, VoxelShape> generateShapes(ImmutableList<IBlockState> states)
    {
        final VoxelShape[] COUCH_BASE_SHAPE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1.0, 3.0, 0.0, 15.0, 9.0, 16.0));
        final VoxelShape[] COUCH_ARMREST_LEFT_SHAPE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1.0, 0.0, 0.0, 15.0, 13.0, 2.0));
        final VoxelShape[] COUCH_ARMREST_RIGHT_SHAPE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(1.0, 0.0, 14.0, 15.0, 13.0, 16.0));
        final VoxelShape[] COUCH_BACKREST_BASE_SHAPE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(11.0, 9.0, 2.0, 15.0, 19.0, 14.0));
        final VoxelShape[] COUCH_BACKREST_LEFT_SHAPE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(11.0, 9.0, 0.0, 15.0, 19.0, 2.0));
        final VoxelShape[] COUCH_BACKREST_RIGHT_SHAPE = VoxelShapeHelper.getRotatedVoxelShapes(Block.makeCuboidShape(11.0, 9.0, 14.0, 15.0, 19.0, 16.0));

        ImmutableMap.Builder<IBlockState, VoxelShape> builder = new ImmutableMap.Builder<>();
        for(IBlockState state : states)
        {
            EnumFacing facing = state.getValue(HORIZONTAL_FACING);
            CouchType type = state.getValue(TYPE);

            List<VoxelShape> shapes = new ArrayList<>();
            shapes.add(COUCH_BASE_SHAPE[facing.getHorizontalIndex()]);
            shapes.add(COUCH_BACKREST_BASE_SHAPE[facing.getHorizontalIndex()]);
            switch(type)
            {
                case NONE:
                    shapes.add(COUCH_ARMREST_LEFT_SHAPE[facing.getHorizontalIndex()]);
                    shapes.add(COUCH_ARMREST_RIGHT_SHAPE[facing.getHorizontalIndex()]);
                    break;
                case LEFT:
                    shapes.add(COUCH_BACKREST_RIGHT_SHAPE[facing.getHorizontalIndex()]);
                    shapes.add(COUCH_ARMREST_LEFT_SHAPE[facing.getHorizontalIndex()]);
                    break;
                case RIGHT:
                    shapes.add(COUCH_BACKREST_LEFT_SHAPE[facing.getHorizontalIndex()]);
                    shapes.add(COUCH_ARMREST_RIGHT_SHAPE[facing.getHorizontalIndex()]);
                    break;
                case BOTH:
                    shapes.add(COUCH_BACKREST_RIGHT_SHAPE[facing.getHorizontalIndex()]);
                    shapes.add(COUCH_BACKREST_LEFT_SHAPE[facing.getHorizontalIndex()]);
                    break;
            }
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

    @Nullable
    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context)
    {
        IBlockState state = super.getStateForPlacement(context);
        return this.getActualState(context.getWorld(), context.getPos(), state);
    }

    @Override
    public IBlockState updatePostPlacement(IBlockState state, EnumFacing facing, IBlockState neighbourState, IWorld world, BlockPos pos, BlockPos neighbourPos)
    {
        return this.getActualState(world, pos, state);
    }

    private IBlockState getActualState(IWorld world, BlockPos pos, IBlockState state)
    {
        boolean left = false;
        boolean right = false;

        if(StateHelper.getBlock(world, pos, state.getValue(HORIZONTAL_FACING), StateHelper.Direction.LEFT) instanceof BlockModernCouch)
        {
            if(StateHelper.getRotation(world, pos, state.getValue(HORIZONTAL_FACING), StateHelper.Direction.LEFT) == StateHelper.Direction.DOWN)
            {
                left = true;
            }
        }
        if(StateHelper.getBlock(world, pos, state.getValue(HORIZONTAL_FACING), StateHelper.Direction.RIGHT) instanceof BlockModernCouch)
        {
            if(StateHelper.getRotation(world, pos, state.getValue(HORIZONTAL_FACING), StateHelper.Direction.RIGHT) == StateHelper.Direction.DOWN)
            {
                right = true;
            }
        }
        if(left && right)
        {
            return state.withProperty(TYPE, CouchType.BOTH);
        }
        else if(!left && !right)
        {
            return state.withProperty(TYPE, CouchType.NONE);
        }
        else if(left)
        {
            return state.withProperty(TYPE, CouchType.LEFT);
        }
        else
        {
            return state.withProperty(TYPE, CouchType.RIGHT);
        }
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World world, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        return EntitySeat.create(world, pos, 5 * 0.0625, player);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(TYPE);
    }

    public enum CouchType implements IStringSerializable
    {
        NONE,
        LEFT,
        RIGHT,
        BOTH;

        @Override
        public String getName()
        {
            return this.toString().toLowerCase(Locale.US);
        }
    }
}
