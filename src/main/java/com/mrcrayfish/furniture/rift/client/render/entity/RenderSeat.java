package com.mrcrayfish.furniture.rift.client.render.entity;

import com.mrcrayfish.furniture.rift.entity.EntitySeat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

/**
 * Author: MrCrayfish
 */
public class RenderSeat extends Render<EntitySeat>
{
    public RenderSeat(RenderManager manager)
    {
        super(manager);
        this.shadowSize = 0;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySeat entitySeat)
    {
        return null;
    }
}
