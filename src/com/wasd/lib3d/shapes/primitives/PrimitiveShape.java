package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.rendering.Renderable;
import com.wasd.lib3d.shapes.primitives.drawable.Drawable;

public interface PrimitiveShape<DRAWABLE extends Drawable> extends Renderable {

    void update(Camera camera);

    DRAWABLE getDrawable();

}