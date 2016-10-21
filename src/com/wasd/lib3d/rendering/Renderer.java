package com.wasd.lib3d.rendering;

import com.wasd.lib3d.Settings;
import com.wasd.lib3d.misc.FontCache;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.objects.atoms.framedata.FrameDataForDot;
import com.wasd.lib3d.objects.atoms.framedata.FrameDataForLine;
import com.wasd.lib3d.objects.atoms.framedata.FrameDataForText;

import java.awt.Color;
import java.awt.Graphics;

public class Renderer {

    private final Graphics graphics;
    private final int centerX;
    private final int centerY;

    public Renderer(Graphics graphics, int width, int height) {
        this.graphics = graphics;
        centerX = width / 2;
        centerY = height / 2;
    }

    public void renderLine(FrameDataForLine frameData) {
        Color c = Colors.colorBasedOnDistance(frameData);
        if (c == null) {
            return;
        }

        Float2 pixel1 = relativeToAbsolutePixels(frameData.getStartLocationOnScreen());
        Float2 pixel2 = relativeToAbsolutePixels(frameData.getEndLocationOnScreen());

        graphics.setColor(c);
        graphics.drawLine(Math.round(pixel1.x), Math.round(pixel1.y),
                Math.round(pixel2.x), Math.round(pixel2.y));
    }

    public void renderDot(FrameDataForDot frameData) {
        int dotDrawSize = Math.round(frameData.getSize());
        Color c = Colors.colorBasedOnDistance(frameData);
        if (c == null) {
            return;
        }

        Float2 pixel = relativeToAbsolutePixels(frameData.getLocationOnScreen());

        graphics.setColor(c);
        graphics.fillOval(Math.round(pixel.x - dotDrawSize / 2f), Math.round(pixel.y - dotDrawSize / 2f),
                dotDrawSize, dotDrawSize);
    }

    public void renderText(FrameDataForText frameData) {
        //TODO http://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java

        int fontSize = Math.round(frameData.getSize());
        Color c = Colors.colorBasedOnDistance(frameData);
        if (c == null) {
            return;
        }
        Float2 pixel = relativeToAbsolutePixels(frameData.getLocationOnScreen());

        graphics.setColor(c);
        graphics.setFont(FontCache.get(fontSize));
        graphics.drawString(frameData.getText(), Math.round(pixel.x), Math.round(pixel.y));
        //TODO REMOVE DUPLICATE CODE !!! same as above but drawString instead of fillOval
    }

    private Float2 relativeToAbsolutePixels(Float2 relativeLocation) {
        float pixelX = centerX + relativeLocation.x * Settings.RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
        float pixelY = centerY + relativeLocation.y * Settings.RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
        return new Float2(pixelX, pixelY);
    }
}
