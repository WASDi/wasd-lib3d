package com.wasd.lib3d.rendering;

import com.sun.istack.internal.Nullable;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.shapes.primitives.drawable.Drawable;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableDot;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableLine;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableText;

import java.awt.Color;
import java.awt.Font;
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

    public void render(Drawable drawable){
        Color color = colorBasedOnDistance(drawable);
        if (color == null) {
            return;
        }
        graphics.setColor(color);

        if(drawable instanceof DrawableLine){
            renderLine((DrawableLine) drawable);
        } else if(drawable instanceof DrawableDot){
            DrawableDot dotted = (DrawableDot)drawable;

            int size = Math.round(dotted.getSize());
            Float2 positionPixel = relativeToAbsolutePixels(dotted.getLocationOnScreen());

            if(drawable instanceof DrawableText){
                renderText(((DrawableText) drawable).getText(), size, positionPixel);
            } else {
                renderDot(size, positionPixel);
            }
        }
    }

    private void renderLine(DrawableLine drawableLine) {
        Float2 positionPixel1 = relativeToAbsolutePixels(drawableLine.getStartLocationOnScreen());
        Float2 positionPixel2 = relativeToAbsolutePixels(drawableLine.getEndLocationOnScreen());

        graphics.drawLine(Math.round(positionPixel1.x), Math.round(positionPixel1.y),
                Math.round(positionPixel2.x), Math.round(positionPixel2.y));
    }

    private void renderDot(int dotDrawSize, Float2 positionPixel) {
        graphics.fillOval(Math.round(positionPixel.x - dotDrawSize / 2f), Math.round(positionPixel.y - dotDrawSize / 2f),
                dotDrawSize, dotDrawSize);
    }

    private void renderText(String text, int fontSize, Float2 positionPixel) {
        //TODO http://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java

        graphics.setFont(createFont(fontSize));
        graphics.drawString(text, Math.round(positionPixel.x), Math.round(positionPixel.y));
    }

    private Font createFont(int fontSize) {
        return new Font(null, Font.PLAIN, fontSize);
    }

    /**
     * @return null if too far away and should not be drawn.
     */
    @Nullable
    private static Color colorBasedOnDistance(Drawable drawable) {
        float zDistance = drawable.getZDistanceFromCamera();
        Color oldColor = drawable.getColor();

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
