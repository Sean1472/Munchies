package com.etho5.munchies.Runnable;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/*
 Created on 13/01/2021 at 01:18
 Author - Sean
*/
public class EatRunnable extends BukkitRunnable {

    private int seconds;
    private final Player player;

    public EatRunnable(Player player) {
        this.player = player;
    }

    public void run() {
        ++this.seconds;
        this.player.damage(1.0D);
        this.player.playSound(this.player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 3.0F, 0.9F);
        if (this.seconds >= 3) {
            this.seconds = 0;
            this.cancel();
        }

    }
}
