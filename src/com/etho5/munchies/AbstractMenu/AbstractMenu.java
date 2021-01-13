package com.etho5.munchies.AbstractMenu;

import com.etho5.munchies.Utils.ItemBuilder;
import com.etho5.munchies.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/*
 Created on 13/01/2021 at 01:34
 Author - Sean
*/
public abstract class AbstractMenu implements InventoryHolder {
    protected Inventory inventory;
    public Player player;
    protected ItemStack CLOSE_ITEM;
    protected ItemStack NEXT_BUTTON;
    protected ItemStack BACK_BUTTON;
    protected List<Integer> sideSlots;

    public AbstractMenu(Player player) {
        this.CLOSE_ITEM = (new ItemBuilder(Material.NETHER_STAR)).setName("&aClick to close the menu!").build();
        this.NEXT_BUTTON = (new ItemBuilder(Material.OAK_BUTTON)).addPersistentString("ForwardButton", "Forward").setName("&eClick to go forward a page").build();
        this.BACK_BUTTON = (new ItemBuilder(Material.OAK_BUTTON)).addPersistentString("BackButton", "Back").setName("&eClick to go back a page").build();
        this.sideSlots = new ArrayList();
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setCloseButton(int slot) {
        this.inventory.setItem(slot, this.CLOSE_ITEM);
    }

    public void setBackButton(int slot) {
        this.inventory.setItem(slot, this.BACK_BUTTON);
    }

    public void setForwardButton(int slot) {
        this.inventory.setItem(slot, this.NEXT_BUTTON);
    }

    public void fillAll(ItemStack itemStack) {
        for (int i = 0; i < this.inventory.getSize(); ++i) {
            if (this.inventory.getItem(i) == null) {
                this.inventory.setItem(i, itemStack);
            }
        }

    }

    public void fillBorder(ItemStack itemStack) {
        int size = this.inventory.getSize();
        int rows = size / 9;
        if (rows >= 3) {
            int lr;
            for (lr = 0; lr <= 8; ++lr) {
                this.inventory.setItem(lr, itemStack);
                this.sideSlots.add(lr);
            }

            for (lr = 8; lr < this.inventory.getSize() - 9; lr += 9) {
                int lastSlot = lr + 1;
                this.inventory.setItem(lr, itemStack);
                this.inventory.setItem(lastSlot, itemStack);
                this.sideSlots.add(lr);
                this.sideSlots.add(lastSlot);
            }

            for (lr = this.inventory.getSize() - 9; lr < this.inventory.getSize(); ++lr) {
                this.inventory.setItem(lr, itemStack);
                this.sideSlots.add(lr);
            }
        }

    }

    public List<Integer> getNonSideSlots() {
        List<Integer> availableSlots = new ArrayList();

        for (int i = 0; i < this.inventory.getSize(); ++i) {
            if (!this.sideSlots.contains(i)) {
                availableSlots.add(i);
            }
        }

        return availableSlots;
    }

    public abstract String getMenuName();

    public abstract int getSlots();

    public abstract void handleMenu(InventoryClickEvent var1);

    public abstract void setMenuItems();

    public void open() {
        this.inventory = Bukkit.createInventory(this, this.getSlots(), Utils.color(this.getMenuName()));
        this.setMenuItems();
        this.getPlayer().openInventory(this.inventory);
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
