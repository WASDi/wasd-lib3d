package com.wasd.lib3d;

public class Camera {

    private float posX;
    private float posY;
    private float posZ;

    public Camera() {
        this(0f, 0f);
    }

    public Camera(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void relativeXYMovement(float dx, float dy) {
        posX += dx;
        posY += dy;
    }

    public void relativeZMovement(float dz) {
        posZ += dz;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getPosZ() {
        return posZ;
    }

}