package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.model.Float3;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableDot;

public class Dot implements PrimitiveShape<DrawableDot> {

    private static final float DOT_SIZE_FACTOR = 3f;

    private final Float3 pos;

    private DrawableDot drawable = new DrawableDot();

    public Dot(float x, float y, float z) {
        pos = new Float3(x, y, z);
    }

    public Float3 getPos() {
        return pos;
    }

    @Override
    public void updateDrawable(Camera camera) {
        if (pos.getZ() < (camera.getPosZ() + MIN_DISTANCE_FROM_CAMERA)) {
            drawable.setShouldRender(true);
            return;
        }
        drawable.setShouldRender(false);

        float factor = 1 / (pos.getZ() - camera.getPosZ());
//        float factor = 2 / (distanceFrom(camera));

        drawable.updateX(factor * (pos.getX() - camera.getPosX()));
        drawable.updateY(factor * (pos.getY() - camera.getPosY()));
        drawable.updateSize(DOT_SIZE_FACTOR * factor);

        drawable.updateZDistanceFromCamera(pos.getZ() - camera.getPosZ());
    }

    @Override
    public DrawableDot getDrawable() {
        return drawable;
    }

    public float distanceFrom(Camera camera) {
        float dx = camera.getPosX() - pos.getX();
        float dy = camera.getPosY() - pos.getY();
        float dz = camera.getPosZ() - pos.getZ();
        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}
