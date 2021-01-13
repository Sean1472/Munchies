package com.etho5.munchies.Utils;

import com.etho5.munchies.OOP.Items;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

/*
 Created on 13/01/2021 at 01:19
 Author - Sean
*/
public class Utils {

    public Utils() {
        throw new AssertionError();
    }

    public static String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static List<String> color(List<String> input) {
        return input.isEmpty() ? input : input.stream().map(Utils::color).collect(Collectors.toList());
    }

    public static boolean hasPermission(CommandSender sender, String permission) {
        if (!sender.hasPermission("munchies." + permission) && !sender.hasPermission("munchies.admin")) {
            sender.sendMessage(String.format(color("&c&l(!) &fYou do not have permission: &amunchies.%s"), permission));
            return false;
        } else {
            return true;
        }
    }

    public static ItemStack createGUIItem(Items items) {
        return new ItemBuilder(items.getItem().getType()).setName("&a" + items.getItem().getItemMeta().getDisplayName()).setLore("&fLeft-click for &e1", "&fRight-click for &e64").addPersistentString("Items", items.name()).build();
    }
}
