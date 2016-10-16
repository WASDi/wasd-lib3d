package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;

public interface PrimitiveShape<DRAWABLE> {

    float MIN_DISTANCE_FROM_CAMERA = .01f;

    void updateDrawable(Camera camera);

    DRAWABLE getDrawable();

}