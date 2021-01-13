package com.etho5.munchies.events;

import com.etho5.munchies.OOP.Items;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.concurrent.ThreadLocalRandom;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        if(b.getType().equals(Material.GRASS)) {
            int x = ThreadLocalRandom.current().nextInt(3000);
            if(x == 1) {
                b.getWorld().dropItem(b.getLocation(), Items.TURMERIC.getItem());
            } else if(x == 2) {
                b.getWorld().dropItem(b.getLocation(), Items.CUMIN.getItem());
            } else if(x == 3) {
                b.getWorld().dropItem(b.getLocation(), Items.CILANTRO.getItem());
            }
        }

        else if(b.getType().equals(Material.TALL_GRASS)) {
            int x = ThreadLocalRandom.current().nextInt(3000);
            if(x == 1) {
                b.getWorld().dropItem(b.getLocation(), Items.CHILI_PEPPER.getItem());
            } else if(x == 2) {
                b.getWorld().dropItem(b.getLocation(), Items.TOMATO.getItem());
            } else if(x == 3) {
                b.getWorld().dropItem(b.getLocation(), Items.CORN.getItem());
            }
        }

        else if(b.getType().equals(Material.VINE)) {
            int x = ThreadLocalRandom.current().nextInt(3000);
            if(x == 1) {
                b.getWorld().dropItem(b.getLocation(), Items.BLUEBERRIES.getItem());
            } else if(x == 2) {
                b.getWorld().dropItem(b.getLocation(), Items.RASPBERRIES.getItem());
            } else if(x == 3) {
                b.getWorld().dropItem(b.getLocation(), Items.GRAPES.getItem());
            }
        }

        else if(b.getType().equals(Material.OAK_LEAVES) || b.getType().equals(Material.BIRCH_LEAVES) || b.getType().equals(Material.JUNGLE_LEAVES)
        || b.getType().equals(Material.ACACIA_LEAVES) || b.getType().equals(Material.DARK_OAK_LEAVES) || b.getType().equals(Material.SPRUCE_LEAVES)) {
            int x = ThreadLocalRandom.current().nextInt(3000);
            if (x == 1) {
                b.getWorld().dropItem(b.getLocation(), Items.ALMONDS.getItem());
            } else if (x == 2) {
                b.getWorld().dropItem(b.getLocation(), Items.PECANS.getItem());
            } else if(x == 3) {
                b.getWorld().dropItem(b.getLocation(), Items.LEMON.getItem());
            }
        }
    }
}
