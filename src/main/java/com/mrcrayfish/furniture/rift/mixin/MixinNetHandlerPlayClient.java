package com.mrcrayfish.furniture.rift.mixin;

import com.mrcrayfish.furniture.rift.entity.EntitySeat;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.EntityTracker;
import net.minecraft.network.play.server.SPacketSpawnObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Based on https://github.com/Modding-Legacy/Aether-Legacy-Rift/blob/master/src/main/java/com/legacy/aether/mixin/client/MixinNetHandlerPlayClient.java
 * Author: MrCrayfish
 */
@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient
{
    @Shadow
    private WorldClient world;

    @Inject(method = "handleSpawnObject", at = @At("RETURN"))
    public void handleSpawnObject(SPacketSpawnObject packetIn, CallbackInfo ci)
    {
        double posX = packetIn.getX();
        double posY = packetIn.getY();
        double posZ = packetIn.getZ();

        if(packetIn.getType() == 600)
        {
            EntitySeat seat = new EntitySeat(world);
            seat.setPosition(posX, posY, posZ);
            seat.setEntityId(packetIn.getEntityID());
            seat.setUniqueId(packetIn.getUniqueId());
            EntityTracker.updateServerPosition(seat, posX, posY, posZ);
            this.world.addEntityToWorld(packetIn.getEntityID(), seat);
        }
    }
}
