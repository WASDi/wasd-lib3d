package com.wasd.lib3d.shapes;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.shapes.drawable.DrawableDot;

public interface Shape {

    void calculateDotsAndLinesToDraw(Camera camera);

    Iterable<DrawableDot> getDrawableDotsAfterCalculation();
}
