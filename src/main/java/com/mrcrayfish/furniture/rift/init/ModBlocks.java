package com.mrcrayfish.furniture.rift.init;

import com.mrcrayfish.furniture.rift.block.BlockModernChair;
import com.mrcrayfish.furniture.rift.block.BlockModernTable;
import net.minecraft.block.Block;

/**
 * Author: MrCrayfish
 */
public class ModBlocks
{
    public static final Block MODERN_TABLE = new BlockModernTable();
    public static final Block MODERN_CHAIR = new BlockModernChair();

    public static void init()
    {
        RegistrationHandler.add("modern_table", MODERN_TABLE, true);
        RegistrationHandler.add("modern_chair", MODERN_CHAIR, true);
    }
}
