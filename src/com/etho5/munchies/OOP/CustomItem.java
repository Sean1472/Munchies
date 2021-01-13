package com.etho5.munchies.OOP;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItem extends ItemStack {

    /**
     * Creates a custom item from a Material.STICK with a display name and a custom model data value.
     * @param displayName the display name of the item
     * @param customModelData the custom model data of the item, used in custom textures
     */
    public CustomItem(String displayName, int customModelData) {
        super(Material.STICK, 1);
        ItemMeta meta = this.getItemMeta();
        assert meta != null;
        meta.setDisplayName(displayName);
        meta.setCustomModelData(customModelData);
        this.setItemMeta(meta);
    }

    /**
     * Creates a custom item from a stated Material with a display name and a custom model data value.
     * @param displayName the display name of the item
     * @param customModelData the custom model data of the item, used in custom textures
     * @param baseItem the base Material of the item
     */
    public CustomItem(String displayName, int customModelData, Material baseItem) {
        super(baseItem, 1);
        ItemMeta meta = this.getItemMeta();
        assert meta != null;
        meta.setDisplayName(displayName);
        meta.setCustomModelData(customModelData);
        this.setItemMeta(meta);
    }
}
