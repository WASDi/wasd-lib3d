package com.wasd.lib3d.model;

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
        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

}
