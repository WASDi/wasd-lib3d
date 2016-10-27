package com.wasd.lib3d.gui;

import com.wasd.lib3d.Settings;
import com.wasd.lib3d.World;
import com.wasd.lib3d.input.Keyboard;
import com.wasd.lib3d.input.Mouse;

import javax.swing.JFrame;
import java.awt.HeadlessException;

public class Window3D extends JFrame {

    private final Panel3D panel3D;
    private final Mouse mouse;

    public Window3D(World world) throws HeadlessException {
        super("WASD 3D Window");
        setSize(Settings.WINDOW_SIZE);
        panel3D = new Panel3D(world);
        add(panel3D);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mouse = new Mouse(panel3D);
        panel3D.addFullMouseListener(mouse);

        addKeyListener(new Keyboard());
    }

    public void makeVisible() {
        setVisible(true);
    }
}
