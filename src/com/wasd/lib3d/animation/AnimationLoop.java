package com.wasd.lib3d.animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AnimationLoop implements ActionListener {

    private static final float NANOS_TO_SECONDS_FACTOR = 1e-9f;

    private final Runnable repaintFunction;
    private final List<Animation> animations;

    private long startTime = 0L;
    private long lastFrameStartTime = 0L;

    public AnimationLoop(Runnable repaintFunction,
                         List<Animation> animations) {
        this.repaintFunction = repaintFunction;
        this.animations = animations;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long thisFrameStartTime = System.nanoTime();
        if (startTime == 0L) {
            startTime = thisFrameStartTime;
            return; //Don't repaint first frame with zero delta.
        }

        float absoluteTime = (thisFrameStartTime - startTime) * NANOS_TO_SECONDS_FACTOR;
        float deltaTime = (thisFrameStartTime - lastFrameStartTime) * NANOS_TO_SECONDS_FACTOR;

        boolean repaintNeeded = false;
        for (Animation animation : animations) {
            repaintNeeded |= animation.step(absoluteTime, deltaTime);
        }

        if (repaintNeeded) {
            repaintFunction.run();
        }

        lastFrameStartTime = thisFrameStartTime;
    }
}
