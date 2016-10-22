package com.wasd.lib3d;

import com.wasd.lib3d.projections.Projection;
import com.wasd.lib3d.projections.WeakPerspective;

import java.awt.Color;
import java.awt.Dimension;

public class Settings {

    public static final Dimension WINDOW_SIZE = new Dimension(1280, 720);
    public static final Color BACKGROUND_COLOR = new Color(0f, .15f, .3f);

    public static final Projection PROJECTION = new WeakPerspective();

    public static final float RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR = 500;

    public static final float FOG_START_DISTANCE = .5f;
    public static final float MAX_DISTANCE_TO_RENDER = 4f;

    public static final int FONT_CACHE_SIZE = 100;
}
