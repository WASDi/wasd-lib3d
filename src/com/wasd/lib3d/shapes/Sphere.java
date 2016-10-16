package com.wasd.lib3d.shapes;

import com.wasd.lib3d.Camera;

public class Sphere extends Shape {

    public Sphere(float x, float y, float z, float size) {
        super(100, 100);
        initDots(x, y, z, size);
        initLinesBetweenDots();
    }

    private void initDots(float x, float y, float z, float size) {
        dots.forEach(dot -> dotsAsDrawable.add(dot.getDrawable()));
    }

    private void initLinesBetweenDots() {
        lines.forEach(dot -> linesAsDrawable.add(dot.getDrawable()));
    }

    @Override
    public void updateDrawables(Camera camera) {

    }
}
