package com.wasd.lib3d.rendering;

import com.sun.istack.internal.Nullable;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.shapes.primitives.drawable.Drawable;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableDot;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableLine;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableText;

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

    public void renderLine(DrawableLine drawableLine) {
        Color c = colorBasedOnDistance(drawableLine);
        if (c == null) {
            return;
        }

        float pixelX1 = centerX + drawableLine.getStartLocationOnScreen().x * Settings.RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
        float pixelY1 = centerY + drawableLine.getStartLocationOnScreen().y * Settings.RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
        float pixelX2 = centerX + drawableLine.getEndLocationOnScreen().x * Settings.RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
        float pixelY2 = centerY + drawableLine.getEndLocationOnScreen().y * Settings.RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;

        graphics.setColor(c);
        graphics.drawLine(Math.round(pixelX1), Math.round(pixelY1),
                Math.round(pixelX2), Math.round(pixelY2));
    }

    public void renderDot(DrawableDot drawableDot) {
        int dotDrawSize = Math.round(drawableDot.getSize());
        if (dotDrawSize <= 1) {
            return;
        }
        Color c = colorBasedOnDistance(drawableDot);
        if (c == null) {
            return;
        }
        float pixelX = centerX + drawableDot.getLocationOnScreen().x * Settings.RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
        float pixelY = centerY + drawableDot.getLocationOnScreen().y * Settings.RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;

        graphics.setColor(c);
        graphics.fillOval(Math.round(pixelX - dotDrawSize / 2f), Math.round(pixelY - dotDrawSize / 2f),
                dotDrawSize, dotDrawSize);
    }

    public void renderText(DrawableText drawableText) {
        //TODO http://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java

        int dotDrawSize = Math.round(drawableText.getSize());
        if (dotDrawSize <= 1) {
            return;
        }
        Color c = colorBasedOnDistance(drawableText);
        if (c == null) {
            return;
        }
        float pixelX = centerX + drawableText.getLocationOnScreen().x * Settings.RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
        float pixelY = centerY + drawableText.getLocationOnScreen().y * Settings.RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;

        graphics.setColor(c);
        graphics.drawString(drawableText.getText(), Math.round(pixelX - dotDrawSize / 2f), Math.round(pixelY - dotDrawSize / 2f));
        //TODO REMOVE DUPLICATE CODE !!! same as above but drawString instead of fillOval
    }

    /**
     * @return null if too far away and should not be drawn.
     */
    @Nullable
    private static Color colorBasedOnDistance(Drawable drawable) {
        float distance = drawable.getZDistanceFromCamera();
        Color oldColor = drawable.getColor();

        float distanceForMaxColor = .5f;
        float distanceForLeastColor = 4f;

        if (distance < distanceForMaxColor) {
            return oldColor;
        } else if (distance > distanceForLeastColor) {
            return null;
        }
        float fogFactor = 1 - (distance - distanceForMaxColor) / (distanceForLeastColor - distanceForMaxColor);
        float red = oldColor.getRed() * fogFactor;
        float green = oldColor.getGreen() * fogFactor;
        float blue = oldColor.getBlue() * fogFactor;
        return new Color((int) red,
                (int) green,
                (int) blue);
    }
}
