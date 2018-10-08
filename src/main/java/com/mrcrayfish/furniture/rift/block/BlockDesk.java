package com.mrcrayfish.furniture.rift.block;

import com.mrcrayfish.furniture.rift.utils.StateHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.Locale;

/**
 * Author: MrCrayfish
 */
public class BlockDesk extends BlockFurnitureWaterlogged implements IDesk
{
    public static final EnumProperty<DeskType> TYPE = EnumProperty.create("type", DeskType.class);

    public BlockDesk(EnumDyeColor color)
    {
        super(Block.Builder.create(Material.WOOD, color).hardnessAndResistance(1.0F, 1.0F));
        this.setDefaultState(this.getStateContainer().getBaseState().withProperty(HORIZONTAL_FACING, EnumFacing.NORTH).withProperty(TYPE, DeskType.NONE).withProperty(WATERLOGGED, false));
    }

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
        if(StateHelper.getBlock(world, pos, state.getValue(HORIZONTAL_FACING), StateHelper.Direction.DOWN) instanceof IDesk)
        {
            if(StateHelper.getRotation(world, pos, state.getValue(HORIZONTAL_FACING), StateHelper.Direction.DOWN) == StateHelper.Direction.RIGHT)
            {
                return state.withProperty(TYPE, DeskType.CORNER_RIGHT);
            }
            else if(StateHelper.getRotation(world, pos, state.getValue(HORIZONTAL_FACING), StateHelper.Direction.DOWN) == StateHelper.Direction.LEFT)
            {
                return state.withProperty(TYPE, DeskType.CORNER_LEFT);
            }
        }

        boolean left = false;
        boolean right = false;

        if(StateHelper.getBlock(world, pos, state.getValue(HORIZONTAL_FACING), StateHelper.Direction.LEFT) instanceof IDesk)
        {
            left = true;
        }
        if(StateHelper.getBlock(world, pos, state.getValue(HORIZONTAL_FACING), StateHelper.Direction.RIGHT) instanceof IDesk)
        {
            right = true;
        }
        if(left && !right)
        {
            return state.withProperty(TYPE, DeskType.LEFT);
        }
        else if(!left && right)
        {
            return state.withProperty(TYPE, DeskType.RIGHT);
        }
        else if(!left && !right)
        {
            return state.withProperty(TYPE, DeskType.NONE);
        }
        return state.withProperty(TYPE, DeskType.BOTH);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(TYPE);
    }

    public enum DeskType implements IStringSerializable
    {
        NONE,
        LEFT,
        RIGHT,
        BOTH,
        CORNER_LEFT,
        CORNER_RIGHT;

        @Override
        public String getName()
        {
            return this.toString().toLowerCase(Locale.US);
        }
    }
}
