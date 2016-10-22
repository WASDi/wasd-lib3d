package com.wasd.lib3d.projections;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;

public class WeakPerspective implements Projection {

    private static final float MIN_DISTANCE_FROM_CAMERA = .01f;

    @Override
    public Float2 locationOnScreen(Camera camera, Float3 pos) {
        if (pos.z < (camera.getZ() + MIN_DISTANCE_FROM_CAMERA)) {
            return null;
        }
        float factor = 1 / (pos.z - camera.getZ());

        float x = factor * (pos.x - camera.getX());
        float y = factor * (pos.y - camera.getY());

        return new Float2(x, y);
    }
}
