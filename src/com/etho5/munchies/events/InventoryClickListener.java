package com.etho5.munchies.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(e.getView().getTitle().equals("Munchies")) {
            ItemStack item = e.getCurrentItem();
            if(e.getClick().isLeftClick()) {
                assert item != null;
                item.setAmount(1);
                e.getView().getPlayer().getInventory().addItem(item);
            } else if(e.getClick().isRightClick()) {
                assert item != null;
                item.setAmount(64);
                e.getView().getPlayer().getInventory().addItem(item);
            }
            e.setCancelled(true);
        }
    }
}
