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

    public void renderLine(DrawableLine drawableLine) {
        Color c = colorBasedOnDistance(drawableLine);
        if (c == null) {
            return;
        }

        Float2 pixel1 = relativeToAbsolutePixels(drawableLine.getStartLocationOnScreen());
        Float2 pixel2 = relativeToAbsolutePixels(drawableLine.getEndLocationOnScreen());

        graphics.setColor(c);
        graphics.drawLine(Math.round(pixel1.x), Math.round(pixel1.y),
                Math.round(pixel2.x), Math.round(pixel2.y));
    }

    public void renderDot(DrawableDot drawableDot) {
        int dotDrawSize = Math.round(drawableDot.getSize());
        Color c = colorBasedOnDistance(drawableDot);
        if (c == null) {
            return;
        }

        Float2 pixel = relativeToAbsolutePixels(drawableDot.getLocationOnScreen());

        graphics.setColor(c);
        graphics.fillOval(Math.round(pixel.x - dotDrawSize / 2f), Math.round(pixel.y - dotDrawSize / 2f),
                dotDrawSize, dotDrawSize);
    }

    public void renderText(DrawableText drawableText) {
        //TODO http://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java

        int fontSize = Math.round(drawableText.getSize());
        Color c = colorBasedOnDistance(drawableText);
        if (c == null) {
            return;
        }
        Float2 pixel = relativeToAbsolutePixels(drawableText.getLocationOnScreen());

        graphics.setColor(c);
        graphics.setFont(createFont(fontSize));
        graphics.drawString(drawableText.getText(), Math.round(pixel.x), Math.round(pixel.y));
        //TODO REMOVE DUPLICATE CODE !!! same as above but drawString instead of fillOval
    }

    private Font createFont(int fontSize) {
        return new Font(null, Font.PLAIN, fontSize);
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

    private Float2 relativeToAbsolutePixels(Float2 relativeLocation) {
        float pixelX = centerX + relativeLocation.x * Settings.RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
        float pixelY = centerY + relativeLocation.y * Settings.RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
        return new Float2(pixelX, pixelY);
    }
}
