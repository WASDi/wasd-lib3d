package com.wasd.lib3d.gui.input;

import com.wasd.lib3d.Settings;
import com.wasd.lib3d.gui.CursorVisibilityController;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Mouse extends MouseAdapter {

    private static final float SCROLL_FACTOR = .015f;

    public static final int LEFT_CLICK = 1;
    public static final int MIDDLE_CLICK = 2;
    public static final int RIGHT_CLICK = 3;

    private final CameraMovementController cameraMovementController;
    private final CursorVisibilityController cursorVisibilityController;

    private int lastPosX = 0;
    private int lastPosY = 0;
    private int mode = 0;

    private final Robot robot;
    private boolean grabMouseMode; //TODO should be a strategy
    private Point grabPosOnScreen;

    public Mouse(CameraMovementController panelContainingCamera, CursorVisibilityController cursorVisibilityController) {
        this.cameraMovementController = panelContainingCamera;
        this.cursorVisibilityController = cursorVisibilityController;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mode = e.getButton();
        lastPosX = e.getX();
        lastPosY = e.getY();
        if (mode == MIDDLE_CLICK) {
            grabMouseMode = !grabMouseMode;
            grabPosOnScreen = e.getLocationOnScreen();
            cursorVisibilityController.setVisible(!grabMouseMode);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mode = 0;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (grabMouseMode) {
            movementWhenMouseGrab(e);
            return;
        }

        int dx = e.getX() - lastPosX;
        int dy = e.getY() - lastPosY;

        relativeDrag(dx, dy);

        lastPosX = e.getX();
        lastPosY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (grabMouseMode) {
            movementWhenMouseGrab(e);
        }
    }

    private void movementWhenMouseGrab(MouseEvent e) {
        if (e.getLocationOnScreen().equals(grabPosOnScreen)) {
            return;
        }

        int dx = e.getXOnScreen() - grabPosOnScreen.x;
        int dy = e.getYOnScreen() - grabPosOnScreen.y;

        relativeDrag(dx, dy);
        robot.mouseMove(grabPosOnScreen.x, grabPosOnScreen.y);
    }

    private void relativeDrag(int dx_int, int dy_int) {
        float dx = dx_int / Settings.RELATIVE_TO_ABSOLUTE_PIXEL_RATIO;
        float dy = dy_int / Settings.RELATIVE_TO_ABSOLUTE_PIXEL_RATIO;
        if (grabMouseMode) {
            cameraMovementController.cameraRotation(new Float2(dy, dx));
        } else if (mode == LEFT_CLICK) {
            cameraMovementController.cameraRotation(new Float2(-dy, -dx));
        } else if (mode == RIGHT_CLICK) {
            cameraMovementController.cameraMovement(new Float3(-dx, -dy, 0));
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        float dz = -e.getWheelRotation() * e.getScrollAmount() * SCROLL_FACTOR;
        cameraMovementController.cameraMovement(new Float3(0, 0, dz));
    }
}
