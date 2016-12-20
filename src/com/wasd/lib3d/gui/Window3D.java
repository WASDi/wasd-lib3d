package com.wasd.lib3d.gui;

import com.wasd.lib3d.Settings;
import com.wasd.lib3d.World;
import com.wasd.lib3d.gui.input.Mouse;
import com.wasd.lib3d.gui.input.MovementModifier;
import com.wasd.lib3d.gui.input.WASDKey;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.HeadlessException;
import java.util.List;

public class Window3D extends JFrame {

    private final Panel3D panel3D;
    private final Mouse mouse;
    private final Timer animationTimer;

    public Window3D(World world) throws HeadlessException {
        super("WASD 3D Window");
        setSize(Settings.WINDOW_SIZE);
        panel3D = new Panel3D(world);
        add(panel3D);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mouse = new Mouse(panel3D);
        panel3D.addFullMouseListener(mouse);

        List<MovementModifier> movementModifiers = WASDKey.generateMovementModifiers(panel3D);
        AnimationLoop animationLoop = new AnimationLoop(panel3D, panel3D.getCamera(), movementModifiers);

        animationTimer = new Timer(Settings.ANIMATION_DELAY, animationLoop);
        animationTimer.start();
    }

    public void makeVisible() {
        setVisible(true);
    }
}
