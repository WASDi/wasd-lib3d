package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.shapes.Shape;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableDot;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableLine;

import java.util.ArrayList;
import java.util.Collections;

public class Line implements Shape {

    private DrawableLine drawable = new DrawableLine();

    private final Dot fromDot;
    private final Dot toDot;

    private ArrayList<DrawableLine> singletonList = new ArrayList<>(1);

    public Line(Dot fromDot, Dot toDot) {
        this.fromDot = fromDot;
        this.toDot = toDot;

        singletonList.add(drawable);
    }

    @Override
    public void calculateDotsAndLinesToDraw(Camera camera) {
        drawable.updateX1(fromDot.getDrawable().getX());
        drawable.updateY1(fromDot.getDrawable().getY());

        drawable.updateX2(toDot.getDrawable().getX());
        drawable.updateY2(toDot.getDrawable().getY());
    }

    @Override
    public Iterable<DrawableDot> getDrawableDotsAfterCalculation() {
        return Collections.<DrawableDot>emptyList();
    }

    @Override
    public Iterable<DrawableLine> getDrawableLinesAfterCalculation() {
        return singletonList;
    }

    public DrawableLine getDrawable() {
        return drawable;
    }
}
