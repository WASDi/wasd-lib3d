package com.wasd.lib3d.model;

import com.wasd.lib3d.misc.Maths;

public class Float3 {

    public static final Float3 ZERO = new Float3(0, 0, 0);

    public final float x;
    public final float y;
    public final float z;

    public Float3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float distanceFrom(Float3 other) {
        float dx = other.x - x;
        float dy = other.y - y;
        float dz = other.z - z;
        return Maths.sqrt(dx * dx + dy * dy + dz * dz);
    }


    @Override
    public String toString() {
        return String.format("(%.2f, %.2f, %.2f)", x, y, z);
    }

    public Float2 getXY() {
        return new Float2(x, y);
    }

    public Float3 times(float factor) {
        return new Float3(x * factor, y * factor, z * factor);
    }

    public boolean isZero() {
        return x == 0 && y == 0 && z == 0;
    }
}
