package com.wasd.lib3d.gui;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.World;
import com.wasd.lib3d.gui.input.Mouse;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.rendering.GraphicsWrapper;
import com.wasd.lib3d.rendering.Renderer;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;

public class Panel3D extends JPanel {

    private final World world;
    private final Camera camera;

    public Panel3D(World world) {
        this.world = world;
        camera = new Camera();

        setBackground(Settings.BACKGROUND_COLOR);
    }

    public void addFullMouseListener(MouseAdapter mouseAdapter) {
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        addMouseWheelListener(mouseAdapter);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        Renderer renderer = new Renderer(new GraphicsWrapper(graphics), getWidth(), getHeight());
        world.render(renderer, camera);
    }

    public void onMouseDrag(int dx_int, int dy_int, int mode) {
        float dx = -dx_int / Settings.RELATIVE_TO_ABSOLUTE_PIXEL_RATIO;
        float dy = -dy_int / Settings.RELATIVE_TO_ABSOLUTE_PIXEL_RATIO;
        switch (mode) {
            //TODO refactor
            case Mouse.LEFT_CLICK:
                camera.relativeMovement(dx, dy, 0);
                break;
            case Mouse.RIGHT_CLICK:
                camera.relativeRotation(dy, dx);
                break;
            default:
                return;
        }
        repaint();
    }

    public void cameraXZMovement(Float2 delta) {
        camera.relativeMovement(
                delta.x * Settings.WASD_MOVEMENT_FACTOR,
                0,
                delta.y * Settings.WASD_MOVEMENT_FACTOR
        );
        repaint();
    }

    public void onMouseScroll(float scrollAmount) {
        camera.relativeMovement(0, 0, scrollAmount);
        repaint();
    }

}
