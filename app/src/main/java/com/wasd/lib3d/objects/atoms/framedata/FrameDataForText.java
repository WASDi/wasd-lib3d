package com.wasd.lib3d.objects.atoms.framedata;

import com.wasd.lib3d.Settings;
import com.wasd.lib3d.model.Float2;

import java.awt.Color;

public class FrameDataForText extends FrameData {

    private final String text;
    private Float2 locationOnScreen;
    private float fontSize;
    private Color color = Color.WHITE;

    public FrameDataForText(String text) {
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

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    public float getFontSize() {
        return fontSize;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean shouldRender() {
        return locationOnScreen != null && fontSize > .5f && getZDistanceFromCamera() < Settings.MAX_DISTANCE_TO_RENDER;
    }

}
