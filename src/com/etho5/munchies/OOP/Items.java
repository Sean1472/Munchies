package com.etho5.munchies.OOP;

import org.bukkit.Material;

public enum Items {
    MORTAR_AND_PESTLE("Mortar and Pestle", 61),
    KNIFE("Knife", 62, Material.STONE_SWORD),
    ALMONDS("Almonds", 63, Material.COOKIE),
    PECANS("Pecans", 64, Material.COOKIE),
    TURMERIC("Turmeric", 65),
    CUMIN("Cumin", 66),
    CHILI_PEPPER("Chili Pepper", 67, Material.COOKIE),
    CILANTRO("Cilantro", 68),
    TURMERIC_POWDER("Turmeric Powder", 69),
    CHILI_POWDER("Chili Powder", 70),
    CORIANDER("Coriander", 71),
    CUMIN_POWDER("Cumin Powder", 72),
    CURRY_POWDER("Curry Powder", 73),
    TOMATO("Tomato", 74, Material.COOKIE),
    TOMATO_PASTE("Tomato Paste", 75),
    CURRY("Curry", 76, Material.MUSHROOM_STEW),
    CAN("Can", 77),
    COLEANS("Coleans", 78),
    BLUEBERRIES("Blueberries", 79, Material.SWEET_BERRIES),
    RASPBERRIES("Raspberries", 80, Material.SWEET_BERRIES),
    GRAPES("Grapes", 81, Material.SWEET_BERRIES),
    APPLE_PIE("Apple Pie", 82, Material.PUMPKIN_PIE),
    PECAN_PIE("Pecan Pie", 83, Material.PUMPKIN_PIE),
    BLUEBERRY_PIE("Blueberry Pie", 84, Material.PUMPKIN_PIE),
    RASPBERRY_PIE("Raspberry Pie", 85, Material.PUMPKIN_PIE),
    CORN("Corn", 86, Material.COOKIE),
    CORN_CHOWDER("Corn Chowder", 87, Material.MUSHROOM_STEW),
    FLOUR("Flour", 88),
    DOUGH("Dough", 89),
    NOODLES("Noodles", 90),
    SPAGHETTI("Spaghetti", 91, Material.RABBIT_STEW),
    CHEESE("Cheese", 92, Material.BREAD),
    CREAM_CHEESE("Cream Cheese", 93, Material.BREAD),
    BAGEL("Bagel", 94, Material.BREAD),
    CREAM_CHEESE_BAGEL("Cream Cheese Bagel", 95, Material.COOKED_PORKCHOP),
    FRENCH_FRIES("French Fries", 96, Material.BAKED_POTATO),
    FISH_AND_CHIPS("Fish and Chips", 97, Material.COOKED_PORKCHOP),
    CHEESE_SWORD("Cheese Sword", 98, Material.STONE_SWORD),
    HAMBURGER("Hamburger", 99, Material.COOKED_BEEF),
    CHEESEBURGER("Cheeseburger", 100, Material.COOKED_BEEF),
    SCRAMBLED_EGGS("Scrambled Eggs", 101, Material.COOKIE),
    CARBONARA("Carbonara", 102, Material.RABBIT_STEW),
    CHICKEN_NUGGETS("Chicken Nuggets", 103, Material.COOKED_CHICKEN),
    TOAST("Toast", 104, Material.BREAD),
    COLENIA_COLA("Colenia Cola", 105),
    MACARONI_AND_CHEESE("Macaroni and Cheese", 106, Material.RABBIT_STEW),
    COCOA_POWDER("Cocoa Powder", 107),
    CHOCOLATE("Chocolate", 108, Material.COOKIE),
    RAISINS("Raisins", 109, Material.SWEET_BERRIES),
    TRAIL_MIX("Trail Mix", 110),
    LEMON("Lemon", 111)
    ;

    CustomItem item;
    Items(String displayName, int customModelData) {
        item = new CustomItem(displayName, customModelData);
    }

    Items(String displayName, int customModelData, Material baseItem) {
        item = new CustomItem(displayName, customModelData, baseItem);
    }

    public CustomItem getItem() {
        return this.item;
    }
}
