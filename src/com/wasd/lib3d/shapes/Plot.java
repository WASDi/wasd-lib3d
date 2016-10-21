package com.wasd.lib3d.shapes;

import com.wasd.lib3d.shapes.primitives.Dot;
import com.wasd.lib3d.shapes.primitives.Line;

import java.util.function.BiFunction;

public class Plot extends BasicShape {

    public static final int RESOLUTION = 30;
    public static final float RANGE = 2f;

    private final BiFunction<Float, Float, Float> function;

    public Plot(float x, float y, float z, float size, BiFunction<Float, Float, Float> function) {
        super(RESOLUTION * RESOLUTION,
                RESOLUTION * (RESOLUTION - 1) + RESOLUTION * (RESOLUTION - 1));
        this.function = function;
        initDots(x, y, z, size);
        initLinesBetweenDots();
    }

    private void initDots(float x, float y, float z, float size) {
        float halfSize = size / 2;

        for (int xPointIndex = 0; xPointIndex < RESOLUTION; xPointIndex++) {
            for (int zPointIndex = 0; zPointIndex < RESOLUTION; zPointIndex++) {
                float xInput = RANGE * (1f - (2f * xPointIndex) / (RESOLUTION - 1));
                float zInput = RANGE * (1f - (2f * zPointIndex) / (RESOLUTION - 1));
                float yValue = function.apply(xInput, zInput);

                float dotX = x + halfSize * xInput;
                float dotZ = z + halfSize * zInput;
                dots.add(new Dot(dotX, y - yValue * halfSize, dotZ));
            }
        }
    }

    private void initLinesBetweenDots() {
        initXLines();
        initZLines();
    }

    private void initXLines() {
        for (int x = 0; x < RESOLUTION - 1; x++) {
            int xIndex = x * RESOLUTION;
            for (int z = 0; z < RESOLUTION; z++) {
                lines.add(new Line(dots.get(xIndex + z),
                        dots.get(xIndex + z + RESOLUTION)));
            }
        }
    }

    private void initZLines() {
        for (int x = 0; x < RESOLUTION; x++) {
            int xIndex = x * RESOLUTION;
            for (int z = 0; z < RESOLUTION - 1; z++) {
                lines.add(new Line(dots.get(xIndex + z),
                        dots.get(xIndex + z + 1)));
            }
        }
    }
}
