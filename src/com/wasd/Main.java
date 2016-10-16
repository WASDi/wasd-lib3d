package com.wasd;

import com.wasd.lib3d.Window3D;
import com.wasd.lib3d.shapes.Box;
import com.wasd.lib3d.shapes.Sphere;

import java.awt.Color;

public class Main {

    public static void main(String[] args) {
        Window3D wasdWindow = new Window3D();

        addSomeBoxes(wasdWindow);
        wasdWindow.addShape(new Box(-.1f, .15f, .5f, .2f).withDotColor(Color.PINK));
        wasdWindow.addShape(new Sphere(-.1f, .15f, .5f, .2f).withDotColor(Color.MAGENTA));

        wasdWindow.letsGetStarted();
    }

    private static void addSomeBoxes(Window3D wasdWindow) {
        int numBoxes = 15;
        for (int i = 0; i < numBoxes; i++) {
            Box box = new Box(i / 7f - 1f,
                    -.3f + i / 50f,
                    1f,
                    .1f);
            wasdWindow.addShape(box.withDotColor(Color.getHSBColor((float) i / numBoxes, 1, 1)));
        }
    }
}
