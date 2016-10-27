package com.wasd.lib3d.input;

import com.wasd.lib3d.gui.Panel3D;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Mouse extends MouseAdapter {

    private static final float SCROLL_FACTOR = .015f;

    public static final int LEFT_CLICK = 1;
    public static final int RIGHT_CLICK = 3;

    private final Panel3D cameraPanel;

    private int lastPosX = 0;
    private int lastPosY = 0;
    private int mode = 0;

    public Mouse(Panel3D panelContainingCamera) {
        this.cameraPanel = panelContainingCamera;
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

    private void relativeDrag(int dx, int dy) {
        cameraPanel.onMouseDrag(dx, dy, mode);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        cameraPanel.onMouseScroll(-e.getWheelRotation() * e.getScrollAmount() * SCROLL_FACTOR);
    }
}
