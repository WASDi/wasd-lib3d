package com.wasd.lib3d.gui.input;

import com.wasd.lib3d.model.Float3;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class MovementAction extends AbstractAction {

    private final Float3 movementVector;
    private final CameraMovementController cameraMovementController;

    public MovementAction(Float3 movementVector, CameraMovementController cameraMovementController) {
        this.movementVector = movementVector;
        this.cameraMovementController = cameraMovementController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cameraMovementController.cameraMovement(movementVector);
    }
}
