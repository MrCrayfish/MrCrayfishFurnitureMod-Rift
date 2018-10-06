package com.mrcrayfish.furniture.rift;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

/**
 * Author: MrCrayfish
 */
public class ItemGroupHelper
{
    public static ItemGroup createItemGroup(String id, ItemStack icon)
    {
        int nextId = getNextFreeGroupIndex();
        if(nextId != -1)
        {
            return new ItemGroup(nextId, id)
            {
                @Override
                public ItemStack createIcon()
                {
                    return icon;
                }
            };
        }
        return null;
    }

    private static int getNextFreeGroupIndex()
    {
        for(int i = 0; i < ItemGroup.GROUPS.length; i++)
        {
            if(ItemGroup.GROUPS[i] == null)
            {
                return i;
            }
        }
        return -1;
    }
}
