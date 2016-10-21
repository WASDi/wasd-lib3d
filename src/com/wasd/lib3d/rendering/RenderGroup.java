package com.wasd.lib3d.rendering;

import com.wasd.lib3d.Camera;

public interface RenderGroup {

    void update(Camera camera);

    void renderLineLayer(Renderer renderer);

    void renderDotLayer(Renderer renderer);

    void renderTextLayer(Renderer renderer);

}