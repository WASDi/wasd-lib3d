package com.wasd.lib3d.shapes.primitives;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableText;

public class Text implements PrimitiveShape<DrawableText> {

    private DrawableText drawable = new DrawableText();

    @Override
    public void updateDrawable(Camera camera) {
        //same logic as Dot, TODO don't duplicate code!
    }

    @Override
    public DrawableText getDrawable() {
        return drawable;
    }
}
