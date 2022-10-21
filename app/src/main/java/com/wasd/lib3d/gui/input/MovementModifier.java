package com.wasd.lib3d.gui.input;

import com.wasd.lib3d.model.Float3;

public class MovementModifier {

    public final Float3 movementVector;

    private boolean active;

    public MovementModifier(Float3 movementVector) {
        this.movementVector = movementVector;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
