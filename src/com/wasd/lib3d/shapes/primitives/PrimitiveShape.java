package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.rendering.Renderable;
import com.wasd.lib3d.shapes.primitives.framedata.FrameData;

public interface PrimitiveShape<FRAMEDATA extends FrameData> extends Renderable {

    void update(Camera camera);

    FRAMEDATA getFrameData();

}