package com.etho5.munchies;

import com.etho5.munchies.events.BlockBreakListener;
import com.etho5.munchies.events.EatFoodListener;
import com.etho5.munchies.events.RightClickListener;
import com.etho5.munchies.items.CustomItem;
import com.etho5.munchies.items.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends JavaPlugin {

    public void onEnable() {
        System.out.println("[Munchies] Registering listeners...");
        getServer().getPluginManager().registerEvents(new RightClickListener(), this);
        getServer().getPluginManager().registerEvents(new EatFoodListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        System.out.println("[Munchies] All listeners have been registered.");

        System.out.println("[Munchies] Adding shaped recipes...");
        for(ShapedRecipe r : generateShapedRecipes()) {
            getServer().addRecipe(r);
        }
        System.out.println("[Munchies] All shaped recipes have been added.");

        System.out.println("[Munchies] Adding shapeless recipes...");
        for(ShapelessRecipe sr : generateShapelessRecipes()) {
            getServer().addRecipe(sr);
        }
        System.out.println("[Munchies] All shapeless recipes have been added.");

        System.out.println("[Munchies] Adding furnace recipes...");
        for(FurnaceRecipe f : generateFurnaceRecipes()) {
            getServer().addRecipe(f);
        }
        System.out.println("[Munchies] All furnace recipes have been added.");

        System.out.println("[Munchies] Munchies has been successfully enabled.");
    }

    public void onDisable() {
        System.out.println("[Munchies] Munchies has been successfully disabled.");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            if(args[0].equals("munchies") || args[0].equals("munch") || args[0].equals("fooditems")) {
                if(sender.hasPermission("munchies.*")) {
                    Inventory inv = Bukkit.createInventory(null, 54, "Munchies");
                    Items[] items = Items.values();
                    for(int x = 0; x < items.length; x++) {
                        CustomItem item = items[x].getItem();
                        ItemMeta meta = item.getItemMeta();
                        assert meta != null;
                        meta.setLore(Arrays.asList(ChatColor.GREEN + ChatColor.BOLD.toString() + "LEFT CLICK:" + ChatColor.RESET + "1", ChatColor.RED + ChatColor.BOLD.toString() + "RIGHT CLICK:" + ChatColor.RESET + "64"));
                        item.setItemMeta(meta);
                        inv.setItem(x, item);
                    }
                    ((Player) sender).openInventory(inv);
                } else {
                    sender.sendMessage(ChatColor.GREEN + "[Munchies] " + ChatColor.YELLOW + "by Etho5");
                }
            }
        } else {
            System.out.println("[Munchies] Only players can execute commands!");
            return false;
        }
        return true;
    }

    public List<ShapedRecipe> generateShapedRecipes() {
        Main plugin = this;
        List<ShapedRecipe> recipes = new ArrayList<>();

        //knife
        ShapedRecipe knife = new ShapedRecipe(new NamespacedKey(plugin, "knife"), Items.KNIFE.getItem());
        knife.shape("I", "S");
        knife.setIngredient('I', Material.IRON_INGOT);
        knife.setIngredient('S', Material.STICK);
        recipes.add(knife);

        //can
        ShapedRecipe can = new ShapedRecipe(new NamespacedKey(plugin, "can"), Items.CAN.getItem());
        can.shape("aba", "bab");
        can.setIngredient('a', Material.IRON_INGOT);
        can.setIngredient('b', Material.AIR);
        recipes.add(can);

        //bagel
        ShapedRecipe bagel = new ShapedRecipe(new NamespacedKey(plugin, "bagel"), Items.BAGEL.getItem());
        bagel.shape("aba", "bab", "aba");
        bagel.setIngredient('a', Material.AIR);
        bagel.setIngredient('b', Material.BREAD);
        recipes.add(bagel);

        //cheese sword
        ShapedRecipe sword = new ShapedRecipe(new NamespacedKey(plugin, "cheese_sword"), Items.CHEESE_SWORD.getItem());
        sword.shape("a", "a", "b");
        sword.setIngredient('a', new RecipeChoice.ExactChoice(Items.CHEESE.getItem()));
        sword.setIngredient('b', Material.STICK);
        recipes.add(sword);

        //hamburger
        ShapedRecipe hamburger = new ShapedRecipe(new NamespacedKey(plugin, "hamburger"), Items.HAMBURGER.getItem());
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
        Main plugin = this;
        List<ShapelessRecipe> recipes = new ArrayList<>();

        //mortar and pestle
        ShapelessRecipe mortar = new ShapelessRecipe(new NamespacedKey(plugin, "mortar_and_pestle"), Items.MORTAR_AND_PESTLE.getItem());
        mortar.addIngredient(Material.BOWL);
        mortar.addIngredient(Material.COBBLESTONE);
        recipes.add(mortar);

        //turmeric powder
        ShapelessRecipe turmeric = new ShapelessRecipe(new NamespacedKey(plugin, "turmeric_powder"), Items.TURMERIC_POWDER.getItem());
        turmeric.addIngredient(new RecipeChoice.ExactChoice(Items.TURMERIC.getItem()));
        turmeric.addIngredient(new RecipeChoice.ExactChoice(Items.MORTAR_AND_PESTLE.getItem()));
        recipes.add(turmeric);

        //chili powder
        ShapelessRecipe chili = new ShapelessRecipe(new NamespacedKey(plugin, "chili_powder"), Items.CHILI_POWDER.getItem());
        chili.addIngredient(new RecipeChoice.ExactChoice(Items.CHILI_PEPPER.getItem()));
        chili.addIngredient(new RecipeChoice.ExactChoice(Items.MORTAR_AND_PESTLE.getItem()));
        recipes.add(chili);

        //coriander
        ShapelessRecipe coriander = new ShapelessRecipe(new NamespacedKey(plugin, "coriander"), Items.CORIANDER.getItem());
        coriander.addIngredient(new RecipeChoice.ExactChoice(Items.CILANTRO.getItem()));
        coriander.addIngredient(new RecipeChoice.ExactChoice(Items.MORTAR_AND_PESTLE.getItem()));
        recipes.add(coriander);

        //cumin powder
        ShapelessRecipe cumin = new ShapelessRecipe(new NamespacedKey(plugin, "cumin_powder"), Items.CUMIN_POWDER.getItem());
        cumin.addIngredient(new RecipeChoice.ExactChoice(Items.CUMIN.getItem()));
        cumin.addIngredient(new RecipeChoice.ExactChoice(Items.MORTAR_AND_PESTLE.getItem()));
        recipes.add(cumin);

        //curry powder
        ShapelessRecipe currypowder = new ShapelessRecipe(new NamespacedKey(plugin, "curry_powder"), Items.CURRY_POWDER.getItem());
        currypowder.addIngredient(new RecipeChoice.ExactChoice(Items.TURMERIC_POWDER.getItem()));
        currypowder.addIngredient(new RecipeChoice.ExactChoice(Items.CHILI_POWDER.getItem()));
        currypowder.addIngredient(new RecipeChoice.ExactChoice(Items.CORIANDER.getItem()));
        currypowder.addIngredient(new RecipeChoice.ExactChoice(Items.CUMIN_POWDER.getItem()));
        recipes.add(currypowder);

        //tomato paste
        ShapelessRecipe tomato = new ShapelessRecipe(new NamespacedKey(plugin, "tomato_paste"), Items.TOMATO_PASTE.getItem());
        tomato.addIngredient(new RecipeChoice.ExactChoice(Items.TOMATO.getItem()));
        tomato.addIngredient(new RecipeChoice.ExactChoice(Items.MORTAR_AND_PESTLE.getItem()));
        recipes.add(tomato);

        //curry
        ShapelessRecipe curry = new ShapelessRecipe(new NamespacedKey(plugin, "curry"), Items.CURRY.getItem());
        curry.addIngredient(new RecipeChoice.ExactChoice(Items.CURRY_POWDER.getItem()));
        curry.addIngredient(new RecipeChoice.ExactChoice(Items.CHICKEN_NUGGETS.getItem()));
        curry.addIngredient(Material.BOWL);
        recipes.add(curry);

        //coleans
        ShapelessRecipe coleans = new ShapelessRecipe(new NamespacedKey(plugin, "coleans"), Items.COLEANS.getItem());
        coleans.addIngredient(new RecipeChoice.ExactChoice(Items.CURRY.getItem()));
        coleans.addIngredient(Material.COCOA_BEANS);
        coleans.addIngredient(new RecipeChoice.ExactChoice(Items.CAN.getItem()));
        recipes.add(coleans);

        //apple pie
        ShapelessRecipe applepie = new ShapelessRecipe(new NamespacedKey(plugin, "apple_pie"), Items.APPLE_PIE.getItem());
        applepie.addIngredient(Material.APPLE);
        applepie.addIngredient(Material.SUGAR);
        applepie.addIngredient(Material.EGG);
        recipes.add(applepie);

        //pecan pie
        ShapelessRecipe pecanpie = new ShapelessRecipe(new NamespacedKey(plugin, "pecan_pie"), Items.PECAN_PIE.getItem());
        pecanpie.addIngredient(new RecipeChoice.ExactChoice(Items.PECANS.getItem()));
        pecanpie.addIngredient(Material.SUGAR);
        pecanpie.addIngredient(Material.EGG);
        recipes.add(pecanpie);

        //blueberry pie
        ShapelessRecipe blueberrypie = new ShapelessRecipe(new NamespacedKey(plugin, "blueberry_pie"), Items.BLUEBERRY_PIE.getItem());
        blueberrypie.addIngredient(new RecipeChoice.ExactChoice(Items.BLUEBERRIES.getItem()));
        blueberrypie.addIngredient(Material.SUGAR);
        blueberrypie.addIngredient(Material.EGG);
        recipes.add(blueberrypie);

        //raspberry pie
        ShapelessRecipe raspberrypie = new ShapelessRecipe(new NamespacedKey(plugin, "raspberry_pie"), Items.RASPBERRY_PIE.getItem());
        raspberrypie.addIngredient(new RecipeChoice.ExactChoice(Items.RASPBERRIES.getItem()));
        raspberrypie.addIngredient(Material.SUGAR);
        raspberrypie.addIngredient(Material.EGG);
        recipes.add(raspberrypie);

        //corn chowder
        ShapelessRecipe corn = new ShapelessRecipe(new NamespacedKey(plugin, "corn_chowder"), Items.CORN_CHOWDER.getItem());
        corn.addIngredient(new RecipeChoice.ExactChoice(Items.TOMATO_PASTE.getItem()));
        corn.addIngredient(Material.BOWL);
        corn.addIngredient(new RecipeChoice.ExactChoice(Items.CORN.getItem()));
        recipes.add(corn);

        //flour
        ShapelessRecipe flour = new ShapelessRecipe(new NamespacedKey(plugin, "flour"), Items.FLOUR.getItem());
        flour.addIngredient(Material.WHEAT);
        flour.addIngredient(new RecipeChoice.ExactChoice(Items.MORTAR_AND_PESTLE.getItem()));
        recipes.add(flour);

        //dough
        ShapelessRecipe dough = new ShapelessRecipe(new NamespacedKey(plugin, "dough"), Items.DOUGH.getItem());
        dough.addIngredient(new RecipeChoice.ExactChoice(createWaterBottle()));
        dough.addIngredient(new RecipeChoice.ExactChoice(Items.FLOUR.getItem()));
        recipes.add(dough);

        //noodles
        ShapelessRecipe noodles = new ShapelessRecipe(new NamespacedKey(plugin, "noodles"), Items.NOODLES.getItem());
        noodles.addIngredient(new RecipeChoice.ExactChoice(Items.DOUGH.getItem()));
        noodles.addIngredient(new RecipeChoice.ExactChoice(Items.KNIFE.getItem()));
        recipes.add(noodles);

        //spaghetti
        ShapelessRecipe spaghetti = new ShapelessRecipe(new NamespacedKey(plugin, "spaghetti"), Items.SPAGHETTI.getItem());
        spaghetti.addIngredient(new RecipeChoice.ExactChoice(Items.TOMATO_PASTE.getItem()));
        spaghetti.addIngredient(Material.BOWL);
        spaghetti.addIngredient(new RecipeChoice.ExactChoice(Items.NOODLES.getItem()));
        recipes.add(spaghetti);

        //cream cheese
        ShapelessRecipe creamcheese = new ShapelessRecipe(new NamespacedKey(plugin, "cream_cheese"), Items.CREAM_CHEESE.getItem());
        creamcheese.addIngredient(new RecipeChoice.ExactChoice(Items.CHEESE.getItem()));
        creamcheese.addIngredient(Material.MILK_BUCKET);
        creamcheese.addIngredient(new RecipeChoice.ExactChoice(Items.MORTAR_AND_PESTLE.getItem()));
        recipes.add(creamcheese);

        //cream cheese bagel
        ShapelessRecipe bagel = new ShapelessRecipe(new NamespacedKey(plugin, "cream_cheese_bagel"), Items.CREAM_CHEESE_BAGEL.getItem());
        bagel.addIngredient(new RecipeChoice.ExactChoice(Items.BAGEL.getItem()));
        bagel.addIngredient(new RecipeChoice.ExactChoice(Items.KNIFE.getItem()));
        bagel.addIngredient(new RecipeChoice.ExactChoice(Items.CREAM_CHEESE.getItem()));
        recipes.add(bagel);

        //french fries
        ShapelessRecipe frenchfry = new ShapelessRecipe(new NamespacedKey(plugin, "french_fries"), Items.FRENCH_FRIES.getItem());
        frenchfry.addIngredient(Material.BAKED_POTATO);
        frenchfry.addIngredient(new RecipeChoice.ExactChoice(Items.KNIFE.getItem()));
        recipes.add(frenchfry);

        //fish and chips
        ShapelessRecipe fish = new ShapelessRecipe(new NamespacedKey(plugin, "fish_and_chips"), Items.FISH_AND_CHIPS.getItem());
        fish.addIngredient(Material.COOKED_COD);
        fish.addIngredient(new RecipeChoice.ExactChoice(Items.FRENCH_FRIES.getItem()));
        recipes.add(fish);

        //cheeseburger
        ShapelessRecipe burger = new ShapelessRecipe(new NamespacedKey(plugin, "cheeseburger"), Items.CHEESEBURGER.getItem());
        burger.addIngredient(new RecipeChoice.ExactChoice(Items.HAMBURGER.getItem()));
        burger.addIngredient(new RecipeChoice.ExactChoice(Items.CHEESE.getItem()));
        recipes.add(burger);

        //carbonara
        ShapelessRecipe carbonara = new ShapelessRecipe(new NamespacedKey(plugin, "carbonara"), Items.CARBONARA.getItem());
        carbonara.addIngredient(new RecipeChoice.ExactChoice(Items.NOODLES.getItem()));
        carbonara.addIngredient(Material.EGG);
        carbonara.addIngredient(Material.COOKED_PORKCHOP);
        carbonara.addIngredient(Material.BOWL);
        recipes.add(carbonara);

        //colenia cola
        ShapelessRecipe cola = new ShapelessRecipe(new NamespacedKey(plugin, "colenia_cola"), Items.COLENIA_COLA.getItem());
        cola.addIngredient(new RecipeChoice.ExactChoice(Items.CAN.getItem()));
        cola.addIngredient(new RecipeChoice.ExactChoice(Items.LEMON.getItem()));
        cola.addIngredient(new RecipeChoice.ExactChoice(Items.CORIANDER.getItem()));
        cola.addIngredient(new RecipeChoice.ExactChoice(createWaterBottle()));

        //macaroni and cheese
        ShapelessRecipe mac = new ShapelessRecipe(new NamespacedKey(plugin, "macaroni_and_cheese"), Items.MACARONI_AND_CHEESE.getItem());
        mac.addIngredient(new RecipeChoice.ExactChoice(Items.NOODLES.getItem()));
        mac.addIngredient(new RecipeChoice.ExactChoice(Items.CHEESE.getItem()));
        mac.addIngredient(Material.BOWL);
        recipes.add(mac);

        //cocoa powder
        ShapelessRecipe cocoa = new ShapelessRecipe(new NamespacedKey(plugin, "cocoa_powder"), Items.COCOA_POWDER.getItem());
        cocoa.addIngredient(Material.COCOA_BEANS);
        cocoa.addIngredient(new RecipeChoice.ExactChoice(Items.MORTAR_AND_PESTLE.getItem()));
        recipes.add(cocoa);

        //chocolate
        ShapelessRecipe chocolate = new ShapelessRecipe(new NamespacedKey(plugin, "chocolate"), Items.CHOCOLATE.getItem());
        chocolate.addIngredient(Material.MILK_BUCKET);
        chocolate.addIngredient(new RecipeChoice.ExactChoice(Items.COCOA_POWDER.getItem()));
        recipes.add(chocolate);

        //trail mix
        ShapelessRecipe trail = new ShapelessRecipe(new NamespacedKey(plugin, "trail_mix"), Items.TRAIL_MIX.getItem());
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
        Main plugin = this;
        List<FurnaceRecipe> recipes = new ArrayList<>();

        //cheese
        recipes.add(new FurnaceRecipe(new NamespacedKey(plugin, "cheese"), Items.CHEESE.getItem(), Material.MILK_BUCKET, 0.35F, 240));

        //scrambled eggs
        recipes.add(new FurnaceRecipe(new NamespacedKey(plugin, "scrambled_eggs"), Items.SCRAMBLED_EGGS.getItem(), Material.EGG, 0.35F, 240));

        //toast
        recipes.add(new FurnaceRecipe(new NamespacedKey(plugin, "toast"), Items.TOAST.getItem(), Material.BREAD, 0.35F, 240));

        //raisins
        recipes.add(new FurnaceRecipe(new NamespacedKey(plugin, "raisins"), Items.CHEESE.getItem(), new RecipeChoice.ExactChoice(Items.GRAPES.getItem()), 0.35F, 240));

        return recipes;
    }
}
