package com.etho5.munchies.Listeners;

import com.etho5.munchies.OOP.Items;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

/*
 Created on 13/01/2021 at 01:37
 Author - Sean
*/
public class BlockBreakListener implements Listener {
    private final EnumSet<Material> materialsSet = EnumSet.of(Material.OAK_LEAVES, Material.BIRCH_LEAVES, Material.JUNGLE_LEAVES, Material.ACACIA_LEAVES, Material.DARK_OAK_LEAVES, Material.SPRUCE_LEAVES);

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        int x = ThreadLocalRandom.current().nextInt(100);
        if (b.getType() == Material.GRASS) {
            if (x == 1) {
                b.getWorld().dropItem(b.getLocation(), Items.TURMERIC.getItem());
            } else if (x == 2) {
                b.getWorld().dropItem(b.getLocation(), Items.CUMIN.getItem());
            } else if (x == 3) {
                b.getWorld().dropItem(b.getLocation(), Items.CILANTRO.getItem());
            }
        } else if (b.getType() == Material.TALL_GRASS) {
            if (x == 1) {
                b.getWorld().dropItem(b.getLocation(), Items.CHILI_PEPPER.getItem());
            } else if (x == 2) {
                b.getWorld().dropItem(b.getLocation(), Items.TOMATO.getItem());
            } else if (x == 3) {
                b.getWorld().dropItem(b.getLocation(), Items.CORN.getItem());
            }
        } else if (b.getType() == Material.VINE) {
            if (x == 1) {
                b.getWorld().dropItem(b.getLocation(), Items.BLUEBERRIES.getItem());
            } else if (x == 2) {
                b.getWorld().dropItem(b.getLocation(), Items.RASPBERRIES.getItem());
            } else if (x == 3) {
                b.getWorld().dropItem(b.getLocation(), Items.GRAPES.getItem());
            }
        } else if (this.materialsSet.contains(b.getType())) {
            if (x == 1) {
                b.getWorld().dropItem(b.getLocation(), Items.ALMONDS.getItem());
            } else if (x == 2) {
                b.getWorld().dropItem(b.getLocation(), Items.PECANS.getItem());
            } else if (x == 3) {
                b.getWorld().dropItem(b.getLocation(), Items.LEMON.getItem());
            }
        }
    }
}
