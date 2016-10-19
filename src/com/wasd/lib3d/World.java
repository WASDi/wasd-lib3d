package com.wasd.lib3d;

import com.wasd.lib3d.rendering.Layer;
import com.wasd.lib3d.rendering.Renderable;
import com.wasd.lib3d.rendering.Renderer;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final List<Renderable> renderableObjects = new ArrayList<>();

    public void add(Renderable object) {
        renderableObjects.add(object);
    }

    public void render(Renderer renderer, Camera camera) {
        renderableObjects.forEach(renderable -> renderable.update(camera));
        renderLayer(renderer, Layer.LINES);
        renderLayer(renderer, Layer.DOTS);
        renderLayer(renderer, Layer.TEXTS);
    }

    private void renderLayer(Renderer renderer, Layer layer) {
        for (Renderable object : renderableObjects) {
            object.render(renderer, layer);
        }
    }

}
