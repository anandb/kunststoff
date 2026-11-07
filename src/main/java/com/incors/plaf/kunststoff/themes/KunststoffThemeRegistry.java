package com.incors.plaf.kunststoff.themes;

import java.util.LinkedHashMap;
import java.util.Map;

import com.incors.plaf.kunststoff.KunststoffTheme;

/**
 * Registry that maps theme names to KunststoffTheme instances. Theme names
 * are case-insensitive. Use the KUNSTSTOFF_THEME environment variable to
 * select a theme at runtime. If the variable is unset or unknown, the look and
 * feel falls back to "brick", the original Kunststoff brown theme.
 *
 * Available themes:
 *   default    – gray-blue (KunststoffTheme base colors)
 *   brick      – warm brown (original Kunststoff brown)
 *   aqua       – teal/aqua
 *   burgundy   – muted wine red
 *   creme      – warm cream/tan
 *   forest     – deep cool green
 *   indigo     – deep blue-violet
 *   mint       – light green-teal
 *   olive      – olive green
 *   purple     – purple/violet
 *   sepia      – sepia/brown-yellow
 *   slate      – neutral cool gray
 *   steelblue  – blue-gray
 *   terracotta – warm clay orange
 */
public final class KunststoffThemeRegistry {

    private static final Map<String, KunststoffTheme> THEMES = new LinkedHashMap<>();

    static {
        register(new KunststoffDefaultTheme(), "default");
        register(new KunststoffTheme(), "brick");          // original brown
        register(new KunststoffAquaTheme(), "aqua");
        register(new KunststoffBurgundyTheme(), "burgundy");
        register(new KunststoffCremeTheme(), "creme");
        register(new KunststoffForestTheme(), "forest");
        register(new KunststoffIndigoTheme(), "indigo");
        register(new KunststoffMintTheme(), "mint");
        register(new KunststoffOliveTheme(), "olive");
        register(new KunststoffPurpleTheme(), "purple");
        register(new KunststoffSepiaTheme(), "sepia");
        register(new KunststoffSlateTheme(), "slate");
        register(new KunststoffSteelBlueTheme(), "steelblue");
        register(new KunststoffTerracottaTheme(), "terracotta");
    }

    private KunststoffThemeRegistry() {}

    private static void register(KunststoffTheme theme, String name) {
        THEMES.put(name.toLowerCase(), theme);
    }

    /**
     * Returns the theme for the given name, or null if not found.
     * Matching is case-insensitive.
     */
    public static KunststoffTheme getTheme(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        return THEMES.get(name.trim().toLowerCase());
    }

    /**
     * Returns the list of available theme names for display.
     */
    public static String[] getAvailableThemeNames() {
        return THEMES.keySet().toArray(String[]::new);
    }
}
