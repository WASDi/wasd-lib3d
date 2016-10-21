package com.wasd.lib3d.rendering;

import com.wasd.lib3d.misc.FontCache;
import com.wasd.lib3d.model.Float2;

import java.awt.Color;
import java.awt.Graphics;

public class GraphicsWrapper {

    private final Graphics graphics;

    public GraphicsWrapper(Graphics graphics) {
        this.graphics = graphics;
    }

    public void drawLine(Color color, Float2 fromPixel, Float2 toPixel) {
        graphics.setColor(color);
        graphics.drawLine(Math.round(fromPixel.x), Math.round(fromPixel.y),
                Math.round(toPixel.x), Math.round(toPixel.y));
    }

    public void drawDot(Color color, float size, Float2 pixelLocation) {
        int intSize = Math.round(size);
        graphics.setColor(color);
        graphics.fillOval(Math.round(pixelLocation.x - size / 2f), Math.round(pixelLocation.y - size / 2f),
                intSize, intSize);
    }

    public void drawText(Color color, String text, float fontSize, Float2 pixelLocation) {
        //TODO http://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java
        graphics.setColor(color);
        graphics.setFont(FontCache.get(Math.round(fontSize)));
        graphics.drawString(text, Math.round(pixelLocation.x), Math.round(pixelLocation.y));
    }
}
