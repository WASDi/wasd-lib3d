package com.wasd.lib3d.model;

import com.wasd.lib3d.misc.Maths;

public class Float2 {

    public final float x;
    public final float y;

    public Float2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float distanceFrom(Float2 other) {
        float dx = other.x - x;
        float dy = other.y - y;
        return Maths.sqrt(dx * dx + dy * dy);
    }

    public float length() {
        return Maths.sqrt(x * x + y * y);
    }
}
