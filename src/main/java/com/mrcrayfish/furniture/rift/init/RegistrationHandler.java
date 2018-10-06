package com.mrcrayfish.furniture.rift.init;

import com.mrcrayfish.furniture.rift.Constants;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import org.dimdev.rift.listener.BlockAdder;
import org.dimdev.rift.listener.EntityTypeAdder;
import org.dimdev.rift.listener.ItemAdder;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: MrCrayfish
 */
public class RegistrationHandler implements BlockAdder, ItemAdder
{
    private static final Map<ResourceLocation, Block> BLOCKS = new HashMap<>();
    private static final Map<ResourceLocation, Item> ITEMS = new HashMap<>();

    @Override
    public void registerBlocks()
    {
        ModBlocks.init();
        BLOCKS.forEach(Block::register);
    }

    @Override
    public void registerItems()
    {
        ModItems.init();
        ITEMS.forEach(Item::register);
    }

    public static void add(String id, Block block, boolean withItem)
    {
        ResourceLocation resource = new ResourceLocation(Constants.MOD_ID, id);
        BLOCKS.put(resource, block);
        if(withItem)
        {
            ITEMS.put(resource, new ItemBlock(block, new Item.Builder()));
        }
    }

    public static void add(String id, Item item)
    {
        ITEMS.put(new ResourceLocation(Constants.MOD_ID, id), item);
    }
}
