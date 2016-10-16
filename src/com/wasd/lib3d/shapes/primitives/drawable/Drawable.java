package com.wasd.lib3d.shapes.primitives.drawable;

import java.awt.Color;

public abstract class Drawable {

    private boolean shouldRender;
    private float zDistanceFromCamera;

    public boolean shouldRender() {
        return shouldRender;
    }

    public void setShouldRender(boolean shouldRender) {
        this.shouldRender = shouldRender;
    }

    public void updateZDistanceFromCamera(float distance) {
        this.zDistanceFromCamera = distance;
    }

    public float getZDistanceFromCamera() {
        return zDistanceFromCamera;
    }

    public abstract Color getColor();

}
