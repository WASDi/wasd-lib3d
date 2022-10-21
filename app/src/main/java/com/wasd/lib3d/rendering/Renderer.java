package com.wasd.lib3d.rendering;

import com.wasd.lib3d.Settings;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.objects.atoms.framedata.FrameDataForDot;
import com.wasd.lib3d.objects.atoms.framedata.FrameDataForLine;
import com.wasd.lib3d.objects.atoms.framedata.FrameDataForText;

import java.awt.Color;

public class Renderer {

    private final GraphicsWrapper graphics;
    private final int centerX;
    private final int centerY;

    public Renderer(GraphicsWrapper graphics, int width, int height) {
        this.graphics = graphics;
        centerX = width / 2;
        centerY = height / 2;
    }

    public void renderLine(FrameDataForLine frameData) {
        Color color = Colors.colorBasedOnDistance(frameData);
        if (color == null) {
            return;
        }
        Float2 fromPixel = relativeToAbsolutePixels(frameData.getStartLocationOnScreen());
        Float2 toPixel = relativeToAbsolutePixels(frameData.getEndLocationOnScreen());
        graphics.drawLine(color, fromPixel, toPixel);
    }

    public void renderDot(FrameDataForDot frameData) {
        Color color = Colors.colorBasedOnDistance(frameData);
        if (color == null) {
            return;
        }
        Float2 pixelLocation = relativeToAbsolutePixels(frameData.getLocationOnScreen());
        graphics.drawDot(color, frameData.getSize(), pixelLocation);
    }

    public void renderText(FrameDataForText frameData) {
        Color color = Colors.colorBasedOnDistance(frameData);
        if (color == null) {
            return;
        }
        Float2 pixelLocation = relativeToAbsolutePixels(frameData.getLocationOnScreen());
        graphics.drawText(color, frameData.getText(), frameData.getFontSize(), pixelLocation);
    }

    private Float2 relativeToAbsolutePixels(Float2 relativeLocation) {
        float pixelX = centerX + relativeLocation.x * Settings.RELATIVE_TO_ABSOLUTE_PIXEL_RATIO;
        float pixelY = centerY + relativeLocation.y * Settings.RELATIVE_TO_ABSOLUTE_PIXEL_RATIO;
        return new Float2(pixelX, pixelY);
    }
}
