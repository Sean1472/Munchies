package com.etho5.munchies.Listeners;

import com.etho5.munchies.AbstractMenu.AbstractMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

/*
 Created on 13/01/2021 at 01:36
 Author - Sean
*/
public class MenuHandler implements Listener {

    @EventHandler(ignoreCancelled = true)
    private void onMenuClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        InventoryHolder holder = e.getInventory().getHolder();
        if (holder instanceof AbstractMenu) {
            if (e.getClickedInventory() == p.getOpenInventory().getBottomInventory()) {
                e.setCancelled(true);
                return;
            }

            if (e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                e.setCancelled(true);
            }

            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }

            AbstractMenu menu = (AbstractMenu) holder;
            menu.handleMenu(e);
        }

    }
}
