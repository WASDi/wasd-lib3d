package com.wasd.lib3d.shapes;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.shapes.primitives.Dot;
import com.wasd.lib3d.shapes.primitives.Line;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableDot;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableLine;

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
        lines.add(new Line(dots.get(0), dots.get(1)));
        lines.add(new Line(dots.get(0), dots.get(2)));
        lines.add(new Line(dots.get(3), dots.get(1)));
        lines.add(new Line(dots.get(3), dots.get(2)));

        lines.add(new Line(dots.get(4), dots.get(5)));
        lines.add(new Line(dots.get(4), dots.get(6)));
        lines.add(new Line(dots.get(7), dots.get(5)));
        lines.add(new Line(dots.get(7), dots.get(6)));

        lines.add(new Line(dots.get(0), dots.get(4)));
        lines.add(new Line(dots.get(1), dots.get(5)));
        lines.add(new Line(dots.get(2), dots.get(6)));
        lines.add(new Line(dots.get(3), dots.get(7)));

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
