package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.shapes.primitives.drawable.Drawable;

public interface PrimitiveShape<DRAWABLE extends Drawable> {

    float MIN_DISTANCE_FROM_CAMERA = .01f;

    void updateDrawable(Camera camera);

    DRAWABLE getDrawable();

}