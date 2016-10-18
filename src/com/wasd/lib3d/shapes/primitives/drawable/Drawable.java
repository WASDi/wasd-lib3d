package com.wasd.lib3d.shapes.primitives.drawable;

import java.awt.Color;

public abstract class Drawable {

    private float zDistanceFromCamera;

    public void setZDistanceFromCamera(float distance) {
        this.zDistanceFromCamera = distance;
    }

    public float getZDistanceFromCamera() {
        return zDistanceFromCamera;
    }

    public abstract Color getColor();

    public abstract boolean shouldRender();

}
