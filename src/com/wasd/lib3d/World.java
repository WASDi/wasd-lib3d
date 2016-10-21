package com.wasd.lib3d;

import com.wasd.lib3d.rendering.RenderGroup;
import com.wasd.lib3d.rendering.Renderer;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final List<RenderGroup> renderableObjects = new ArrayList<>();

    public void add(RenderGroup object) {
        renderableObjects.add(object);
    }

    public void render(Renderer renderer, Camera camera) {
        renderableObjects.forEach(object -> object.update(camera));
        renderableObjects.forEach(object -> object.renderLineLayer(renderer));
        renderableObjects.forEach(object -> object.renderDotLayer(renderer));
        renderableObjects.forEach(object -> object.renderTextLayer(renderer));
    }

}
