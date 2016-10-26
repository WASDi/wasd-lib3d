package com.wasd.lib3d.model;

import com.wasd.lib3d.misc.Maths;

public class Float3 {

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

}
