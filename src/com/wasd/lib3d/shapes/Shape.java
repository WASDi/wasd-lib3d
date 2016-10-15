package com.wasd.lib3d.shapes;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableDot;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableLine;

public interface Shape {

    void calculateDotsAndLinesToDraw(Camera camera);

    Iterable<DrawableDot> getDrawableDotsAfterCalculation();

    Iterable<DrawableLine> getDrawableLinesAfterCalculation();
}
