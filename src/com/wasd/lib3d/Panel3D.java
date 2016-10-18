package com.wasd.lib3d;

import com.sun.istack.internal.Nullable;
import com.wasd.lib3d.shapes.Shape;
import com.wasd.lib3d.shapes.primitives.drawable.Drawable;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableDot;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableLine;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

public class Panel3D extends JPanel {

    public static final float RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR = 500;

    private List<Shape> shapes = new ArrayList<>();
    private final Camera camera;

    public Panel3D() {
        camera = new Camera();
    }

    public void addFullMouseListener(MouseAdapter mouseAdapter) {
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        addMouseWheelListener(mouseAdapter);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        updateDrawables();
        drawAllLines(g);
        drawAllDots(g);
    }

    private void updateDrawables() {
        for (Shape shape : shapes) {
            shape.updateDrawables(camera);
        }
    }

    private void drawAllLines(Graphics g) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        for (Shape shape : shapes) {
            for (DrawableLine drawableLine : shape.getDrawableLinesAfterCalculation()) {
                if (drawableLine.shouldRender()) {
                    continue;
                }
                Color c = colorBasedOnDistance(drawableLine);
                if (c == null) {
                    continue;
                }
                float pixelX1 = centerX + drawableLine.getX1() * RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
                float pixelY1 = centerY + drawableLine.getY1() * RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
                float pixelX2 = centerX + drawableLine.getX2() * RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
                float pixelY2 = centerY + drawableLine.getY2() * RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;

                g.setColor(c);
                g.drawLine(Math.round(pixelX1), Math.round(pixelY1),
                        Math.round(pixelX2), Math.round(pixelY2));
            }
        }
    }

    private void drawAllDots(Graphics g) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        for (Shape shape : shapes) {
            for (DrawableDot drawableDot : shape.getDrawableDotsAfterCalculation()) {
                if (drawableDot.shouldRender()) {
                    continue;
                }
                int dotDrawSize = Math.round(drawableDot.getSize());
                if (dotDrawSize <= 1) {
                    continue;
                }
                Color c = colorBasedOnDistance(drawableDot);
                if (c == null) {
                    continue;
                }
                float pixelX = centerX + drawableDot.getX() * RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
                float pixelY = centerY + drawableDot.getY() * RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;

                g.setColor(c);
                g.fillOval(Math.round(pixelX - dotDrawSize / 2f), Math.round(pixelY - dotDrawSize / 2f),
                        dotDrawSize, dotDrawSize);
            }
        }
    }

    /**
     * @return null if too far away and should not be drawn.
     */
    @Nullable
    private Color colorBasedOnDistance(Drawable drawableLine) {
        //TODO doesn't fit in this class,
        float distance = drawableLine.getZDistanceFromCamera();
        Color oldColor = drawableLine.getColor();

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

    public void onMouseDrag(int dx, int dy) {
        camera.relativeXYMovement(-dx / RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR,
                -dy / RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR);
        repaint();
    }

    public void onMouseScroll(float scrollAmount) {
        camera.relativeZMovement(scrollAmount);
        repaint();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }
}
