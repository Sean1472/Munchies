package com.etho5.munchies.events;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EatFoodListener implements Listener {

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        // if the item eaten is a chili pepper, do damage for 3 seconds and add a "burn" sound effect each time player is damaged
        if(e.getItem().getItemMeta().getCustomModelData() == 67) {
            Player p = e.getPlayer();
            new BukkitRunnable() {
                public void run() {
                    p.damage(1);
                    p.playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 3.0F, 0.9F);
                }
            }.runTask(e.getPlayer().getServer().getPluginManager().getPlugin("Munchies"));
            new BukkitRunnable() {
                public void run() {
                    p.damage(1);
                    p.playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 3.0F, 0.9F);
                }
            }.runTaskLater(e.getPlayer().getServer().getPluginManager().getPlugin("Munchies"), 20);
            new BukkitRunnable() {
                public void run() {
                    p.damage(1);
                    p.playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 3.0F, 0.9F);
                }
            }.runTaskLater(e.getPlayer().getServer().getPluginManager().getPlugin("Munchies"), 40);
        }
    }
}
