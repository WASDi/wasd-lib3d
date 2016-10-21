package com.wasd.lib3d.objects.atoms;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;
import com.wasd.lib3d.objects.atoms.framedata.FrameDataForText;
import com.wasd.lib3d.rendering.Renderer;

public class PrimitiveText implements Atom<FrameDataForText> {

    private static final float TEXT_SIZE_FACTOR = 3f;

    private final Float3 pos;

    private final FrameDataForText frameData;

    public PrimitiveText(float x, float y, float z, String text) {
        this.pos = new Float3(x, y, z);
        frameData = new FrameDataForText(text);
    }

    @Override
    public void update(Camera camera) {
        Float2 locationOnScreen = Settings.PROJECTION.locationOnScreen(camera, pos);
        frameData.setLocationOnScreen(locationOnScreen);

        if (locationOnScreen == null) {
            return;
        }

        frameData.setZDistanceFromCamera(pos.z - camera.getZ());
        frameData.setSize(TEXT_SIZE_FACTOR / frameData.getZDistanceFromCamera());
    }

    @Override
    public FrameDataForText getFrameData() {
        return frameData;
    }

    @Override
    public void render(Renderer renderer) {
        if (frameData.shouldRender()) {
            renderer.renderText(frameData);
        }
    }
}
