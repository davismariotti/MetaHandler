package info.gomeow.metahandler.util;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Color;

public class LeatherUtil {

    private static HashMap<String, Color> colors = new HashMap<String, Color>();

    static {
        colors.put("AQUA", Color.AQUA);
        colors.put("BLACK", Color.BLACK);
        colors.put("BLUE", Color.BLUE);
        colors.put("FUCHSIA", Color.FUCHSIA);
        colors.put("GRAY", Color.GRAY);
        colors.put("GREEN", Color.GREEN);
        colors.put("LIME", Color.LIME);
        colors.put("MAROON", Color.MAROON);
        colors.put("NAVY", Color.NAVY);
        colors.put("OLIVE", Color.OLIVE);
        colors.put("ORANGE", Color.ORANGE);
        colors.put("PURPLE", Color.PURPLE);
        colors.put("RED", Color.RED);
        colors.put("SILVER", Color.SILVER);
        colors.put("TEAL", Color.TEAL);
        colors.put("WHITE", Color.WHITE);
        colors.put("YELLOW", Color.YELLOW);
    }

    public static Color toColor(String s) {
        if(colors.containsKey(s.toUpperCase())) {
            return colors.get(s.toUpperCase());
        }
        return null;
    }

    public static Set<String> getColors() {
        return colors.keySet();
    }
}
