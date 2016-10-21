package com.wasd.lib3d.rendering;

import com.sun.istack.internal.Nullable;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.misc.FontCache;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.shapes.primitives.framedata.FrameData;
import com.wasd.lib3d.shapes.primitives.framedata.FrameDataForDot;
import com.wasd.lib3d.shapes.primitives.framedata.FrameDataForLine;
import com.wasd.lib3d.shapes.primitives.framedata.FrameDataForText;

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
        Color c = colorBasedOnDistance(frameData);
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
        Color c = colorBasedOnDistance(frameData);
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
        Color c = colorBasedOnDistance(frameData);
        if (c == null) {
            return;
        }
        Float2 pixel = relativeToAbsolutePixels(frameData.getLocationOnScreen());

        graphics.setColor(c);
        graphics.setFont(FontCache.get(fontSize));
        graphics.drawString(frameData.getText(), Math.round(pixel.x), Math.round(pixel.y));
        //TODO REMOVE DUPLICATE CODE !!! same as above but drawString instead of fillOval
    }

    /**
     * @return null if too far away and should not be drawn.
     */
    @Nullable
    private static Color colorBasedOnDistance(FrameData frameData) {
        float zDistance = frameData.getZDistanceFromCamera();
        Color oldColor = frameData.getColor();

        float fogFactor = fogFactor(zDistance);

        if (fogFactor <= 0f) {
            return oldColor;
        } else if (fogFactor >= 1f) {
            return null;
        }

        float red = oldColor.getRed() + fogFactor * (Settings.BACKGROUND_COLOR.getRed() - oldColor.getRed());
        float green = oldColor.getGreen() + fogFactor * (Settings.BACKGROUND_COLOR.getGreen() - oldColor.getGreen());
        float blue = oldColor.getBlue() + fogFactor * (Settings.BACKGROUND_COLOR.getBlue() - oldColor.getBlue());

        return new Color(
                (int) red,
                (int) green,
                (int) blue
        );
    }

    private static float fogFactor(float zDistance) {
        if (zDistance < Settings.FOG_START_DISTANCE) {
            return 0f;
        } else if (zDistance > Settings.MAX_DISTANCE_TO_RENDER) {
            return 1f;
        }
        return (zDistance - Settings.FOG_START_DISTANCE) /
                (Settings.MAX_DISTANCE_TO_RENDER - Settings.FOG_START_DISTANCE);
    }

    private Float2 relativeToAbsolutePixels(Float2 relativeLocation) {
        float pixelX = centerX + relativeLocation.x * Settings.RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
        float pixelY = centerY + relativeLocation.y * Settings.RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
        return new Float2(pixelX, pixelY);
    }
}
