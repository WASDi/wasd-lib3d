package com.wasd.lib3d.gui.input;

import com.wasd.lib3d.model.Float3;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class MovementAction extends AbstractAction {

    private final Float3 movementVector;
    private final CameraMovementListenerish cameraMovementListenerish;

    public MovementAction(Float3 movementVector, CameraMovementListenerish cameraMovementListenerish) {
        this.movementVector = movementVector;
        this.cameraMovementListenerish = cameraMovementListenerish;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cameraMovementListenerish.cameraMovement(movementVector);
    }
}
