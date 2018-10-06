package com.mrcrayfish.furniture.rift;

import org.dimdev.rift.listener.MinecraftStartListener;

/**
 * Author: MrCrayfish
 */
public class MrCrayfishFurnitureMod implements MinecraftStartListener
{
    @Override
    public void onMinecraftStart()
    {
        System.out.println("Starting");
    }
}
