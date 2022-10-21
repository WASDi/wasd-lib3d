package com.wasd.lib3d.animation;

public interface Animation {

    /**
     * @param absoluteTime time in seconds since first frame
     * @param deltaTime    time in seconds last frame was visible
     * @return true if repaint needed
     */
    boolean step(float absoluteTime, float deltaTime);
}
