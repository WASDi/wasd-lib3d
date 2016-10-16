package com.wasd.lib3d.shapes.primitives.drawable;

import java.awt.Color;

public abstract class Drawable {

    private boolean outsideScreen;

    public boolean isOutsideScreen() {
        return outsideScreen;
    }

    public void setOutsideScreen(boolean outsideScreen) {
        this.outsideScreen = outsideScreen;
    }

    public abstract Color getColor();

}
