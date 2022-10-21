package com.wasd.lib3d.animation;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.gui.input.MovementModifier;
import com.wasd.lib3d.model.Float3;

import java.util.List;

public class CameraMovementAnimation implements Animation {

    private final Camera camera;
    private final List<MovementModifier> movementModifiers;

    public CameraMovementAnimation(Camera camera, List<MovementModifier> movementModifiers) {
        this.camera = camera;
        this.movementModifiers = movementModifiers;
    }

    @Override
    public boolean step(float absoluteTime, float deltaTime) {
        Float3 cameraMovementVector = getCameraMovementVector(deltaTime);
        if (cameraMovementVector.isZero()) {
            return false;
        }

        camera.relativeMovementRespectRotation(cameraMovementVector);
        return true;
    }

    private Float3 getCameraMovementVector(float deltaTime) {
        float dx = 0;
        float dy = 0;
        float dz = 0;

        for (MovementModifier movementModifier : movementModifiers) {
            if (movementModifier.isActive()) {
                dx += movementModifier.movementVector.x;
                dy += movementModifier.movementVector.y;
                dz += movementModifier.movementVector.z;
            }
        }

        if (dx == 0 && dy == 0 && dz == 0) {
            return Float3.ZERO;
        }

        return new Float3(dx, dy, dz)
                .times(Settings.MOVEMENT_SPEED_FACTOR * deltaTime);
    }
}
