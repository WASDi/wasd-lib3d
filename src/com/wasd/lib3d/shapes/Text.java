package com.wasd.lib3d.shapes;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.rendering.RenderGroup;
import com.wasd.lib3d.rendering.Renderer;
import com.wasd.lib3d.shapes.primitives.PrimitiveText;

public class Text implements RenderGroup {

    private final PrimitiveText primitiveText;

    public Text(float x, float y, float z, String text) {
        primitiveText = new PrimitiveText(x, y, z, text);
    }

    @Override
    public void update(Camera camera) {
        primitiveText.update(camera);
    }

    @Override
    public void renderLineLayer(Renderer renderer) {
    }

    @Override
    public void renderDotLayer(Renderer renderer) {
    }

    @Override
    public void renderTextLayer(Renderer renderer) {
        primitiveText.render(renderer);
    }
}
