package com.wasd.lib3d.misc;

import java.util.function.BiFunction;

public class Functions {

    public static final BiFunction<Float, Float, Float> x_times_z = (x, z) -> x * z;

    public static final BiFunction<Float, Float, Float> gaussian = (x, z) -> {
        double d = Math.sqrt(x * x + z * z);
        return (float) Math.exp(-d * d);
    };

}