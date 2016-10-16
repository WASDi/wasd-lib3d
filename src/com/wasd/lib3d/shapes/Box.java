package com.wasd.lib3d.shapes;

import com.wasd.lib3d.shapes.primitives.Dot;
import com.wasd.lib3d.shapes.primitives.Line;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableDot;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableLine;

public class Box extends Shape {

    public Box(float x, float y, float z, float size) {
        super(8, 12);
        initDots(x, y, z, size);
        initLinesBetweenDots();
    }

    private void initDots(float x, float y, float z, float size) {
        float halfSize = size / 2;
        dots.add(new Dot(x + halfSize, y + halfSize, z + halfSize));
        dots.add(new Dot(x + halfSize, y + halfSize, z - halfSize));
        dots.add(new Dot(x + halfSize, y - halfSize, z + halfSize));
        dots.add(new Dot(x + halfSize, y - halfSize, z - halfSize));

        dots.add(new Dot(x - halfSize, y + halfSize, z + halfSize));
        dots.add(new Dot(x - halfSize, y + halfSize, z - halfSize));
        dots.add(new Dot(x - halfSize, y - halfSize, z + halfSize));
        dots.add(new Dot(x - halfSize, y - halfSize, z - halfSize));

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
    public Iterable<DrawableDot> getDrawableDotsAfterCalculation() {
        return dotsAsDrawable;
    }

    @Override
    public Iterable<DrawableLine> getDrawableLinesAfterCalculation() {
        return linesAsDrawable;
    }
}
