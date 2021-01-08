package com.etho5.munchies.events;

import com.etho5.munchies.items.Items;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EatFoodListener implements Listener {

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        // if the item eaten is a chili pepper, give them poison for 3 seconds
        // the food "burns"
        if(e.getItem().equals(Items.CHILI_PEPPER.getItem())) {
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1, true));
        }
    }
}
