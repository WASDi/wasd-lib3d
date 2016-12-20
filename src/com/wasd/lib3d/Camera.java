package com.wasd.lib3d;

import com.wasd.lib3d.misc.Maths;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;

public class Camera {

    private static final float MIN_ROT_X = -Maths.HALF_PI + .01f;
    private static final float MAX_ROT_X = Maths.HALF_PI - .01f;

    private float x;
    private float y;
    private float z;

    private float rotX;
    private float rotZ;

    public Camera() {
        this(0f, 0f);
    }

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void relativeMovement(float dx, float dy, float dz) {
        x += dx;
        y += dy;
        z += dz;
    }

    public void relativeRotation(float dx, float dy) {
        rotX += dx;
        rotZ += dy;
        rotX = Maths.clamp(rotX, MIN_ROT_X, MAX_ROT_X);
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
        return new Float2(rotX, rotZ);
    }

    public void relativeMovement(Float3 delta) {
        relativeMovement(delta.x, delta.y, delta.z);
    }
}