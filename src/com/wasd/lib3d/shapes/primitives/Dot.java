package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;
import com.wasd.lib3d.rendering.Renderer;
import com.wasd.lib3d.shapes.primitives.framedata.FrameDataForDot;

public class Dot implements PrimitiveShape<FrameDataForDot> {

    private static final float DOT_SIZE_FACTOR = 3f;

    private final Float3 pos;

    private final FrameDataForDot frameData = new FrameDataForDot();

    public Dot(float x, float y, float z) {
        pos = new Float3(x, y, z);
    }

    public Float3 getPos() {
        return pos;
    }

    @Override
    public void update(Camera camera) {
        Float2 locationOnScreen = Settings.PROJECTION.locationOnScreen(camera, pos);

        frameData.setLocationOnScreen(locationOnScreen);
        if (locationOnScreen == null) {
            return;
        }

        frameData.setZDistanceFromCamera(pos.z - camera.getZ());
        frameData.setSize(DOT_SIZE_FACTOR / frameData.getZDistanceFromCamera());
    }

    @Override
    public void render(Renderer renderer) {
        if (frameData.shouldRender()) {
            renderer.renderDot(frameData);
        }
    }

    @Override
    public FrameDataForDot getFrameData() {
        return frameData;
    }

}
