package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;

public interface PrimitiveShape<DRAWABLE> {

    void updateDrawable(Camera camera);

    DRAWABLE getDrawable();

}