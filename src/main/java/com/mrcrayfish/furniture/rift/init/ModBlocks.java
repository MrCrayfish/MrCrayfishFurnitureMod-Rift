package com.mrcrayfish.furniture.rift.init;

import com.mrcrayfish.furniture.rift.Constants;
import com.mrcrayfish.furniture.rift.block.BlockModernChair;
import com.mrcrayfish.furniture.rift.block.BlockModernCouch;
import com.mrcrayfish.furniture.rift.block.BlockModernTV;
import com.mrcrayfish.furniture.rift.block.BlockModernTable;
import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import org.dimdev.rift.listener.BlockAdder;
import org.dimdev.rift.listener.ItemAdder;

/**
 * Author: MrCrayfish
 */
public class ModBlocks implements BlockAdder, ItemAdder
{
    public static final Block MODERN_TV = new BlockModernTV();
    public static final Block MODERN_TABLE = new BlockModernTable();
    public static final Block MODERN_CHAIR = new BlockModernChair();
    public static final Block WHITE_MODERN_COUCH = new BlockModernCouch(EnumDyeColor.WHITE);
    public static final Block ORANGE_MODERN_COUCH = new BlockModernCouch(EnumDyeColor.ORANGE);
    public static final Block MAGENTA_MODERN_COUCH = new BlockModernCouch(EnumDyeColor.MAGENTA);
    public static final Block LIGHT_BLUE_MODERN_COUCH = new BlockModernCouch(EnumDyeColor.LIGHT_BLUE);
    public static final Block YELLOW_MODERN_COUCH = new BlockModernCouch(EnumDyeColor.YELLOW);
    public static final Block LIME_MODERN_COUCH = new BlockModernCouch(EnumDyeColor.LIME);
    public static final Block PINK_MODERN_COUCH = new BlockModernCouch(EnumDyeColor.PINK);
    public static final Block GRAY_MODERN_COUCH = new BlockModernCouch(EnumDyeColor.GRAY);
    public static final Block LIGHT_GRAY_MODERN_COUCH = new BlockModernCouch(EnumDyeColor.LIGHT_GRAY);
    public static final Block CYAN_MODERN_COUCH = new BlockModernCouch(EnumDyeColor.CYAN);
    public static final Block PURPLE_MODERN_COUCH = new BlockModernCouch(EnumDyeColor.PURPLE);
    public static final Block BLUE_MODERN_COUCH = new BlockModernCouch(EnumDyeColor.BLUE);
    public static final Block BROWN_MODERN_COUCH = new BlockModernCouch(EnumDyeColor.BROWN);
    public static final Block GREEN_MODERN_COUCH = new BlockModernCouch(EnumDyeColor.GREEN);
    public static final Block RED_MODERN_COUCH = new BlockModernCouch(EnumDyeColor.RED);
    public static final Block BLACK_MODERN_COUCH = new BlockModernCouch(EnumDyeColor.BLACK);

    @Override
    public void registerBlocks()
    {
        registerBlock("modern_tv", MODERN_TV);
        registerBlock("modern_table", MODERN_TABLE);
        registerBlock("modern_chair", MODERN_CHAIR);
        registerBlock("white_modern_couch", WHITE_MODERN_COUCH);
        registerBlock("orange_modern_couch", ORANGE_MODERN_COUCH);
        registerBlock("magenta_modern_couch", MAGENTA_MODERN_COUCH);
        registerBlock("light_blue_modern_couch", LIGHT_BLUE_MODERN_COUCH);
        registerBlock("yellow_modern_couch", YELLOW_MODERN_COUCH);
        registerBlock("lime_modern_couch", LIME_MODERN_COUCH);
        registerBlock("pink_modern_couch", PINK_MODERN_COUCH);
        registerBlock("gray_modern_couch", GRAY_MODERN_COUCH);
        registerBlock("light_gray_modern_couch", LIGHT_GRAY_MODERN_COUCH);
        registerBlock("cyan_modern_couch", CYAN_MODERN_COUCH);
        registerBlock("purple_modern_couch", PURPLE_MODERN_COUCH);
        registerBlock("blue_modern_couch", BLUE_MODERN_COUCH);
        registerBlock("brown_modern_couch", BROWN_MODERN_COUCH);
        registerBlock("green_modern_couch", GREEN_MODERN_COUCH);
        registerBlock("red_modern_couch", RED_MODERN_COUCH);
        registerBlock("black_modern_couch", BLACK_MODERN_COUCH);
    }

    @Override
    public void registerItems()
    {
        Item.register(MODERN_TV, ItemGroup.DECORATIONS);
        Item.register(MODERN_TABLE, ItemGroup.DECORATIONS);
        Item.register(MODERN_CHAIR, ItemGroup.DECORATIONS);
        Item.register(WHITE_MODERN_COUCH, ItemGroup.DECORATIONS);
        Item.register(ORANGE_MODERN_COUCH, ItemGroup.DECORATIONS);
        Item.register(MAGENTA_MODERN_COUCH, ItemGroup.DECORATIONS);
        Item.register(LIGHT_BLUE_MODERN_COUCH, ItemGroup.DECORATIONS);
        Item.register(YELLOW_MODERN_COUCH, ItemGroup.DECORATIONS);
        Item.register(LIME_MODERN_COUCH, ItemGroup.DECORATIONS);
        Item.register(PINK_MODERN_COUCH, ItemGroup.DECORATIONS);
        Item.register(GRAY_MODERN_COUCH, ItemGroup.DECORATIONS);
        Item.register(LIGHT_GRAY_MODERN_COUCH, ItemGroup.DECORATIONS);
        Item.register(CYAN_MODERN_COUCH, ItemGroup.DECORATIONS);
        Item.register(PURPLE_MODERN_COUCH, ItemGroup.DECORATIONS);
        Item.register(BLUE_MODERN_COUCH, ItemGroup.DECORATIONS);
        Item.register(BROWN_MODERN_COUCH, ItemGroup.DECORATIONS);
        Item.register(GREEN_MODERN_COUCH, ItemGroup.DECORATIONS);
        Item.register(RED_MODERN_COUCH, ItemGroup.DECORATIONS);
        Item.register(BLACK_MODERN_COUCH, ItemGroup.DECORATIONS);
    }

    private void registerBlock(String id, Block block)
    {
        Block.register(new ResourceLocation(Constants.MOD_ID, id), block);
    }
}
