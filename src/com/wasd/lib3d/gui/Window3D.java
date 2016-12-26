package com.wasd.lib3d.gui;

import com.wasd.lib3d.Settings;
import com.wasd.lib3d.World;
import com.wasd.lib3d.animation.Animation;
import com.wasd.lib3d.gui.input.Mouse;
import com.wasd.lib3d.gui.input.MovementKey;
import com.wasd.lib3d.gui.input.MovementModifier;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

public class Window3D extends JFrame {

    private final Panel3D panel3D;
    private final Mouse mouse;
    private final Timer animationTimer;

    public Window3D(World world, List<Animation> animations) throws HeadlessException {
        super("WASD 3D Window");
        setSize(Settings.WINDOW_SIZE);
        panel3D = new Panel3D(world);
        add(panel3D);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mouse = new Mouse(panel3D,
                          new CursorVisibilityController(getCursor(),
                                                         getInvisibleCursor(),
                                                         this::setCursor));
        panel3D.addFullMouseListener(mouse);

        List<MovementModifier> movementModifiers = MovementKey.generateMovementModifiers(panel3D);
        AnimationLoop animationLoop = new AnimationLoop(panel3D::repaint,
                                                        panel3D.getCamera(),
                                                        movementModifiers,
                                                        animations);

        animationTimer = new Timer(Settings.ANIMATION_DELAY, animationLoop);
        animationTimer.start();
    }

    private Cursor getInvisibleCursor() {
        return getToolkit()
                .createCustomCursor(
                        new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
                        new Point(0, 0),
                        "null");
    }

    public void makeVisible() {
        setVisible(true);
    }
}
