package com.mrcrayfish.furniture.rift.mixin;

import com.mrcrayfish.furniture.rift.entity.EntitySeat;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketSpawnObject;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Author: MrCrayfish
 */
@Mixin(EntityTrackerEntry.class)
public class MixinEntityTrackerEntry
{
    @Shadow
    @Final
    private Entity trackedEntity;

    @Inject(method = "createSpawnPacket", at = @At("INVOKE"), cancellable = true)
    private void createSpawnPacket(CallbackInfoReturnable<Packet<?>> info)
    {
        if (this.trackedEntity instanceof EntitySeat)
        {
            info.setReturnValue(new SPacketSpawnObject(this.trackedEntity, 600));
        }
    }
}
