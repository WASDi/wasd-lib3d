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
        drawable.setStartLocationOnScreen(fromDot.getDrawable().getLocationOnScreen());
        drawable.setEndLocationOnScreen(toDot.getDrawable().getLocationOnScreen());

        if (!drawable.shouldRender()) {
            return;
        }

        drawable.setZDistanceFromCamera((fromDot.getPos().z + toDot.getPos().z) / 2f - camera.getPosZ());
    }

    @Override
    public DrawableLine getDrawable() {
        return drawable;
    }
}
