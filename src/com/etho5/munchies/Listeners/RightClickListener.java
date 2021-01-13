package com.etho5.munchies.Listeners;

import com.etho5.munchies.OOP.Items;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/*
 Created on 13/01/2021 at 01:37
 Author - Sean
*/
public class RightClickListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (p.getInventory().getItemInMainHand().getType() != Material.AIR) return;
        final ItemStack main = p.getInventory().getItemInMainHand();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getFoodLevel() < 20) {
            if (main.getItemMeta().getCustomModelData() == 110) {
                main.setAmount(main.getAmount() - 1);
                if (main.getAmount() < 1) {
                    p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                } else {
                    p.getInventory().setItemInMainHand(main);
                }

                p.setFoodLevel(p.getFoodLevel() + 6);
                p.setSaturation(p.getSaturation() + 14.4F);
                p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 3.0F, 0.75F);
            } else if (main.getItemMeta().getCustomModelData() == 105) {
                main.setAmount(main.getAmount() - 1);
                if (main.getAmount() < 1) {
                    p.getInventory().setItemInMainHand(Items.CAN.getItem());
                } else {
                    p.getInventory().setItemInMainHand(main);
                    p.getInventory().addItem(Items.CAN.getItem());
                }

                p.setFoodLevel(p.getFoodLevel() + 4);
                p.setSaturation(p.getSaturation() + 4.5F);
                p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 3.0F, 0.5F);
            } else if (main.getItemMeta().getCustomModelData() == 78) {
                main.setAmount(main.getAmount() - 1);
                if (main.getAmount() < 1) {
                    p.getInventory().setItemInMainHand(Items.CAN.getItem());
                } else {
                    p.getInventory().setItemInMainHand(main);
                    p.getInventory().addItem(Items.CAN.getItem());
                }

                p.setFoodLevel(p.getFoodLevel() + 5);
                p.setSaturation(p.getSaturation() + 6.5F);
                p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 3.0F, 0.75F);
            }
        }
    }
}
