package com.wasd.lib3d.gui.input;

import com.wasd.lib3d.model.Float3;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public enum WASDKey {
    W(KeyEvent.VK_W, new Float3(0, 0, 1)),
    A(KeyEvent.VK_A, new Float3(-1, 0, 0)),
    S(KeyEvent.VK_S, new Float3(0, 0, -1)),
    D(KeyEvent.VK_D, new Float3(1, 0, 0));

    public final int keyCode;
    public final Float3 movementVector;

    WASDKey(int keyCode, Float3 movementVector) {
        this.keyCode = keyCode;
        this.movementVector = movementVector;
    }

    public static List<MovementModifier> generateMovementModifiers(JComponent keyEventSource) {
        List<MovementModifier> modifiers = new ArrayList<>();

        for (WASDKey key : values()) {
            MovementModifier movementModifier = new MovementModifier(key.movementVector);
            modifiers.add(movementModifier);

            KeyStroke keyPressed = KeyStroke.getKeyStroke(key.keyCode, 0, false);
            KeyStroke keyReleased = KeyStroke.getKeyStroke(key.keyCode, 0, true);

            String pressCode = movementModifier.toString() + ".PRESS";
            String releaseCode = movementModifier.toString() + ".RELEASE";

            keyEventSource.getInputMap().put(keyPressed, pressCode);
            keyEventSource.getInputMap().put(keyReleased, releaseCode);

            keyEventSource.getActionMap().put(pressCode, new MovementModifierActiveSetter(movementModifier, true));
            keyEventSource.getActionMap().put(releaseCode, new MovementModifierActiveSetter(movementModifier, false));
        }
        return modifiers;
    }

    private static class MovementModifierActiveSetter extends AbstractAction {

        private final MovementModifier movementModifier;
        private final boolean activeStatus;

        private MovementModifierActiveSetter(MovementModifier movementModifier, boolean activeStatus) {
            this.movementModifier = movementModifier;
            this.activeStatus = activeStatus;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            movementModifier.setActive(activeStatus);
        }
    }
}
