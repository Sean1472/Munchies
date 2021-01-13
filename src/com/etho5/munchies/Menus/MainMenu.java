package com.etho5.munchies.Menus;

import com.etho5.munchies.AbstractMenu.PaginatedMenu;
import com.etho5.munchies.Munchies;
import com.etho5.munchies.OOP.Items;
import com.etho5.munchies.Utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

/*
 Created on 13/01/2021 at 01:34
 Author - Sean
*/
public class MainMenu extends PaginatedMenu {
    public MainMenu(Player player) {
        super(player);
    }

    public String getMenuName() {
        return "Munchies - Main";
    }

    public int getSlots() {
        return 54;
    }

    public void handleMenu(InventoryClickEvent e) {
        if (e.getCurrentItem() != null) {
            if (e.getCurrentItem().hasItemMeta()) {
                PersistentDataContainer container = e.getCurrentItem().getItemMeta().getPersistentDataContainer();
                if (container.has(new NamespacedKey(Munchies.getInstance(), "Items"), PersistentDataType.STRING)) {
                    String name = container.get(new NamespacedKey(Munchies.getInstance(), "Items"), PersistentDataType.STRING);
                    ItemStack items = Items.valueOf(name).getItem();
                    if (e.getClick() == ClickType.LEFT) {
                        this.getPlayer().getInventory().addItem(items);
                        this.getPlayer().sendMessage(Utils.color("&6&l(!) &fAdded &a" + StringUtils.capitalize(name.toLowerCase()) + " &fto your inventory!"));
                    } else if (e.getClick() == ClickType.RIGHT) {
                        items.setAmount(64);
                        this.getPlayer().getInventory().addItem(items);
                        this.getPlayer().sendMessage(Utils.color("&6&l(!) &fAdded 64 of &a" + StringUtils.capitalize(name.toLowerCase()) + " &fto your inventory!"));
                    }
                }

                if (e.getCurrentItem().isSimilar(this.CLOSE_ITEM)) {
                    this.getPlayer().closeInventory();
                } else if (e.getCurrentItem().isSimilar(this.NEXT_BUTTON)) {
                    if (this.index + 1 < this.getItemsList().length) {
                        ++this.page;
                        super.open();
                    }
                } else if (e.getCurrentItem().isSimilar(this.BACK_BUTTON)) {
                    if (this.page == 0) {
                        return;
                    }

                    --this.page;
                    super.open();
                }

            }
        }
    }

    public void setMenuItems() {
        this.setCloseButton(49);
        this.setBackButton(45);
        this.setForwardButton(53);
        if (this.getItemsList().length != 0) {
            for (int i = 0; i < this.getMaxPageItems(); ++i) {
                this.index = this.getMaxPageItems() * this.page + i;
                if (this.index >= this.getItemsList().length) {
                    break;
                }

                if (this.getItemsList()[this.index] == null) {
                    return;
                }

                ItemStack guiItem = Utils.createGUIItem(this.getItemsList()[this.index]);
                this.inventory.addItem(guiItem);
            }

        }
    }

    protected int getMaxPageItems() {
        return 45;
    }

    private Items[] getItemsList() {
        return Items.values();
    }
}
