package com.mrcrayfish.furniture.rift.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockFurnitureTile extends BlockFurniture implements ITileEntityProvider
{
    public BlockFurnitureTile(Block.Builder builder)
    {
        super(builder);
    }

    @Override
    public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos)
    {
        TileEntity tileEntity = world.getTileEntity(pos);
        return Container.calcRedstone(tileEntity);
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    @Override
    public void onReplaced(IBlockState oldState, World world, BlockPos pos, IBlockState newState, boolean unknownBool)
    {
        if(oldState.getBlock() != newState.getBlock())
        {
            TileEntity tileEntity = world.getTileEntity(pos);
            if(tileEntity instanceof IInventory)
            {
                IInventory inv = (IInventory) tileEntity;
                InventoryHelper.dropInventoryItems(world, pos, inv);
            }
        }
    }

    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity != null && tileentity.receiveClientEvent(id, param);
    }
}
