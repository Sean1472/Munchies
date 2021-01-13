package com.etho5.munchies.Listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

/*
 Created on 13/01/2021 at 01:39
 Author - Sean
*/
public class CraftItemListener implements Listener {
    @EventHandler
    public void onCraft(CraftItemEvent e) {
        for (int x = 0; x < e.getInventory().getMatrix().length; ++x) {
            ItemStack item = e.getInventory().getMatrix()[x];
            if (item != null && item.getType() != Material.AIR) {
                ItemMeta meta = item.getItemMeta();
                if (meta != null && meta.hasCustomModelData()) {
                    if (meta.getCustomModelData() == 61) {
                        e.getWhoClicked().getInventory().addItem(item);
                        break;
                    }

                    if (meta.getCustomModelData() == 62) {
                        Damageable dmg = (Damageable) meta;
                        dmg.setDamage(dmg.getDamage() - 1);
                        e.getWhoClicked().getInventory().addItem(item);
                        break;
                    }
                }
            }
        }

    }
}