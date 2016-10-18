package com.wasd.lib3d.gui;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.Renderer;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.shapes.Shape;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableDot;
import com.wasd.lib3d.shapes.primitives.drawable.DrawableLine;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

public class Panel3D extends JPanel {

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
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        updateDrawables();

        Renderer renderer = new Renderer(graphics, getWidth(), getHeight());
        drawAllLines(renderer);
        drawAllDots(renderer);
    }

    private void updateDrawables() {
        for (Shape shape : shapes) {
            shape.updateDrawables(camera);
        }
    }

    private void drawAllLines(Renderer renderer) {
        for (Shape shape : shapes) {
            for (DrawableLine drawableLine : shape.getDrawableLinesAfterCalculation()) {
                if (drawableLine.shouldRender()) {
                    renderer.renderLine(drawableLine);
                }
            }
        }
    }

    private void drawAllDots(Renderer renderer) {
        for (Shape shape : shapes) {
            for (DrawableDot drawableDot : shape.getDrawableDotsAfterCalculation()) {
                if (drawableDot.shouldRender()) {
                    renderer.renderDot(drawableDot);
                }
            }
        }
    }

    public void onMouseDrag(int dx, int dy) {
        camera.relativeXYMovement(-dx / Settings.RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR,
                -dy / Settings.RELATIVE_TO_ABSOLUTE_PIXEL_FACTOR);
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
