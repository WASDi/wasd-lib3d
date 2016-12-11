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

    public static void registerFor(Panel3D panel3D) {
        //TODO http://stackoverflow.com/questions/22730715/java-keyboard-input-game-development
        //Animation loop using Timer, which checks keyboard/mouse status AND the new Animation class for moving objects.
        //maybe it can have a queue, and execute and clear it each loop. Possibly having nothing to render
        for (WASDKeys event : values()) {
            panel3D.getInputMap().put(KeyStroke.getKeyStroke(event.key), event);
            panel3D.getActionMap().put(
                    event,
                    new MovementAction(
                            event.movementVector.times(Settings.WASD_MOVEMENT_SPEED_FACTOR),
                            panel3D
                    )
            );
        }
    }
}
