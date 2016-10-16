package com.wasd;

import com.wasd.lib3d.Window3D;
import com.wasd.lib3d.shapes.Box;

import java.awt.Color;

public class Main {

    public static void main(String[] args) {
        Window3D wasdWindow = new Window3D();

        addSomeBoxes(wasdWindow);
        wasdWindow.addShape(new Box(-.1f, .3f, .5f, .2f).withColor(Color.PINK));

        wasdWindow.letsGetStarted();
    }

    private static void addSomeBoxes(Window3D wasdWindow) {
        for (int i = 0; i < 5; i++) {
            Box box = new Box(i / 3f - 1f,
                    -.3f,
                    1f,
                    .05f + i / 50f);
            wasdWindow.addShape(box.withColor(Color.getHSBColor(i / 10f, 1, 1)));
        }
    }
}
