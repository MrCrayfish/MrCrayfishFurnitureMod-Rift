package com.mrcrayfish.furniture.rift.mixin;

import org.dimdev.riftloader.listener.InitializationListener;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

/**
 * Author: MrCrayfish
 */
public class MixinListener implements InitializationListener
{
    @Override
    public void onInitialization()
    {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.cfmrift.json");
    }
}
