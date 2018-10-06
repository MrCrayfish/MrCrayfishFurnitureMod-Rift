package com.mrcrayfish.furniture.rift.init;

import com.mrcrayfish.furniture.rift.Constants;
import com.mrcrayfish.furniture.rift.entity.EntitySeat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import org.dimdev.rift.listener.EntityTypeAdder;

/**
 * Author: MrCrayfish
 */
public class ModEntities implements EntityTypeAdder
{
    public static EntityType<EntitySeat> SEAT;

    @Override
    public void registerEntityTypes()
    {
        SEAT = register("seat", EntityType.Builder.create(EntitySeat.class, EntitySeat::new));
    }

    private <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder)
    {
        return EntityType.register(new ResourceLocation(Constants.MOD_ID, id).toString(), builder);
    }
}
