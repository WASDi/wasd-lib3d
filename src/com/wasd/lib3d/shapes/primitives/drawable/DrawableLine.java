package com.wasd.lib3d.shapes.primitives.drawable;

import java.awt.Color;

public class DrawableLine implements Drawable {

    private float x1;
    private float y1;
    private float x2;
    private float y2;

    public void updateX1(float x1) {
        this.x1 = x1;
    }

    public void updateY1(float y1) {
        this.y1 = y1;
    }

    public void updateX2(float x2) {
        this.x2 = x2;
    }

    public void updateY2(float y2) {
        this.y2 = y2;
    }

    public float getX1() {
        return x1;
    }

    public float getY1() {
        return y1;
    }

    public float getX2() {
        return x2;
    }

    public float getY2() {
        return y2;
    }

    @Override
    public Color getColor() {
        return Color.CYAN;
    }
}
