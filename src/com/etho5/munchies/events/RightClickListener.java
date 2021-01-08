package com.etho5.munchies.events;

import com.etho5.munchies.items.Items;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class RightClickListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack a = p.getInventory().getItemInMainHand();
        ItemStack b = p.getInventory().getItemInOffHand();
        // checks to see if the player right-clicked
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            // checks to see if the player is hungry
            if (p.getFoodLevel() < 20) {

                // checks to see if the item in either hand is Colenia Cola, the sole drinkable "food"
                // if so, replaces one cola with a can, then gives 2 hunger bars and plays a drink sound
                if(a.equals(Items.COLENIA_COLA.getItem())) {
                    if(a.getAmount() <= 1) {
                        p.getInventory().setItemInMainHand(Items.CAN.getItem());
                    } else {
                        a.setAmount(a.getAmount() - 1);
                        p.getInventory().setItemInOffHand(a);
                        p.getInventory().addItem(Items.CAN.getItem());
                    }
                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 3.0F, 1.0F);
                    p.setFoodLevel(p.getFoodLevel() + 4);
                } else if(b.equals(Items.COLENIA_COLA.getItem())) {
                    if(b.getAmount() <= 1) {
                        p.getInventory().setItemInOffHand(Items.CAN.getItem());
                    } else {
                        b.setAmount(b.getAmount() - 1);
                        p.getInventory().setItemInOffHand(b);
                        p.getInventory().addItem(Items.CAN.getItem());
                    }
                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 3.0F, 1.0F);
                    p.setFoodLevel(p.getFoodLevel() + 4);
                }
                // checks to see if the item in either hand is trail mix, a faster version of golden carrots
                // if so, removes one trail mix, then gives the player and extra 3 hunger bars and extra saturation while playing an eat sound
                else if(a.equals(Items.TRAIL_MIX.getItem())) {
                    if(a.getAmount() <= 1) {
                        p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                    } else {
                        a.setAmount(a.getAmount() - 1);
                        p.getInventory().setItemInOffHand(a);
                        p.getInventory().addItem(Items.CAN.getItem());
                    }
                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 3.0F, 1.0F);
                    p.setFoodLevel(p.getFoodLevel() + 4);
                    p.setSaturation(14.4F);
                } else if(b.equals(Items.TRAIL_MIX.getItem())) {
                    if(b.getAmount() <= 1) {
                        p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                    } else {
                        b.setAmount(b.getAmount() - 1);
                        p.getInventory().setItemInOffHand(b);
                        p.getInventory().addItem(Items.CAN.getItem());
                    }
                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 3.0F, 1.0F);
                    p.setFoodLevel(p.getFoodLevel() + 4);
                    p.setSaturation(14.4F);
                }
            }
        }
    }
}
