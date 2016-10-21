package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;
import com.wasd.lib3d.rendering.Renderer;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableText;

public class PrimitiveText implements PrimitiveShape<DrawableText> {

    private static final float TEXT_SIZE_FACTOR = 3f;

    private final Float3 pos;

    private final DrawableText drawable;

    public PrimitiveText(float x, float y, float z, String text) {
        this.pos = new Float3(x, y, z);
        drawable = new DrawableText(text);
    }

    @Override
    public void update(Camera camera) {
        Float2 locationOnScreen = Settings.PROJECTION.locationOnScreen(camera, pos);
        drawable.setLocationOnScreen(locationOnScreen);

        if (locationOnScreen == null) {
            return;
        }

        drawable.setZDistanceFromCamera(pos.z - camera.getZ());
        drawable.setSize(TEXT_SIZE_FACTOR / drawable.getZDistanceFromCamera());
    }

    @Override
    public DrawableText getDrawable() {
        return drawable;
    }

    @Override
    public void render(Renderer renderer) {
        if (drawable.shouldRender()) {
            renderer.renderText(drawable);
        }
    }
}
