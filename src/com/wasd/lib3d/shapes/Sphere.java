package com.wasd.lib3d.shapes;

import com.wasd.lib3d.shapes.primitives.Dot;
import com.wasd.lib3d.shapes.primitives.Line;

public class Sphere extends Shape {

    private static final int RESOLUTION_X = 20;
    private static final int RESOLUTION_Y = 18;

    private static final float PI = (float) Math.PI;
    private static final float TWO_PI = (float) Math.PI * 2f;

    public Sphere(float x, float y, float z, float size) {
        super(2 + RESOLUTION_X * RESOLUTION_Y,
                RESOLUTION_X * RESOLUTION_Y + RESOLUTION_X * (RESOLUTION_Y + 1));
        initDots(x, y, z, size);
        initLinesBetweenDots();
    }

    private void initDots(float x, float y, float z, float size) {
        float halfSize = size / 2;

        Dot topDot = new Dot(x, y - halfSize, z);
        Dot bottomDot = new Dot(x, y + halfSize, z);
        dots.add(topDot);
        dots.add(bottomDot);

        for (int ring = 0; ring < RESOLUTION_Y; ring++) {
            float ringLinearIndexTimesPi = PI * (ring + 1f) / (RESOLUTION_Y + 1);
            float ringY = y - halfSize * (float) Math.cos(ringLinearIndexTimesPi);
            float ringRadius = (float) Math.sin(ringLinearIndexTimesPi);
            for (int pointInRing = 0; pointInRing < RESOLUTION_X; pointInRing++) {
                float dotX = x + ringRadius * halfSize * (float) Math.cos(TWO_PI * pointInRing / RESOLUTION_X);
                float dotZ = z + ringRadius * halfSize * (float) Math.sin(TWO_PI * pointInRing / RESOLUTION_X);
                Dot dotInRing = new Dot(dotX, ringY, dotZ);
                dots.add(dotInRing);
            }
        }


        dots.forEach(dot -> dotsAsDrawable.add(dot.getDrawable()));
    }

    private void initLinesBetweenDots() {
        initLinesToTopAndBottom();
        initLinesBetweenRings();
        initLinesWithinRings();

        lines.forEach(dot -> linesAsDrawable.add(dot.getDrawable()));
    }

    private void initLinesToTopAndBottom() {
        Dot topDot = dots.get(0);
        Dot bottomDot = dots.get(1);
        for (int i = 0; i < RESOLUTION_X; i++) {
            lines.add(new Line(topDot, dots.get(2 + i)));
            lines.add(new Line(bottomDot, dots.get(RESOLUTION_X * RESOLUTION_Y + i - RESOLUTION_X + 2)));
        }
    }

    private void initLinesBetweenRings() {
        for (int ring = 0; ring < RESOLUTION_Y - 1; ring++) {
            int ringIndex = 2 + ring * RESOLUTION_X;
            for (int pointIndex = 0; pointIndex < RESOLUTION_X; pointIndex++) {
                lines.add(new Line(dots.get(ringIndex + pointIndex),
                        dots.get(ringIndex + pointIndex + RESOLUTION_X)));
            }
        }
    }

    private void initLinesWithinRings() {
        for (int ring = 0; ring < RESOLUTION_Y; ring++) {
            int ringIndex = 2 + ring * RESOLUTION_X;
            for (int pointIndex = 0; pointIndex < RESOLUTION_X; pointIndex++) {
                lines.add(new Line(dots.get(ringIndex + pointIndex),
                        dots.get(ringIndex + (pointIndex + 1) % RESOLUTION_X)));
            }
        }
    }
}
