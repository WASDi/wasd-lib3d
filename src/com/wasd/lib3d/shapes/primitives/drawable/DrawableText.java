package com.wasd.lib3d.shapes.primitives.drawable;

import com.wasd.lib3d.Settings;
import com.wasd.lib3d.model.Float2;

import java.awt.Color;

public class DrawableText extends Drawable {

    private final String text;
    private Float2 locationOnScreen;
    private float size;
    private Color color = Color.WHITE;

    public DrawableText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setLocationOnScreen(Float2 locationOnScreen) {
        this.locationOnScreen = locationOnScreen;
    }

    public Float2 getLocationOnScreen() {
        return locationOnScreen;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean shouldRender() {
        return locationOnScreen != null && size > .5f && getZDistanceFromCamera() < Settings.MAX_DISTANCE_TO_RENDER;
    }

}
