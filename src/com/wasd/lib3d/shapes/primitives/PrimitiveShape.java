package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.shapes.primitives.drawable.Drawable;

public interface PrimitiveShape<DRAWABLE extends Drawable> {

    void updateDrawable(Camera camera);

    DRAWABLE getDrawable();

}