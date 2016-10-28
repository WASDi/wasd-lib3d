package com.wasd.lib3d.input;

import com.wasd.lib3d.model.Float2;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class MovementAction extends AbstractAction {
    private final Float2 xzMovementVector;

    public MovementAction(Float2 xzMovementVector) {
        this.xzMovementVector = xzMovementVector;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(xzMovementVector);
    }
}
