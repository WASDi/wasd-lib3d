package com.wasd.lib3d.misc;

import com.wasd.lib3d.Settings;

import java.awt.Font;
import java.util.logging.Logger;

public class FontCache {

    private static final Logger logger = Logger.getLogger(FontCache.class.getName());
    private static final Font[] cache = new Font[Settings.FONT_CACHE_SIZE];

    static {
        for (int i = 0; i < cache.length; i++) {
            cache[i] = createFont(i);
        }
    }

    public static Font get(int fontSize) {
        if (fontSize < cache.length) {
            return cache[fontSize];
        }
        logger.info("Cache miss: " + fontSize);
        return createFont(fontSize);
    }

    private static Font createFont(int fontSize) {
        return new Font(null, Font.PLAIN, fontSize);
    }

}