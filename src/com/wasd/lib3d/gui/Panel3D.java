package com.wasd.lib3d.gui;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.World;
import com.wasd.lib3d.gui.input.CameraMovementListenerish;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;
import com.wasd.lib3d.rendering.GraphicsWrapper;
import com.wasd.lib3d.rendering.Renderer;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;

public class Panel3D extends JPanel implements CameraMovementListenerish {

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
        camera.relativeMovement(delta.x, delta.y, delta.z);
        repaint();
    }

    @Override
    public void cameraRotation(Float2 delta) {
        camera.relativeRotation(delta.x, delta.y);
        repaint();
    }
}
