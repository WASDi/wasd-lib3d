package com.wasd.lib3d.gui;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.gui.input.MovementModifier;
import com.wasd.lib3d.model.Float3;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AnimationLoop implements ActionListener {

    private final Component repaintThis;
    private final Camera camera;
    private final List<MovementModifier> movementModifiers;

    public AnimationLoop(Component repaintThis, Camera camera, List<MovementModifier> movementModifiers) {
        this.repaintThis = repaintThis;
        this.camera = camera;
        this.movementModifiers = movementModifiers;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Float3 cameraMovementVector = getCameraMovementVector();
        if (cameraMovementVector.isZero()) {
            return;
        }
        camera.relativeMovementRespectRotation(cameraMovementVector);
        repaintThis.repaint();
    }

    private Float3 getCameraMovementVector() {
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

        dx *= Settings.MOVEMENT_SPEED_FACTOR;
        dy *= Settings.MOVEMENT_SPEED_FACTOR;
        dz *= Settings.MOVEMENT_SPEED_FACTOR;

        return new Float3(dx, dy, dz);
    }
}
