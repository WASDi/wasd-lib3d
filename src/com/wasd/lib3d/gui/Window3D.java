package com.wasd.lib3d.gui;

import com.wasd.lib3d.Keyboard;
import com.wasd.lib3d.MouseForCamera;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.World;

import javax.swing.JFrame;
import java.awt.HeadlessException;

public class Window3D extends JFrame {

    private final Panel3D panel3D;
    private final MouseForCamera mouseForCamera;

    public Window3D(World world) throws HeadlessException {
        super("WASD 3D Window");
        setSize(Settings.WINDOW_SIZE);
        panel3D = new Panel3D(world);
        add(panel3D);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mouseForCamera = new MouseForCamera(panel3D);
        panel3D.addFullMouseListener(mouseForCamera);

        addKeyListener(new Keyboard());
    }

    public void makeVisible() {
        setVisible(true);
    }
}
