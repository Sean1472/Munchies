package com.etho5.munchies.AbstractMenu;

import org.bukkit.entity.Player;

/*
 Created on 13/01/2021 at 01:34
 Author - Sean
*/
public abstract class PaginatedMenu extends AbstractMenu {
    protected int page = 0;
    protected int index = 0;

    public PaginatedMenu(Player player) {
        super(player);
    }

    protected abstract int getMaxPageItems();
}
