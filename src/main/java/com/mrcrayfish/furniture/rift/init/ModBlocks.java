package com.mrcrayfish.furniture.rift.init;

import com.mrcrayfish.furniture.rift.block.BlockTest;
import net.minecraft.block.Block;

/**
 * Author: MrCrayfish
 */
public class ModBlocks
{
    public static final Block TEST_BLOCK = new BlockTest();

    public static void init()
    {
        RegistrationHandler.add("test", TEST_BLOCK, true);
    }
}
