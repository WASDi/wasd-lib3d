package com.wasd.lib3d;

import com.wasd.lib3d.misc.Maths;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;

public class Camera {

    private float x;
    private float y;
    private float z;

    public Camera() {
        this(0f, 0f);
    }

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void relativeXYMovement(float dx, float dy) {
        x += dx;
        y += dy;
    }

    public void relativeZMovement(float dz) {
        z += dz;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float distanceFrom(Float3 point) {
        float dx = point.x - x;
        float dy = point.y - y;
        float dz = point.z - z;
        return Maths.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public Float2 getRotation() {
        return new Float2(0, 0);
    }
}