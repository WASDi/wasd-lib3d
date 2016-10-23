package com.wasd.lib3d.projections;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;

public class WeakPerspective implements Projection {

    private static final float MIN_Z_DISTANCE_FROM_CAMERA = .01f;

    @Override
    public Float2 locationOnScreen(Camera camera, Float3 pos) {
        Float3 delta = delta(camera, pos);
        if (delta.z < MIN_Z_DISTANCE_FROM_CAMERA) {
            return null;
        }
        float factor = 1 / (delta.z);

        float x = factor * (delta.x);
        float y = factor * (delta.y);

        return new Float2(x, y);
    }

    private Float3 delta(Camera camera, Float3 pos) {
        //TODO camera rotation
        return new Float3(pos.x - camera.getX(),
                pos.y - camera.getY(),
                pos.z - camera.getZ());
    }
}
