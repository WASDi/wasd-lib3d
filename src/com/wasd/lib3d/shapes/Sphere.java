package com.wasd.lib3d.shapes;

import com.wasd.lib3d.shapes.primitives.Dot;

public class Sphere extends Shape {

    private static final int RESOLUTION_X = 10;
    private static final int RESOLUTION_Y = 8;

    public Sphere(float x, float y, float z, float size) {
        super(2 + RESOLUTION_X * RESOLUTION_Y, 1000);
        initDots(x, y, z, size);
        initLinesBetweenDots();
    }

    private void initDots(float x, float y, float z, float size) {
        Dot topDot = new Dot(x, y - size / 2, z);
        Dot bottomDot = new Dot(x, y + size / 2, z);

        dots.add(topDot);
        dots.add(bottomDot);

        dots.forEach(dot -> dotsAsDrawable.add(dot.getDrawable()));
    }

    private void initLinesBetweenDots() {
        lines.forEach(dot -> linesAsDrawable.add(dot.getDrawable()));
    }
}
