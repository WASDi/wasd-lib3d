package com.wasd.lib3d.projections;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;

public class Orthographic implements Projection {
    @Override
    public Float2 locationOnScreen(Camera camera, Float3 pos) {
        return new Float2((pos.x - camera.getX()),
                (pos.y - camera.getY()));
    }
}
