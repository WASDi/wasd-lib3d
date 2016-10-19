package com.wasd.lib3d.gui;

import com.wasd.lib3d.MouseForUpdatingCamera;
import com.wasd.lib3d.World;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.HeadlessException;

public class Window3D extends JFrame {

    public static final Dimension DEFAULT_SIZE = new Dimension(1280, 720);

    private final Panel3D panel3D;
    private final MouseForUpdatingCamera mouseForUpdatingCamera;

    public Window3D(World world) throws HeadlessException {
        super("WASD 3D Window");
        setSize(DEFAULT_SIZE);
        panel3D = new Panel3D(world);
        add(panel3D);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mouseForUpdatingCamera = new MouseForUpdatingCamera(panel3D);
        panel3D.addFullMouseListener(mouseForUpdatingCamera);
    }

    public void letsGetStarted() {
        setVisible(true);
    }
}
