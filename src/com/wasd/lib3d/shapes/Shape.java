package com.wasd.lib3d.shapes;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.future_features.World;
import com.wasd.lib3d.shapes.primitives.Dot;
import com.wasd.lib3d.shapes.primitives.Line;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableDot;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableLine;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Shape {

    /**
     * This is SHIT !!! Need a smarter way. Will look too ugly after adding Text too.
     * Start using #{@link World}
     */
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

    public void updateDrawables(Camera camera) {
        dots.forEach(dot -> dot.updateDrawable(camera));
        lines.forEach(line -> line.updateDrawable(camera));
    }

    public Iterable<DrawableDot> getDrawableDotsAfterCalculation() {
        return dotsAsDrawable;
    }

    public Iterable<DrawableLine> getDrawableLinesAfterCalculation() {
        return linesAsDrawable;
    }

    public Shape withDotColor(Color color) {
        dotsAsDrawable.forEach(drawableDot -> drawableDot.setColor(color));
        return this;
    }

    public Shape withLineColor(Color color) {
        linesAsDrawable.forEach(drawableLine -> drawableLine.setColor(color));
        return this;
    }
}
