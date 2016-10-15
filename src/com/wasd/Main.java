package com.wasd;

import com.wasd.lib3d.Window3D;
import com.wasd.lib3d.shapes.Box;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Window3D wasdWindow = new Window3D();

        addSomeBoxes(wasdWindow);

        wasdWindow.letsGetStarted();
    }

    private static void addSomeBoxes(Window3D wasdWindow) {
        Color[][] boxColors = {{Color.RED, Color.GREEN}, {Color.BLUE, Color.YELLOW}};

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                wasdWindow.addShape(
                        new Box(x / 4f - .125f,
                                y / 4f - .125f,
                                .7f,
                                .1f).withColor(boxColors[x][y])
                );
            }
        }
    }
}
