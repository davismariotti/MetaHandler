package info.gomeow.metahandler.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {
    
    private static HashMap<String, Enchantment> enchants = new HashMap<String, Enchantment>();
    
    static {
        enchants.put("SHARPNESS", Enchantment.DAMAGE_ALL);
        enchants.put("POWER", Enchantment.ARROW_DAMAGE);
        enchants.put("FIRE_PROTECTION", Enchantment.PROTECTION_FIRE);
        enchants.put("FEATHER_FALLING", Enchantment.PROTECTION_FALL);
        enchants.put("PROTECTION", Enchantment.PROTECTION_ENVIRONMENTAL);
        enchants.put("BLAST_PROTECTION", Enchantment.PROTECTION_EXPLOSIONS);
        enchants.put("PROJECTILE_PROTECTION", Enchantment.PROTECTION_PROJECTILE);
        enchants.put("OXYGEN", Enchantment.OXYGEN);
        enchants.put("INFINITY", Enchantment.ARROW_INFINITE);
        enchants.put("AQUA_AFFINITY", Enchantment.WATER_WORKER);
        enchants.put("UNBREAKING", Enchantment.DURABILITY);
        enchants.put("SMITE", Enchantment.DAMAGE_UNDEAD);
        enchants.put("BANE_OF_ANTHROPODS", Enchantment.DAMAGE_ARTHROPODS);
        enchants.put("EFFICIENCY", Enchantment.DIG_SPEED);
        enchants.put("FIRE_ASPECT", Enchantment.FIRE_ASPECT);
        enchants.put("SILK_TOUCH", Enchantment.SILK_TOUCH);
        enchants.put("FORTUNE", Enchantment.LOOT_BONUS_BLOCKS);
        enchants.put("LOOTING", Enchantment.LOOT_BONUS_MOBS);
        enchants.put("PUNCH", Enchantment.ARROW_KNOCKBACK);
        enchants.put("FLAME", Enchantment.ARROW_FIRE);
    }

    public static ItemStack setDisplayName(ItemStack is, String data) {
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(data);
        is.setItemMeta(im);
        return is;
    }
    
    public static ItemStack clearDisplayName(ItemStack is) {
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(null);
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack setLore(ItemStack is, int line, String data) {
        ItemMeta im = is.getItemMeta();
        if(im.hasLore()) {
            List<String> lore = im.getLore();
            while(lore.size() < line) {
                lore.add(" ");
            }
            lore.set(line - 1, data);
            im.setLore(lore);
        } else {
            String[] loreArray = new String[line];
            loreArray[line - 1] = data;
            im.setLore(Arrays.asList(loreArray));
        }
        is.setItemMeta(im);
        return is;
    }
    
    public static ItemStack clearLore(ItemStack is) {
        ItemMeta im = is.getItemMeta();
        im.setLore(null);
        is.setItemMeta(im);
        return is;
    }
    
    public static ItemStack clearMeta(ItemStack is) {
        is = clearEnchantments(is);
        is = clearLore(is);
        is = clearDisplayName(is);
        return is;
    }
    
    public static Enchantment getEnchantment(String ench) {
        ench = ench.toUpperCase().replace('-', '_');
        Enchantment e = Enchantment.getByName(ench);
        if(e == null) {
            if(enchants.containsKey(ench)) {
                e = enchants.get(ench);
            } else {
                if(ench.contains("_")) {
                    e = getEnchantment(ench.replace("_", ""));
                }
                if(e == null) {
                    return null;
                }
            }
        }
        return e;
    }
    
    public static List<String> getEnchantments() {
        List<String> list = new ArrayList<String>();
        for(String key:enchants.keySet()) {
            list.add(key.toLowerCase().replace('_', '-'));
        }
        return list;
    }
    
    public static ItemStack clearEnchantments(ItemStack is) {
        for(Enchantment ench:is.getEnchantments().keySet()) {
            is.removeEnchantment(ench);
        }
        return is;
    }

}
