package com.wasd.lib3d;

import com.wasd.lib3d.shapes.Shape;

import javax.swing.*;
import java.awt.*;

public class Window3D extends JFrame {

    public static final Dimension DEFAULT_SIZE = new Dimension(1280, 720);

    private final Panel3D panel3D;
    private final MouseForUpdatingCamera mouseForUpdatingCamera;

    public Window3D() throws HeadlessException {
        super("WASD 3D Window");
        setSize(DEFAULT_SIZE);
        panel3D = new Panel3D();
        add(panel3D);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mouseForUpdatingCamera = new MouseForUpdatingCamera(panel3D);
        panel3D.addFullMouseListener(mouseForUpdatingCamera);
    }

    public void addShape(Shape shape) {
        panel3D.addShape(shape);
    }

    public void letsGetStarted() {
        setVisible(true);
    }
}
