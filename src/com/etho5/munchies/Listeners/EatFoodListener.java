package com.etho5.munchies.Listeners;

import com.etho5.munchies.Munchies;
import com.etho5.munchies.Runnable.EatRunnable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

/*
 Created on 13/01/2021 at 01:39
 Author - Sean
*/
public class EatFoodListener implements Listener {

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        if (!e.getItem().hasItemMeta()) return;
        if (e.getItem().getItemMeta().getCustomModelData() != 67) return;
        new EatRunnable(p).runTaskTimer(Munchies.getInstance(), 0L, 20L);
    }
}
