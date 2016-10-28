package com.wasd.lib3d.gui.input;

import com.wasd.lib3d.gui.Panel3D;
import com.wasd.lib3d.model.Float2;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class MovementAction extends AbstractAction {

    private final Float2 xzMovementVector;
    private final Panel3D cameraPanel;

    public MovementAction(Float2 xzMovementVector, Panel3D panelContainingCamera) {
        this.xzMovementVector = xzMovementVector;
        this.cameraPanel = panelContainingCamera;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cameraPanel.cameraXZMovement(xzMovementVector);
    }
}
