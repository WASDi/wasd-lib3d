package com.wasd.lib3d.shapes;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.shapes.drawable.DrawableDot;
import com.wasd.lib3d.shapes.drawable.DrawableLine;

import java.util.ArrayList;
import java.util.Collections;

public class Dot implements Shape {

    public static final float DOT_SIZE_FACTOR = 5f;
    private float x;
    private float y;
    private float z;

    private DrawableDot drawable = new DrawableDot();
    private ArrayList<DrawableDot> singletonList = new ArrayList<>(1);

    public Dot(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        singletonList.add(drawable);
    }

    @Override
    public void calculateDotsAndLinesToDraw(Camera camera) {
        float factorStepAwayFromCenter = 1 / (z - camera.getPosZ());

        drawable.updateX(camera.getPosX() * factorStepAwayFromCenter + x * factorStepAwayFromCenter);
        drawable.updateY(camera.getPosY() * factorStepAwayFromCenter + y * factorStepAwayFromCenter);
        drawable.updateSize(DOT_SIZE_FACTOR * factorStepAwayFromCenter);
    }

    @Override
    public Iterable<DrawableDot> getDrawableDotsAfterCalculation() {
        return singletonList;
    }

    @Override
    public Iterable<DrawableLine> getDrawableLinesAfterCalculation() {
        return Collections.<DrawableLine>emptyList();
    }

    public DrawableDot getDrawable() {
        return drawable;
    }
}
