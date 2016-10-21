package com.wasd.lib3d.objects.atoms.framedata;

import java.awt.Color;

public abstract class FrameData {

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
