package com.wasd.lib3d.gui.input;

import com.wasd.lib3d.Settings;
import com.wasd.lib3d.animation.CameraRotationAnimation;
import com.wasd.lib3d.gui.CursorVisibilityController;
import com.wasd.lib3d.model.Float2;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {

    private static final int MIDDLE_CLICK = 2;

    private final CameraRotationAnimation cameraRotationAnimation;
    private final CursorVisibilityController cursorVisibilityController;
    private final Robot robot;

    private boolean grabMouseMode;
    private Point grabPosOnScreen;

    public Mouse(CameraRotationAnimation cameraRotationAnimation, CursorVisibilityController cursorVisibilityController) {
        this.cameraRotationAnimation = cameraRotationAnimation;
        this.cursorVisibilityController = cursorVisibilityController;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MIDDLE_CLICK) {
            grabMouseMode = !grabMouseMode;
            grabPosOnScreen = e.getLocationOnScreen();
            cursorVisibilityController.setVisible(!grabMouseMode);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMovement(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseMovement(e);
    }

    private void mouseMovement(MouseEvent e) {
        if (!grabMouseMode || e.getLocationOnScreen().equals(grabPosOnScreen)) {
            return;
        }

        int dx = e.getXOnScreen() - grabPosOnScreen.x;
        int dy = e.getYOnScreen() - grabPosOnScreen.y;

        rotateCamera(dx, dy);

        robot.mouseMove(grabPosOnScreen.x, grabPosOnScreen.y);
    }

    private void rotateCamera(int dx_int, int dy_int) {
        float dx = dx_int / Settings.RELATIVE_TO_ABSOLUTE_PIXEL_RATIO;
        float dy = dy_int / Settings.RELATIVE_TO_ABSOLUTE_PIXEL_RATIO;
        cameraRotationAnimation.rotate(new Float2(dy, dx));
    }
}
