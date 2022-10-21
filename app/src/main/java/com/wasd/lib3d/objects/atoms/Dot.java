package com.wasd.lib3d.objects.atoms;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.model.Float3;
import com.wasd.lib3d.objects.atoms.framedata.FrameDataForDot;
import com.wasd.lib3d.rendering.Renderer;

public class Dot implements Atom<FrameDataForDot> {

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
        Float3 projection = Settings.PROJECTION.xyLocationOnScreenAndZDistanceFromCamera(camera, pos);
        if (projection == null) {
            frameData.setLocationOnScreen(null);
            return;
        }

        frameData.setLocationOnScreen(projection.getXY());
        frameData.setZDistanceFromCamera(projection.z);
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
