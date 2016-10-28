package com.wasd.lib3d.input;

import com.wasd.lib3d.gui.Panel3D;
import com.wasd.lib3d.model.Float2;

import javax.swing.KeyStroke;

public enum WASDKeys {
    W('w', new Float2(0, 1)),
    A('a', new Float2(-1, 0)),
    S('s', new Float2(1, 0)),
    D('d', new Float2(0, -1));

    public final char key;
    public final Float2 xzMovementVector;

    WASDKeys(char key, Float2 xzMovementVector) {
        this.key = key;
        this.xzMovementVector = xzMovementVector;
    }

    public static void registerFor(Panel3D panel3D) {
        for (WASDKeys event : values()) {
            panel3D.getInputMap().put(KeyStroke.getKeyStroke(event.key), event);
            panel3D.getActionMap().put(event, new MovementAction(event.xzMovementVector));
        }
    }
}
