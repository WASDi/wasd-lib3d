package com.wasd.lib3d;

import com.wasd.lib3d.gui.Panel3D;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MouseForUpdatingCamera extends MouseAdapter {

    private static final float SCROLL_FACTOR = .015f;

    private final Panel3D cameraPanel;

    private int lastPosX = 0;
    private int lastPosY = 0;

    public MouseForUpdatingCamera(Panel3D panelContainingCamera) {
        this.cameraPanel = panelContainingCamera;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastPosX = e.getX();
        lastPosY = e.getY();
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
        cameraPanel.onMouseDrag(dx, dy);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        cameraPanel.onMouseScroll(-e.getWheelRotation() * e.getScrollAmount() * SCROLL_FACTOR);
    }
}
