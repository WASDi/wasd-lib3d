package com.wasd.lib3d;

import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;

public enum Projection {

    ORTHOGRAPHIC {
        @Override
        public Float2 locationOnScreen(Camera camera, Float3 pos) {
            return new Float2((pos.x - camera.getPosX()),
                    (pos.y - camera.getPosY()));
        }
    },
    WEAK_PERSPECTIVE {
        public static final float MIN_DISTANCE_FROM_CAMERA = .01f;

        @Override
        public Float2 locationOnScreen(Camera camera, Float3 pos) {
            if (pos.z < (camera.getPosZ() + MIN_DISTANCE_FROM_CAMERA)) {
                return null;
            }
            float factor = 1 / (pos.z - camera.getPosZ());

            float x = factor * (pos.x - camera.getPosX());
            float y = factor * (pos.y - camera.getPosY());

            return new Float2(x, y);
        }
    };

    /**
     * @return null iff not to render
     */
    public abstract Float2 locationOnScreen(Camera camera, Float3 pos);

}
