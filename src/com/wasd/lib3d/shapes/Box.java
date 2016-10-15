package com.wasd.lib3d.shapes;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.shapes.drawable.DrawableDot;
import com.wasd.lib3d.shapes.drawable.DrawableLine;

import java.awt.*;
import java.util.ArrayList;

public class Box implements Shape {

    private final ArrayList<Dot> dots = new ArrayList<>(8);
    private final ArrayList<DrawableDot> dotsAsDrawable = new ArrayList<>(8);

    private final ArrayList<Line> lines = new ArrayList<>(12);
    private final ArrayList<DrawableLine> linesAsDrawable = new ArrayList<>(12);


    public Box(float x, float y, float z, float size) {
        initDots(x, y, z, size);
        initLinesBetweenDots();
    }

    private void initDots(float x, float y, float z, float size) {
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

    private void initLinesBetweenDots() {
        lines.add(new Line(dots.get(0), dots.get(7))); //TODO do correctly

        lines.forEach(dot -> linesAsDrawable.add(dot.getDrawable()));
    }

    @Override
    public void calculateDotsAndLinesToDraw(Camera camera) {
        dots.forEach(dot -> dot.calculateDotsAndLinesToDraw(camera));
        lines.forEach(line -> line.calculateDotsAndLinesToDraw(camera));
    }

    @Override
    public Iterable<DrawableDot> getDrawableDotsAfterCalculation() {
        return dotsAsDrawable;
    }

    @Override
    public Iterable<DrawableLine> getDrawableLinesAfterCalculation() {
        return linesAsDrawable;
    }

    public Box withColor(Color color) {
        dotsAsDrawable.forEach(drawableDot -> drawableDot.updateColor(color));
        return this;
    }
}
