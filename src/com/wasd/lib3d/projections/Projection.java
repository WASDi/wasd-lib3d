package com.wasd.lib3d.projections;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;

public interface Projection {

    /**
     * @return null if not to render
     */
    Float2 locationOnScreen(Camera camera, Float3 pos);

}