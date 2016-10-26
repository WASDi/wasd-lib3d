package com.wasd.lib3d.misc;

public class Maths {

    public static final float PI = (float) Math.PI;
    public static final float TWO_PI = (float) (Math.PI * 2);
    public static final float HALF_PI = (float) (Math.PI / 2);

    public static float sqrt(float x) {
        return (float) Math.sqrt(x);
    }

    public static float exp(double x) {
        return (float) Math.exp(x);
    }

    public static float sin(double x) {
        return (float) Math.sin(x);
    }

    public static float cos(double x) {
        return (float) Math.cos(x);
    }

    public static float atan(float x) {
        return (float) Math.atan(x);
    }

    public static float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }
}
