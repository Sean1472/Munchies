package com.etho5.munchies.Utils;

import com.etho5.munchies.Munchies;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/*
 Created on 13/01/2021 at 01:18
 Author - Sean
*/
public class ItemBuilder {
    private final ItemStack itemStack;
    private Method metaSetProfileMethod;
    private Field metaProfileField;

    public ItemBuilder(Material material) {
        this(new ItemStack(material, 1));
    }

    public ItemBuilder(Material material, int amount, short data) {
        this(new ItemStack(material, amount, data));
    }

    public ItemBuilder(ItemStack item) {
        this.itemStack = item;
    }

    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemMeta getItemMeta() {
        return this.itemStack.getItemMeta();
    }

    public ItemBuilder setItemMeta(ItemMeta meta) {
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(Utils.color(name));
        this.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        ItemMeta meta = this.getItemMeta();
        meta.setLore(Utils.color(Arrays.asList(lore)));
        this.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta meta = this.getItemMeta();
        meta.setLore(Utils.color(lore));
        this.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setBase64(String input) {
        Validate.notNull(input, "base64");
        if (!(this.getItemMeta() instanceof SkullMeta)) {
            return null;
        } else {
            SkullMeta meta = (SkullMeta) this.getItemMeta();
            this.mutateItemMeta(meta, input);
            this.setItemMeta(meta);
            return this;
        }
    }

    public ItemBuilder setGlow(boolean glow) {
        if (glow) {
            ItemMeta meta = this.itemStack.getItemMeta();
            meta.addEnchant(Enchantment.KNOCKBACK, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            this.itemStack.setItemMeta(meta);
        }

        return this;
    }

    public ItemBuilder addPersistentString(String namedKey, String input) {
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(Munchies.getInstance(), namedKey), PersistentDataType.STRING, input);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    private void mutateItemMeta(SkullMeta meta, String base64) {
        try {
            if (this.metaSetProfileMethod == null) {
                this.metaSetProfileMethod = meta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
                this.metaSetProfileMethod.setAccessible(true);
            }

            this.metaSetProfileMethod.invoke(meta, this.makeProfile(base64));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var6) {
            try {
                if (this.metaProfileField == null) {
                    this.metaProfileField = meta.getClass().getDeclaredField("profile");
                    this.metaProfileField.setAccessible(true);
                }

                this.metaProfileField.set(meta, this.makeProfile(base64));
            } catch (IllegalAccessException | NoSuchFieldException var5) {
                var5.printStackTrace();
            }
        }

    }

    private GameProfile makeProfile(String b64) {
        UUID uuid = new UUID(b64.substring(b64.length() - 20).hashCode(), b64.substring(b64.length() - 10).hashCode());
        GameProfile profile = new GameProfile(uuid, "aaaaa");
        profile.getProperties().put("textures", new Property("textures", b64));
        return profile;
    }

    public ItemStack build() {
        return this.itemStack;
    }


}
