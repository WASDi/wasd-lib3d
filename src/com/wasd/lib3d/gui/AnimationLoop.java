package com.wasd.lib3d.gui;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.model.Float3;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class AnimationLoop implements ActionListener {

    private final Component repaintThis;
    private final Camera camera;

    AnimationLoop(Component repaintThis, Camera camera) {
        this.repaintThis = repaintThis;
        this.camera = camera;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Float3 cameraMovementVector = getCameraMovementVector();
        if (cameraMovementVector.isZero()) {
            return;
        }
        camera.relativeMovement(cameraMovementVector);
        repaintThis.repaint();
    }

    private Float3 getCameraMovementVector() {
        return Float3.ZERO; //TODO calculate form what keys are pressed
    }
}
