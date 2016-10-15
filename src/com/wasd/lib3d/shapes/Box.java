package com.wasd.lib3d.shapes;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.shapes.drawable.DrawableDot;

import java.awt.*;
import java.util.ArrayList;

public class Box implements Shape {

    private final ArrayList<Dot> dots = new ArrayList<>(8);
    private final ArrayList<DrawableDot> dotsAsDrawable = new ArrayList<>(8);


    public Box(float x, float y, float z, float size) {
        dots.add(new Dot(x + size, y + size, z + size));
        dots.add(new Dot(x + size, y + size, z - size));
        dots.add(new Dot(x + size, y - size, z + size));
        dots.add(new Dot(x + size, y - size, z - size));

        dots.add(new Dot(x - size, y + size, z + size));
        dots.add(new Dot(x - size, y + size, z - size));
        dots.add(new Dot(x - size, y - size, z + size));
        dots.add(new Dot(x - size, y - size, z - size));

        dots.forEach(dot -> dotsAsDrawable.add(dot.getDrawable()));
    }

    @Override
    public void calculateDotsAndLinesToDraw(Camera camera) {
        dots.forEach(dot -> dot.calculateDotsAndLinesToDraw(camera));
    }

    @Override
    public Iterable<DrawableDot> getDrawableDotsAfterCalculation() {
        return dotsAsDrawable;
    }

    public Box withColor(Color color) {
        dotsAsDrawable.forEach(drawableDot -> drawableDot.updateColor(color));
        return this;
    }
}
