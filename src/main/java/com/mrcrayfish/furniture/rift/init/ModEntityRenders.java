package com.mrcrayfish.furniture.rift.init;

import com.mrcrayfish.furniture.rift.client.render.entity.RenderSeat;
import com.mrcrayfish.furniture.rift.entity.EntitySeat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.dimdev.rift.listener.client.EntityRendererAdder;

import java.util.Map;

/**
 * Author: MrCrayfish
 */
public class ModEntityRenders implements EntityRendererAdder
{
    @Override
    public void addEntityRenderers(Map<Class<? extends Entity>, Render<? extends Entity>> entityRenderMap, RenderManager manager)
    {
        entityRenderMap.put(EntitySeat.class, new RenderSeat(manager));
    }
}
