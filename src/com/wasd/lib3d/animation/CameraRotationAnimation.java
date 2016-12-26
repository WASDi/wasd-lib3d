package com.wasd.lib3d.animation;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.model.Float2;

public class CameraRotationAnimation implements Animation {

    private final Camera camera;
    private boolean renderNeeded = false;

    public CameraRotationAnimation(Camera camera) {
        this.camera = camera;
    }

    @Override
    public boolean step(float t) {
        if (renderNeeded) {
            renderNeeded = false;
            return true;
        }
        return false;
    }

    public void rotate(Float2 delta) {
        renderNeeded = true;
        camera.relativeRotation(delta);
    }
}
