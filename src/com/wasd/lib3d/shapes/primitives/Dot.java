package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.Float3;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableDot;

public class Dot implements PrimitiveShape<DrawableDot> {

    private static final float DOT_SIZE_FACTOR = 3f;

    private final Float3 pos;

    private DrawableDot drawable = new DrawableDot();

    public Dot(float x, float y, float z) {
        pos = new Float3(x, y, z);
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public float getZ() {
        return pos.z;
    }

    @Override
    public void updateDrawable(Camera camera) {
        if (pos.z < (camera.getPosZ() + MIN_DISTANCE_FROM_CAMERA)) {
            drawable.setShouldRender(true);
            return;
        }
        drawable.setShouldRender(false);

        float factor = 1 / (pos.z - camera.getPosZ());
//        float factor = 2 / (distanceFrom(camera));

        drawable.updateX(factor * (pos.x - camera.getPosX()));
        drawable.updateY(factor * (pos.y - camera.getPosY()));
        drawable.updateSize(DOT_SIZE_FACTOR * factor);

        drawable.updateZDistanceFromCamera(pos.z - camera.getPosZ());
    }

    @Override
    public DrawableDot getDrawable() {
        return drawable;
    }

    public float distanceFrom(Camera camera) {
        float dx = camera.getPosX() - pos.x;
        float dy = camera.getPosY() - pos.y;
        float dz = camera.getPosZ() - pos.z;
        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}
