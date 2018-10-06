package com.mrcrayfish.furniture.rift.mixin;

import com.mrcrayfish.furniture.rift.entity.EntitySeat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Author: MrCrayfish
 */
@Mixin(EntityTracker.class)
public class MixinEntityTracker
{
    @Inject(method = "track", at = @At("RETURN"))
    public void trackEntity(Entity entityIn, CallbackInfo ci)
    {
        if (entityIn instanceof EntitySeat)
        {
            ((EntityTracker) (Object) this).track(entityIn, 120, 2147483647, true);
        }
    }
}
