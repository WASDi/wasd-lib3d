package com.wasd.lib3d.shapes;

import com.wasd.lib3d.shapes.primitives.Dot;

public class Sphere extends Shape {

    private static final int RESOLUTION_X = 10;
    private static final int RESOLUTION_Y = 8;

    private static final float PI = (float) Math.PI;
    private static final float TWO_PI = (float) Math.PI * 2f;

    public Sphere(float x, float y, float z, float size) {
        super(2 + RESOLUTION_X * RESOLUTION_Y, 1000);
        initDots(x, y, z, size);
        initLinesBetweenDots();
    }

    private void initDots(float x, float y, float z, float size) {
        float halfSize = size / 2;
        Dot topDot = new Dot(x, y - halfSize, z);
        dots.add(topDot);

        for (int ring = 0; ring < RESOLUTION_Y; ring++) {
            float ringY = topDot.getY() + size * (ring + 1f) / (RESOLUTION_Y + 1);
            float ringRadius = (float) Math.sin(PI * (ring + 1f) / (RESOLUTION_Y + 1));
            for (int pointInRing = 0; pointInRing < RESOLUTION_X; pointInRing++) {
                float dotX = x + ringRadius * halfSize * (float) Math.cos(TWO_PI * pointInRing / RESOLUTION_X);
                float dotZ = z + ringRadius * halfSize * (float) Math.sin(TWO_PI * pointInRing / RESOLUTION_X);
                Dot dotInRing = new Dot(dotX, ringY, dotZ);
                dots.add(dotInRing);
            }
        }

        Dot bottomDot = new Dot(x, y + halfSize, z);
        dots.add(bottomDot);

        dots.forEach(dot -> dotsAsDrawable.add(dot.getDrawable()));
    }

    private void initLinesBetweenDots() {
        lines.forEach(dot -> linesAsDrawable.add(dot.getDrawable()));
    }
}
