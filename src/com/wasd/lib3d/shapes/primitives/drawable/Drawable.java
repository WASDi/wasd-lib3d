package com.wasd.lib3d.shapes.primitives.drawable;

import java.awt.Color;

public abstract class Drawable {

    private boolean shouldRender;

    public boolean shouldRender() {
        return shouldRender;
    }

    public void setShouldRender(boolean shouldRender) {
        this.shouldRender = shouldRender;
    }

    public abstract Color getColor();

}
