package com.wasd.lib3d.shapes.primitives.framedata;

import com.wasd.lib3d.model.Float2;

import java.awt.Color;

public class FrameDataForLine extends FrameData {

    private Float2 startLocationOnScreen;
    private Float2 endLocationOnScreen;
    private Color color = Color.WHITE;

    public Float2 getStartLocationOnScreen() {
        return startLocationOnScreen;
    }

    public void setStartLocationOnScreen(Float2 startLocationOnScreen) {
        this.startLocationOnScreen = startLocationOnScreen;
    }

    public Float2 getEndLocationOnScreen() {
        return endLocationOnScreen;
    }

    public void setEndLocationOnScreen(Float2 endLocationOnScreen) {
        this.endLocationOnScreen = endLocationOnScreen;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean shouldRender() {
        return startLocationOnScreen != null && endLocationOnScreen != null;
    }
}
