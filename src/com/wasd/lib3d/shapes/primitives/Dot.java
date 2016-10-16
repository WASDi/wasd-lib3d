package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableDot;

public class Dot {

    public static final float DOT_SIZE_FACTOR = 10f;
    private float x;
    private float y;
    private float z;

    private DrawableDot drawable = new DrawableDot();

    public Dot(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void update(Camera camera) {
        float factorStepAwayFromCenter = 1 / (z - camera.getPosZ());

        drawable.updateX(camera.getPosX() * factorStepAwayFromCenter + x * factorStepAwayFromCenter);
        drawable.updateY(camera.getPosY() * factorStepAwayFromCenter + y * factorStepAwayFromCenter);
        drawable.updateSize(DOT_SIZE_FACTOR * factorStepAwayFromCenter);
    }

    public DrawableDot getDrawable() {
        return drawable;
    }
}
