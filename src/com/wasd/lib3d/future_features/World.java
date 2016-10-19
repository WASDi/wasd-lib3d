package com.wasd.lib3d.future_features;

import com.wasd.lib3d.rendering.Layer;
import com.wasd.lib3d.rendering.Renderable;
import com.wasd.lib3d.rendering.Renderer;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final List<Renderable> objects = new ArrayList<>();

    public World() {
    }

    public void render(Renderer renderer) {
        renderLayer(renderer, Layer.LINES);
        renderLayer(renderer, Layer.DOTS);
        renderLayer(renderer, Layer.TEXTS);
    }

    private void renderLayer(Renderer renderer, Layer layer) {
        for (Renderable object : objects) {
            object.render(renderer, layer);
        }
    }

}
