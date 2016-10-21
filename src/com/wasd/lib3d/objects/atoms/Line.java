package com.wasd.lib3d.objects.atoms;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.objects.atoms.framedata.FrameDataForLine;
import com.wasd.lib3d.rendering.Renderer;

public class Line implements Atom<FrameDataForLine> {

    private FrameDataForLine frameData = new FrameDataForLine();

    private final Dot fromDot;
    private final Dot toDot;

    public Line(Dot fromDot, Dot toDot) {
        this.fromDot = fromDot;
        this.toDot = toDot;
    }

    @Override
    public void update(Camera camera) {
        frameData.setStartLocationOnScreen(fromDot.getFrameData().getLocationOnScreen());
        frameData.setEndLocationOnScreen(toDot.getFrameData().getLocationOnScreen());

        if (!frameData.shouldRender()) {
            return;
        }

        frameData.setZDistanceFromCamera((fromDot.getPos().z + toDot.getPos().z) / 2f - camera.getZ());
    }

    @Override
    public void render(Renderer renderer) {
        if (frameData.shouldRender()) {
            renderer.renderLine(frameData);
        }
    }

    @Override
    public FrameDataForLine getFrameData() {
        return frameData;
    }
}
