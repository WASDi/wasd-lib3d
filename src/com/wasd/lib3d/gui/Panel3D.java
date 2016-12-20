package com.wasd.lib3d.gui;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.World;
import com.wasd.lib3d.gui.input.CameraMovementController;
import com.wasd.lib3d.misc.Maths;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;
import com.wasd.lib3d.rendering.GraphicsWrapper;
import com.wasd.lib3d.rendering.Renderer;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;

public class Panel3D extends JPanel implements CameraMovementController {

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

    @Override
    public void cameraMovement(Float3 delta) {
        float cosRotX = Maths.cos(camera.getRotation().x);
        float sinRotX = Maths.sin(camera.getRotation().x);

        float cosRotY = Maths.cos(camera.getRotation().y);
        float sinRotY = Maths.sin(camera.getRotation().y);

        float dx = (delta.x * cosRotY + delta.z * sinRotY) * cosRotX;
        float dy = delta.y + delta.z * sinRotX;
        float dz = (-delta.x * sinRotY + delta.z * cosRotY) * cosRotX;

        camera.relativeMovement(dx, dy, dz);
        repaint();
    }

    @Override
    public void cameraRotation(Float2 delta) {
        camera.relativeRotation(delta.x, delta.y);
        repaint();
    }
}
