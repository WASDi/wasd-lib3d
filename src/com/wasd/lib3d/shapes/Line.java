package com.wasd.lib3d.shapes;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.shapes.drawable.DrawableDot;
import com.wasd.lib3d.shapes.drawable.DrawableLine;

import java.util.Collections;

public class Line implements Shape {

    private DrawableLine drawable = new DrawableLine();

    private final Dot fromDot;
    private final Dot toDot;

    public Line(Dot fromDot, Dot toDot) {
        this.fromDot = fromDot;
        this.toDot = toDot;
    }

    @Override
    public void calculateDotsAndLinesToDraw(Camera camera) {

    }

    @Override
    public Iterable<DrawableDot> getDrawableDotsAfterCalculation() {
        return Collections.<DrawableDot>emptyList();
    }

    @Override
    public Iterable<DrawableLine> getDrawableLinesAfterCalculation() {
        throw new UnsupportedOperationException("MUST DO");
    }

    public DrawableLine getDrawable() {
        return drawable;
    }
}
