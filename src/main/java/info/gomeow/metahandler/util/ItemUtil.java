package info.gomeow.metahandler.util;

import java.util.Arrays;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {

    public static ItemStack setDisplayName(ItemStack is, String data) {
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(data);
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

}
