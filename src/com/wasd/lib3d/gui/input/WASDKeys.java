package com.wasd.lib3d.gui.input;

import com.wasd.lib3d.Settings;
import com.wasd.lib3d.gui.Panel3D;
import com.wasd.lib3d.model.Float3;

import javax.swing.KeyStroke;

public enum WASDKeys {
    W('w', new Float3(0, 0, 1)),
    A('a', new Float3(-1, 0, 0)),
    S('s', new Float3(0, 0, -1)),
    D('d', new Float3(1, 0, 0));

    public final char key;
    public final Float3 movementVector;

    WASDKeys(char key, Float3 movementVector) {
        this.key = key;
        this.movementVector = movementVector;
    }

    public static void registerFor(Panel3D keyEventSource) {
        for (WASDKeys event : values()) {
            keyEventSource.getInputMap().put(KeyStroke.getKeyStroke(event.key), event);
            keyEventSource.getActionMap().put(
                    event,
                    new MovementAction(
                            event.movementVector.times(Settings.WASD_MOVEMENT_SPEED_FACTOR),
                            keyEventSource
                    )
            );
        }
    }
}
