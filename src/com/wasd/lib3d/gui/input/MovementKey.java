package com.wasd.lib3d.gui.input;

import com.wasd.lib3d.model.Float3;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public enum MovementKey {
    W(KeyEvent.VK_W, new Float3(0, 0, 1)),
    A(KeyEvent.VK_A, new Float3(-1, 0, 0)),
    S(KeyEvent.VK_S, new Float3(0, 0, -1)),
    D(KeyEvent.VK_D, new Float3(1, 0, 0)),
    SPACE(KeyEvent.VK_SPACE, new Float3(0, -1, 0)),
    DOWN(KeyEvent.VK_CONTROL, new Float3(0, 1, 0)) {
        @Override
        KeyStroke getKeyStroke(boolean press) {
            //CTRL is special
            if (press) {
                return KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK, false);
            }
            return KeyStroke.getKeyStroke("released CONTROL");
        }
    };

    public final int keyCode;
    public final Float3 movementVector;

    MovementKey(int keyCode, Float3 movementVector) {
        this.keyCode = keyCode;
        this.movementVector = movementVector;
    }

    public static List<MovementModifier> generateMovementModifiers(JComponent keyEventSource) {
        List<MovementModifier> modifiers = new ArrayList<>();

        for (MovementKey key : values()) {
            MovementModifier movementModifier = new MovementModifier(key.movementVector);
            modifiers.add(movementModifier);

            KeyStroke keyPressed = key.getKeyStroke(true);
            KeyStroke keyReleased = key.getKeyStroke(false);

            String pressCode = key.keyCode + ".PRESS";
            String releaseCode = key.keyCode + ".RELEASE";

            keyEventSource.getInputMap().put(keyPressed, pressCode);
            keyEventSource.getInputMap().put(keyReleased, releaseCode);

            keyEventSource.getActionMap().put(pressCode, new MovementModifierActiveSetter(movementModifier, true));
            keyEventSource.getActionMap().put(releaseCode, new MovementModifierActiveSetter(movementModifier, false));
        }
        return modifiers;
    }

    KeyStroke getKeyStroke(boolean press) {
        return KeyStroke.getKeyStroke(keyCode, 0, !press);
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
