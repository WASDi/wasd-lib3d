package com.wasd.lib3d.objects;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.rendering.Renderer;

public interface Object3D {

    void update(Camera camera);

    void renderLineLayer(Renderer renderer);

    void renderDotLayer(Renderer renderer);

    void renderTextLayer(Renderer renderer);

}