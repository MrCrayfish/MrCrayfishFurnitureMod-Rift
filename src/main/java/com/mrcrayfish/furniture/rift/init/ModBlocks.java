package com.mrcrayfish.furniture.rift.init;

import com.mrcrayfish.furniture.rift.block.BlockModernTable;
import net.minecraft.block.Block;

/**
 * Author: MrCrayfish
 */
public class ModBlocks
{
    public static final Block MODERN_TABLE = new BlockModernTable();

    public static void init()
    {
        RegistrationHandler.add("modern_table", MODERN_TABLE, true);
    }
}
