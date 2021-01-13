package com.etho5.munchies;

import com.etho5.munchies.Commands.MMain;
import com.etho5.munchies.Commands.MTab;
import com.etho5.munchies.Listeners.MenuHandler;
import com.etho5.munchies.OOP.Items;
import com.etho5.munchies.events.BlockBreakListener;
import com.etho5.munchies.events.EatFoodListener;
import com.etho5.munchies.events.RightClickListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;

/*
 Created on 13/01/2021 at 01:20
 Author - Sean
*/
public class Munchies extends JavaPlugin {

    private static Munchies instance;

    public void onEnable() {
        System.out.println("[Munchies] Registering listeners...");
        registerCommands();
        registerListeners(new RightClickListener(), new EatFoodListener(), new BlockBreakListener(), new MenuHandler());
        System.out.println("[Munchies] All listeners have been registered.");
        System.out.println("[Munchies] Adding shaped recipes...");
        for (ShapedRecipe r : generateShapedRecipes())
            getServer().addRecipe(r);
        System.out.println("[Munchies] All shaped recipes have been added.");
        System.out.println("[Munchies] Adding shapeless recipes...");
        for (ShapelessRecipe sr : generateShapelessRecipes())
            getServer().addRecipe(sr);
        System.out.println("[Munchies] All shapeless recipes have been added.");
        System.out.println("[Munchies] Adding furnace recipes...");
        for (FurnaceRecipe f : generateFurnaceRecipes())
            getServer().addRecipe(f);
        System.out.println("[Munchies] All furnace recipes have been added.");
        System.out.println("[Munchies] Munchies has been successfully enabled.");
    }

    private void registerListeners(Listener... lis) {
        for (Listener listener : lis)
            Bukkit.getPluginManager().registerEvents(listener, this);
    }

    private void registerCommands() {
        getCommand("munchies").setExecutor(new MMain());
        getCommand("munchies").setTabCompleter(new MTab());
    }

    public List<ShapedRecipe> generateShapedRecipes() {
        List<ShapedRecipe> recipes = new ArrayList<>();
        ShapedRecipe knife = new ShapedRecipe(new NamespacedKey(this, "knife"), Items.KNIFE.getItem());
        knife.shape("I", "S");
        knife.setIngredient('I', Material.IRON_INGOT);
        knife.setIngredient('S', Material.STICK);
        recipes.add(knife);
        ShapedRecipe can = new ShapedRecipe(new NamespacedKey(this, "can"), Items.CAN.getItem());
        can.shape("aba", "bab");
        can.setIngredient('a', Material.IRON_INGOT);
        can.setIngredient('b', Material.AIR);
        recipes.add(can);
        ShapedRecipe bagel = new ShapedRecipe(new NamespacedKey(this, "bagel"), Items.BAGEL.getItem());
        bagel.shape("aba", "bab", "aba");
        bagel.setIngredient('a', Material.AIR);
        bagel.setIngredient('b', Material.BREAD);
        recipes.add(bagel);
        ShapedRecipe sword = new ShapedRecipe(new NamespacedKey(this, "cheese_sword"), Items.CHEESE_SWORD.getItem());
        sword.shape("a", "a", "b");
        sword.setIngredient('a', new RecipeChoice.ExactChoice(Items.CHEESE.getItem()));
        sword.setIngredient('b', Material.STICK);
        recipes.add(sword);
        ShapedRecipe hamburger = new ShapedRecipe(new NamespacedKey(this, "hamburger"), Items.HAMBURGER.getItem());
        hamburger.shape("xax", "bcd", "xax");
        hamburger.setIngredient('x', Material.AIR);
        hamburger.setIngredient('a', Material.BREAD);
        hamburger.setIngredient('b', new RecipeChoice.ExactChoice(Items.TOMATO.getItem()));
        hamburger.setIngredient('c', Material.COOKED_BEEF);
        hamburger.setIngredient('d', Material.OAK_LEAVES);
        recipes.add(hamburger);
        return recipes;
    }

    public List<ShapelessRecipe> generateShapelessRecipes() {
        List<ShapelessRecipe> recipes = new ArrayList<>();
        ShapelessRecipe mortar = new ShapelessRecipe(new NamespacedKey(this, "mortar_and_pestle"), Items.MORTAR_AND_PESTLE.getItem());
        mortar.addIngredient(Material.BOWL);
        mortar.addIngredient(Material.COBBLESTONE);
        recipes.add(mortar);
        ShapelessRecipe turmeric = new ShapelessRecipe(new NamespacedKey(this, "turmeric_powder"), Items.TURMERIC_POWDER.getItem());
        turmeric.addIngredient(new RecipeChoice.ExactChoice(Items.TURMERIC.getItem()));
        turmeric.addIngredient(new RecipeChoice.ExactChoice(Items.MORTAR_AND_PESTLE.getItem()));
        recipes.add(turmeric);
        ShapelessRecipe chili = new ShapelessRecipe(new NamespacedKey(this, "chili_powder"), Items.CHILI_POWDER.getItem());
        chili.addIngredient(new RecipeChoice.ExactChoice(Items.CHILI_PEPPER.getItem()));
        chili.addIngredient(new RecipeChoice.ExactChoice(Items.MORTAR_AND_PESTLE.getItem()));
        recipes.add(chili);
        ShapelessRecipe coriander = new ShapelessRecipe(new NamespacedKey(this, "coriander"), Items.CORIANDER.getItem());
        coriander.addIngredient(new RecipeChoice.ExactChoice(Items.CILANTRO.getItem()));
        coriander.addIngredient(new RecipeChoice.ExactChoice(Items.MORTAR_AND_PESTLE.getItem()));
        recipes.add(coriander);
        ShapelessRecipe cumin = new ShapelessRecipe(new NamespacedKey(this, "cumin_powder"), Items.CUMIN_POWDER.getItem());
        cumin.addIngredient(new RecipeChoice.ExactChoice(Items.CUMIN.getItem()));
        cumin.addIngredient(new RecipeChoice.ExactChoice(Items.MORTAR_AND_PESTLE.getItem()));
        recipes.add(cumin);
        ShapelessRecipe currypowder = new ShapelessRecipe(new NamespacedKey(this, "curry_powder"), Items.CURRY_POWDER.getItem());
        currypowder.addIngredient(new RecipeChoice.ExactChoice(Items.TURMERIC_POWDER.getItem()));
        currypowder.addIngredient(new RecipeChoice.ExactChoice(Items.CHILI_POWDER.getItem()));
        currypowder.addIngredient(new RecipeChoice.ExactChoice(Items.CORIANDER.getItem()));
        currypowder.addIngredient(new RecipeChoice.ExactChoice(Items.CUMIN_POWDER.getItem()));
        recipes.add(currypowder);
        ShapelessRecipe tomato = new ShapelessRecipe(new NamespacedKey(this, "tomato_paste"), Items.TOMATO_PASTE.getItem());
        tomato.addIngredient(new RecipeChoice.ExactChoice(Items.TOMATO.getItem()));
        tomato.addIngredient(new RecipeChoice.ExactChoice(Items.MORTAR_AND_PESTLE.getItem()));
        recipes.add(tomato);
        ShapelessRecipe curry = new ShapelessRecipe(new NamespacedKey(this, "curry"), Items.CURRY.getItem());
        curry.addIngredient(new RecipeChoice.ExactChoice(Items.CURRY_POWDER.getItem()));
        curry.addIngredient(new RecipeChoice.ExactChoice(Items.CHICKEN_NUGGETS.getItem()));
        curry.addIngredient(Material.BOWL);
        recipes.add(curry);
        ShapelessRecipe coleans = new ShapelessRecipe(new NamespacedKey(this, "coleans"), Items.COLEANS.getItem());
        coleans.addIngredient(new RecipeChoice.ExactChoice(Items.CURRY.getItem()));
        coleans.addIngredient(Material.COCOA_BEANS);
        coleans.addIngredient(new RecipeChoice.ExactChoice(Items.CAN.getItem()));
        recipes.add(coleans);
        ShapelessRecipe applepie = new ShapelessRecipe(new NamespacedKey(this, "apple_pie"), Items.APPLE_PIE.getItem());
        applepie.addIngredient(Material.APPLE);
        applepie.addIngredient(Material.SUGAR);
        applepie.addIngredient(Material.EGG);
        recipes.add(applepie);
        ShapelessRecipe pecanpie = new ShapelessRecipe(new NamespacedKey(this, "pecan_pie"), Items.PECAN_PIE.getItem());
        pecanpie.addIngredient(new RecipeChoice.ExactChoice(Items.PECANS.getItem()));
        pecanpie.addIngredient(Material.SUGAR);
        pecanpie.addIngredient(Material.EGG);
        recipes.add(pecanpie);
        ShapelessRecipe blueberrypie = new ShapelessRecipe(new NamespacedKey(this, "blueberry_pie"), Items.BLUEBERRY_PIE.getItem());
        blueberrypie.addIngredient(new RecipeChoice.ExactChoice(Items.BLUEBERRIES.getItem()));
        blueberrypie.addIngredient(Material.SUGAR);
        blueberrypie.addIngredient(Material.EGG);
        recipes.add(blueberrypie);
        ShapelessRecipe raspberrypie = new ShapelessRecipe(new NamespacedKey(this, "raspberry_pie"), Items.RASPBERRY_PIE.getItem());
        raspberrypie.addIngredient(new RecipeChoice.ExactChoice(Items.RASPBERRIES.getItem()));
        raspberrypie.addIngredient(Material.SUGAR);
        raspberrypie.addIngredient(Material.EGG);
        recipes.add(raspberrypie);
        ShapelessRecipe corn = new ShapelessRecipe(new NamespacedKey(this, "corn_chowder"), Items.CORN_CHOWDER.getItem());
        corn.addIngredient(new RecipeChoice.ExactChoice(Items.TOMATO_PASTE.getItem()));
        corn.addIngredient(Material.BOWL);
        corn.addIngredient(new RecipeChoice.ExactChoice(Items.CORN.getItem()));
        recipes.add(corn);
        ShapelessRecipe flour = new ShapelessRecipe(new NamespacedKey(this, "flour"), Items.FLOUR.getItem());
        flour.addIngredient(Material.WHEAT);
        flour.addIngredient(new RecipeChoice.ExactChoice(Items.MORTAR_AND_PESTLE.getItem()));
        recipes.add(flour);
        ShapelessRecipe dough = new ShapelessRecipe(new NamespacedKey(this, "dough"), Items.DOUGH.getItem());
        dough.addIngredient(new RecipeChoice.ExactChoice(createWaterBottle()));
        dough.addIngredient(new RecipeChoice.ExactChoice(Items.FLOUR.getItem()));
        recipes.add(dough);
        ShapelessRecipe noodles = new ShapelessRecipe(new NamespacedKey(this, "noodles"), Items.NOODLES.getItem());
        noodles.addIngredient(new RecipeChoice.ExactChoice(Items.DOUGH.getItem()));
        noodles.addIngredient(new RecipeChoice.ExactChoice(Items.KNIFE.getItem()));
        recipes.add(noodles);
        ShapelessRecipe spaghetti = new ShapelessRecipe(new NamespacedKey(this, "spaghetti"), Items.SPAGHETTI.getItem());
        spaghetti.addIngredient(new RecipeChoice.ExactChoice(Items.TOMATO_PASTE.getItem()));
        spaghetti.addIngredient(Material.BOWL);
        spaghetti.addIngredient(new RecipeChoice.ExactChoice(Items.NOODLES.getItem()));
        recipes.add(spaghetti);
        ShapelessRecipe creamcheese = new ShapelessRecipe(new NamespacedKey(this, "cream_cheese"), Items.CREAM_CHEESE.getItem());
        creamcheese.addIngredient(new RecipeChoice.ExactChoice(Items.CHEESE.getItem()));
        creamcheese.addIngredient(Material.MILK_BUCKET);
        creamcheese.addIngredient(new RecipeChoice.ExactChoice(Items.MORTAR_AND_PESTLE.getItem()));
        recipes.add(creamcheese);
        ShapelessRecipe bagel = new ShapelessRecipe(new NamespacedKey(this, "cream_cheese_bagel"), Items.CREAM_CHEESE_BAGEL.getItem());
        bagel.addIngredient(new RecipeChoice.ExactChoice(Items.BAGEL.getItem()));
        bagel.addIngredient(new RecipeChoice.ExactChoice(Items.KNIFE.getItem()));
        bagel.addIngredient(new RecipeChoice.ExactChoice(Items.CREAM_CHEESE.getItem()));
        recipes.add(bagel);
        ShapelessRecipe frenchfry = new ShapelessRecipe(new NamespacedKey(this, "french_fries"), Items.FRENCH_FRIES.getItem());
        frenchfry.addIngredient(Material.BAKED_POTATO);
        frenchfry.addIngredient(new RecipeChoice.ExactChoice(Items.KNIFE.getItem()));
        recipes.add(frenchfry);
        ShapelessRecipe fish = new ShapelessRecipe(new NamespacedKey(this, "fish_and_chips"), Items.FISH_AND_CHIPS.getItem());
        fish.addIngredient(Material.COOKED_COD);
        fish.addIngredient(new RecipeChoice.ExactChoice(Items.FRENCH_FRIES.getItem()));
        recipes.add(fish);
        ShapelessRecipe burger = new ShapelessRecipe(new NamespacedKey(this, "cheeseburger"), Items.CHEESEBURGER.getItem());
        burger.addIngredient(new RecipeChoice.ExactChoice(Items.HAMBURGER.getItem()));
        burger.addIngredient(new RecipeChoice.ExactChoice(Items.CHEESE.getItem()));
        recipes.add(burger);
        ShapelessRecipe carbonara = new ShapelessRecipe(new NamespacedKey(this, "carbonara"), Items.CARBONARA.getItem());
        carbonara.addIngredient(new RecipeChoice.ExactChoice(Items.NOODLES.getItem()));
        carbonara.addIngredient(Material.EGG);
        carbonara.addIngredient(Material.COOKED_PORKCHOP);
        carbonara.addIngredient(Material.BOWL);
        recipes.add(carbonara);
        ShapelessRecipe cola = new ShapelessRecipe(new NamespacedKey(this, "colenia_cola"), Items.COLENIA_COLA.getItem());
        cola.addIngredient(new RecipeChoice.ExactChoice(Items.CAN.getItem()));
        cola.addIngredient(new RecipeChoice.ExactChoice(Items.LEMON.getItem()));
        cola.addIngredient(new RecipeChoice.ExactChoice(Items.CORIANDER.getItem()));
        cola.addIngredient(new RecipeChoice.ExactChoice(createWaterBottle()));
        ShapelessRecipe mac = new ShapelessRecipe(new NamespacedKey(this, "macaroni_and_cheese"), Items.MACARONI_AND_CHEESE.getItem());
        mac.addIngredient(new RecipeChoice.ExactChoice(Items.NOODLES.getItem()));
        mac.addIngredient(new RecipeChoice.ExactChoice(Items.CHEESE.getItem()));
        mac.addIngredient(Material.BOWL);
        recipes.add(mac);
        ShapelessRecipe cocoa = new ShapelessRecipe(new NamespacedKey(this, "cocoa_powder"), Items.COCOA_POWDER.getItem());
        cocoa.addIngredient(Material.COCOA_BEANS);
        cocoa.addIngredient(new RecipeChoice.ExactChoice(Items.MORTAR_AND_PESTLE.getItem()));
        recipes.add(cocoa);
        ShapelessRecipe chocolate = new ShapelessRecipe(new NamespacedKey(this, "chocolate"), Items.CHOCOLATE.getItem());
        chocolate.addIngredient(Material.MILK_BUCKET);
        chocolate.addIngredient(new RecipeChoice.ExactChoice(Items.COCOA_POWDER.getItem()));
        recipes.add(chocolate);
        ShapelessRecipe trail = new ShapelessRecipe(new NamespacedKey(this, "trail_mix"), Items.TRAIL_MIX.getItem());
        trail.addIngredient(new RecipeChoice.ExactChoice(Items.ALMONDS.getItem()));
        trail.addIngredient(new RecipeChoice.ExactChoice(Items.PECANS.getItem()));
        trail.addIngredient(new RecipeChoice.ExactChoice(Items.CHOCOLATE.getItem()));
        trail.addIngredient(new RecipeChoice.ExactChoice(Items.RAISINS.getItem()));
        recipes.add(trail);
        return recipes;
    }

    private ItemStack createWaterBottle() {
        ItemStack pot = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) pot.getItemMeta();
        assert meta != null;
        meta.setBasePotionData(new PotionData(PotionType.WATER));
        pot.setItemMeta(meta);
        return pot;
    }

    public List<FurnaceRecipe> generateFurnaceRecipes() {
        List<FurnaceRecipe> recipes = new ArrayList<>();
        recipes.add(new FurnaceRecipe(new NamespacedKey(this, "cheese"), Items.CHEESE.getItem(), Material.MILK_BUCKET, 0.35F, 240));
        recipes.add(new FurnaceRecipe(new NamespacedKey(this, "scrambled_eggs"), Items.SCRAMBLED_EGGS.getItem(), Material.EGG, 0.35F, 240));
        recipes.add(new FurnaceRecipe(new NamespacedKey(this, "toast"), Items.TOAST.getItem(), Material.BREAD, 0.35F, 240));
        recipes.add(new FurnaceRecipe(new NamespacedKey(this, "raisins"), Items.CHEESE.getItem(), new RecipeChoice.ExactChoice(Items.GRAPES.getItem()), 0.35F, 240));
        return recipes;
    }

    public static Munchies getInstance() {
        return instance == null ? instance = Munchies.getPlugin(Munchies.class) : instance;
    }


}
