package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableDot;

public class Dot implements PrimitiveShape<DrawableDot> {

    private static final float DOT_SIZE_FACTOR = 3f;

    private final Float3 pos;

    private final DrawableDot drawable = new DrawableDot();

    public Dot(float x, float y, float z) {
        pos = new Float3(x, y, z);
    }

    public Float3 getPos() {
        return pos;
    }

    @Override
    public void update(Camera camera) {
        Float2 locationOnScreen = Settings.PROJECTION.locationOnScreen(camera, pos);

        drawable.setLocationOnScreen(locationOnScreen);
        if (locationOnScreen == null) {
            return;
        }

        drawable.setSize(DOT_SIZE_FACTOR / camera.distanceFrom(pos));
        drawable.setZDistanceFromCamera(pos.z - camera.getZ());
    }

    @Override
    public DrawableDot getDrawable() {
        return drawable;
    }

}
