package com.wasd.lib3d.objects.atoms;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.Settings;
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
        Float3 projection = Settings.PROJECTION.xyLocationOnScreenAndZDistanceFromCamera(camera, pos);
        if (projection == null) {
            frameData.setLocationOnScreen(null);
            return;
        }

        frameData.setLocationOnScreen(projection.getXY());
        frameData.setZDistanceFromCamera(projection.z);
        frameData.setFontSize(TEXT_SIZE_FACTOR / frameData.getZDistanceFromCamera());
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
