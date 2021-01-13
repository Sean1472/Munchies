package com.etho5.munchies.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/*
 Created on 13/01/2021 at 01:32
 Author - Sean
*/
public class MTab implements TabCompleter {

    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> completions = new ArrayList();
        if (args.length == 1) {
            completions.add("gui");
            return StringUtil.copyPartialMatches(args[0], completions, new ArrayList());
        } else {
            return completions;
        }
    }
}
