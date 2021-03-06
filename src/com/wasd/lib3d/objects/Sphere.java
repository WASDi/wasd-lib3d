package com.wasd.lib3d.objects;

import com.wasd.lib3d.misc.Maths;
import com.wasd.lib3d.objects.atoms.Dot;
import com.wasd.lib3d.objects.atoms.Line;

public class Sphere extends AbstractShape {

    private static final int RESOLUTION_X = 20;
    private static final int RESOLUTION_Y = 18;


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
            float ringLinearIndexTimesPi = Maths.PI * (ring + 1f) / (RESOLUTION_Y + 1);
            float ringY = y - halfSize * Maths.cos(ringLinearIndexTimesPi);
            float ringRadius = Maths.sin(ringLinearIndexTimesPi);
            for (int pointInRing = 0; pointInRing < RESOLUTION_X; pointInRing++) {
                float dotX = x + ringRadius * halfSize * Maths.cos(Maths.TWO_PI * pointInRing / RESOLUTION_X);
                float dotZ = z + ringRadius * halfSize * Maths.sin(Maths.TWO_PI * pointInRing / RESOLUTION_X);
                Dot dotInRing = new Dot(dotX, ringY, dotZ);
                dots.add(dotInRing);
            }
        }
    }

    private void initLinesBetweenDots() {
        initLinesToTopAndBottom();
        initLinesBetweenRings();
        initLinesWithinRings();
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
