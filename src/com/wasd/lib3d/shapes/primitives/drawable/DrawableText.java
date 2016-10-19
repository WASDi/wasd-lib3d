package com.wasd.lib3d.shapes.primitives.drawable;

import com.wasd.lib3d.Settings;

public class DrawableText extends DrawableDot {

    private final String text;

    public DrawableText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean shouldRender() {
        return getLocationOnScreen() != null && getSize() > .5f && getZDistanceFromCamera() < Settings.MAX_DISTANCE_TO_RENDER;
    }

}
