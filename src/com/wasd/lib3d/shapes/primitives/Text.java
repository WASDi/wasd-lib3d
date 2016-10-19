package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;
import com.wasd.lib3d.rendering.Layer;
import com.wasd.lib3d.rendering.Renderable;
import com.wasd.lib3d.rendering.Renderer;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableText;

public class Text implements PrimitiveShape<DrawableText>, Renderable {

    private static final float TEXT_SIZE_FACTOR = 3f;

    private final Float3 pos;

    private final DrawableText drawable;

    public Text(float x, float y, float z, String text) {
        this.pos = new Float3(x, y, z);
        drawable = new DrawableText(text);
    }

    public Float3 getPos() {
        return pos;
    }

    @Override
    public void update(Camera camera) {
        Float2 locationOnScreen = Settings.PROJECTION.locationOnScreen(camera, pos);
        drawable.setLocationOnScreen(locationOnScreen);

        if (!drawable.shouldRender()) {
            return;
        }

        drawable.setSize(TEXT_SIZE_FACTOR / camera.distanceFrom(pos));
        drawable.setZDistanceFromCamera(pos.z - camera.getZ());
    }

    @Override
    public DrawableText getDrawable() {
        return drawable;
    }

    @Override
    public void render(Renderer renderer, Layer layer) {
        //TODO refactor
        if (layer == Layer.TEXTS) {
            if (drawable.shouldRender()) {
                renderer.renderText(drawable);
            }
        }
    }
}
