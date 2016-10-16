package com.wasd.lib3d.shapes;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.shapes.primitives.Dot;
import com.wasd.lib3d.shapes.primitives.Line;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableDot;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableLine;

import java.util.ArrayList;

public abstract class Shape {

    protected final ArrayList<Dot> dots;
    protected final ArrayList<DrawableDot> dotsAsDrawable;

    protected final ArrayList<Line> lines;
    protected final ArrayList<DrawableLine> linesAsDrawable;

    public Shape(int numDots, int numLines) {
        dots = new ArrayList<>(numDots);
        dotsAsDrawable = new ArrayList<>(numDots);

        lines = new ArrayList<>(numLines);
        linesAsDrawable = new ArrayList<>(numLines);
    }

    public abstract void updateDrawables(Camera camera);

    public Iterable<DrawableDot> getDrawableDotsAfterCalculation() {
        return dotsAsDrawable;
    }

    public Iterable<DrawableLine> getDrawableLinesAfterCalculation() {
        return linesAsDrawable;
    }
}
