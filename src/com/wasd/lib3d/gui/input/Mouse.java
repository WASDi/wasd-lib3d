package com.wasd.lib3d.gui.input;

import com.wasd.lib3d.Settings;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Mouse extends MouseAdapter {

    private static final float SCROLL_FACTOR = .015f;

    public static final int LEFT_CLICK = 1;
    public static final int RIGHT_CLICK = 3;

    private final CameraMovementListenerish cameraMovementListenerish;

    private int lastPosX = 0;
    private int lastPosY = 0;
    private int mode = 0;

    public Mouse(CameraMovementListenerish panelContainingCamera) {
        this.cameraMovementListenerish = panelContainingCamera;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mode = e.getButton();
        lastPosX = e.getX();
        lastPosY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mode = 0;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int dx = e.getX() - lastPosX;
        int dy = e.getY() - lastPosY;

        relativeDrag(dx, dy);

        lastPosX = e.getX();
        lastPosY = e.getY();
    }

    private void relativeDrag(int dx_int, int dy_int) {
        float dx = -dx_int / Settings.RELATIVE_TO_ABSOLUTE_PIXEL_RATIO;
        float dy = -dy_int / Settings.RELATIVE_TO_ABSOLUTE_PIXEL_RATIO;
        if (mode == LEFT_CLICK) {
            cameraMovementListenerish.cameraMovement(new Float3(dx, dy, 0));
        } else if (mode == RIGHT_CLICK) {
            cameraMovementListenerish.cameraRotation(new Float2(dy, dx));
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        float dz = -e.getWheelRotation() * e.getScrollAmount() * SCROLL_FACTOR;
        cameraMovementListenerish.cameraMovement(new Float3(0, 0, dz));
    }
}
