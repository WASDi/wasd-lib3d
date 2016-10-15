package com.wasd.lib3d.shapes.drawable;

import java.awt.*;

public class DrawableDot {

    private float x;
    private float y;
    private float size;
    private Color color = Color.WHITE;

    public void updateX(float x) {
        this.x = x;
    }

    public void updateY(float y) {
        this.y = y;
    }

    public void updateSize(float size) {
        this.size = size;
    }

    public void updateColor(Color color) {
        this.color = color;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

}
