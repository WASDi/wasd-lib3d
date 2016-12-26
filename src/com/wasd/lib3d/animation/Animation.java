package com.wasd.lib3d.animation;

public interface Animation {

    /**
     * @param t time in seconds for frame
     * @return true if repaint needed
     */
    boolean step(float t);
}
