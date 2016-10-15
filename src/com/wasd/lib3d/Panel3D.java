package com.wasd.lib3d;

import com.wasd.lib3d.shapes.Box;
import com.wasd.lib3d.shapes.Shape;
import com.wasd.lib3d.shapes.drawable.DrawableDot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

public class Panel3D extends JPanel {

    public static final float RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR = 500;

    private static final Color BACKGROUND_LIGHT_GRAY = new Color(240, 240, 240);

    private List<Shape> shapes = new ArrayList<>();
    private final Camera camera;

    public Panel3D() {
        camera = new Camera();

        Color[][] colors = {{Color.RED, Color.GREEN}, {Color.BLUE, Color.YELLOW}};

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                shapes.add(
                        new Box(x / 2f - .25f,
                                y / 2f - .25f, 1,
                                .07f).withColor(colors[x][y])
                );
            }
        }

    }

    public void addFullMouseListener(MouseAdapter mouseAdapter) {
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        addMouseWheelListener(mouseAdapter);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(BACKGROUND_LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        for (Shape shape : shapes) {
            shape.calculateDotsAndLinesToDraw(camera);
            for (DrawableDot drawableDot : shape.getDrawableDotsAfterCalculation()) {
                float pixelX = centerX + drawableDot.getX() * RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
                float pixelY = centerY + drawableDot.getY() * RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR;
                int dotDrawSize = Math.round(drawableDot.getSize());
                g.setColor(drawableDot.getColor());
                g.fillOval(Math.round(pixelX), Math.round(pixelY), dotDrawSize, dotDrawSize);
            }
        }
    }

    public void onMouseDrag(int dx, int dy) {
        camera.relativeXYMovement(dx / RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR,
                dy / RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR);
        repaint();
    }

    public void onMouseScroll(float scrollAmount) {
        camera.relativeZMovement(scrollAmount);
        repaint();
    }
}
