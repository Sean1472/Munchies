package com.etho5.munchies.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class CraftItemListener implements Listener {

    //TODO get mortar and pestle AND knife to return to inventory after use
    // knife should come back with -1 damage compared to before, as if worn after use

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        for(int x = 0; x < e.getInventory().getMatrix().length; x++) {
            ItemStack item = e.getInventory().getMatrix()[x];
            if(item == null || item.getType() == Material.AIR) {
                continue;
            }
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.hasCustomModelData()) {
                if (meta.getCustomModelData() == 61) {
                    e.getWhoClicked().getInventory().addItem(item);
                    break;
                } else if (meta.getCustomModelData() == 62) {
                    Damageable dmg = (Damageable) meta;
                    dmg.setDamage(dmg.getDamage() - 1);
                    e.getWhoClicked().getInventory().addItem(item);
                    break;
                }
            }
        }
    }
}
