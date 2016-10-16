package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableDot;

public class Dot implements PrimitiveShape<DrawableDot> {

    private static final float DOT_SIZE_FACTOR = 3f;

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
            drawable.setShouldRender(true);
            return;
        }
        drawable.setShouldRender(false);

        float factor = 1 / (z - camera.getPosZ());

        drawable.updateX(factor * (x - camera.getPosX()));
        drawable.updateY(factor * (y - camera.getPosY()));
        drawable.updateSize(DOT_SIZE_FACTOR * factor);

        drawable.updateZDistanceFromCamera(z - camera.getPosZ());
    }

    @Override
    public DrawableDot getDrawable() {
        return drawable;
    }
}
