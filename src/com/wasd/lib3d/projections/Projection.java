package com.wasd.lib3d.projections;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.model.Float3;

public interface Projection {

    /**
     * @return null if not to render
     */
    Float3 xyLocationOnScreenAndZDistanceFromCamera(Camera camera, Float3 pos);

}