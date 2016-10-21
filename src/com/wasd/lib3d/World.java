package com.wasd.lib3d;

import com.wasd.lib3d.rendering.RenderGroup;
import com.wasd.lib3d.rendering.Renderer;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final List<RenderGroup> objects = new ArrayList<>();

    public void add(RenderGroup object) {
        objects.add(object);
    }

    public void render(Renderer renderer, Camera camera) {
        objects.forEach(object -> object.update(camera));
        objects.forEach(object -> object.renderLineLayer(renderer));
        objects.forEach(object -> object.renderDotLayer(renderer));
        objects.forEach(object -> object.renderTextLayer(renderer));
    }

}
