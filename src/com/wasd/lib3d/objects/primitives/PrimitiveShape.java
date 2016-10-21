package com.wasd.lib3d.objects.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.objects.primitives.framedata.FrameData;
import com.wasd.lib3d.rendering.Renderable;

public interface PrimitiveShape<FRAMEDATA extends FrameData> extends Renderable {

    void update(Camera camera);

    FRAMEDATA getFrameData();

}