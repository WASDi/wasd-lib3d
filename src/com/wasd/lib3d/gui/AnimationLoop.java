package com.wasd.lib3d.gui;

import com.wasd.lib3d.Settings;
import com.wasd.lib3d.animation.Animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AnimationLoop implements ActionListener {

    private final Runnable repaintFunction;
    private final List<Animation> animations;

    public AnimationLoop(Runnable repaintFunction,
                         List<Animation> animations) {
        this.repaintFunction = repaintFunction;
        this.animations = animations;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean repaintNeeded = false;
        for (Animation animation : animations) {
            repaintNeeded |= animation.step(Settings.ANIMATION_DELAY / 1000f); //TODO calculate exact time
        }

        if (repaintNeeded) {
            repaintFunction.run();
        }
    }
}
