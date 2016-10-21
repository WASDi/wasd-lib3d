package com.wasd.lib3d.rendering;

import com.wasd.lib3d.Camera;

public interface Renderable {

    void update(Camera camera);

    void render(Renderer renderer);

}
