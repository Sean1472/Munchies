package com.etho5.munchies.Commands;

import com.etho5.munchies.Menus.MainMenu;
import com.etho5.munchies.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 Created on 13/01/2021 at 01:32
 Author - Sean
*/
public class MMain implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (!this.hasPermission(sender, "help")) {
                return false;
            }
            sender.sendMessage("Help message: TODO");
        } else if (args[0].equalsIgnoreCase("gui")) {
            if (!(sender instanceof Player)) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Console cannot use this command!");
                return false;
            }

            if (!this.hasPermission(sender, "gui")) {
                return false;
            }

            Player p = (Player) sender;
            new MainMenu(p).open();
        } else {
            sender.sendMessage(ChatColor.GREEN + "[Munchies] " + ChatColor.YELLOW + "by Etho5");
        }

        return true;
    }

    private boolean hasPermission(CommandSender sender, String permission) {
        return Utils.hasPermission(sender, permission);
    }
}
