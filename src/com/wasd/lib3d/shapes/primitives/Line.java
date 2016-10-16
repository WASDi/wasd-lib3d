package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableLine;

public class Line implements PrimitiveShape<DrawableLine> {

    private DrawableLine drawable = new DrawableLine();

    private final Dot fromDot;
    private final Dot toDot;

    public Line(Dot fromDot, Dot toDot) {
        this.fromDot = fromDot;
        this.toDot = toDot;
    }

    @Override
    public void updateDrawable(Camera camera) {
        float cameraPosZ = camera.getPosZ() + MIN_DISTANCE_FROM_CAMERA;
        if (fromDot.getZ() < cameraPosZ || toDot.getZ() < cameraPosZ) {
            drawable.setOutsideScreen(true);
            return;
        }
        drawable.setOutsideScreen(false);

        drawable.updateX1(fromDot.getDrawable().getX());
        drawable.updateY1(fromDot.getDrawable().getY());

        drawable.updateX2(toDot.getDrawable().getX());
        drawable.updateY2(toDot.getDrawable().getY());
    }

    @Override
    public DrawableLine getDrawable() {
        return drawable;
    }
}
