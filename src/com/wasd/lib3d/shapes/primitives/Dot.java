package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableDot;

public class Dot implements PrimitiveShape<DrawableDot> {

    private static final float DOT_SIZE_FACTOR = 5f;

    private float x;
    private float y;
    private float z;

    private DrawableDot drawable = new DrawableDot();

    public Dot(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    @Override
    public void updateDrawable(Camera camera) {
        if (z < (camera.getPosZ() + MIN_DISTANCE_FROM_CAMERA)) {
            drawable.setOutsideScreen(true);
            return;
        }
        drawable.setOutsideScreen(false);

        float factorStepAwayFromCenter = 1 / (z - camera.getPosZ());

        drawable.updateX(camera.getPosX() * factorStepAwayFromCenter + x * factorStepAwayFromCenter);
        drawable.updateY(camera.getPosY() * factorStepAwayFromCenter + y * factorStepAwayFromCenter);
        drawable.updateSize(DOT_SIZE_FACTOR * factorStepAwayFromCenter);
    }

    @Override
    public DrawableDot getDrawable() {
        return drawable;
    }
}
