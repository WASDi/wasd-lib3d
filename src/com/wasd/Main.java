package com.wasd;

import com.wasd.lib3d.Functions;
import com.wasd.lib3d.Window3D;
import com.wasd.lib3d.shapes.Box;
import com.wasd.lib3d.shapes.Plot;
import com.wasd.lib3d.shapes.Sphere;

import java.awt.Color;
import java.util.function.BiFunction;

public class Main {

    public static void main(String[] args) {
        Window3D wasdWindow = new Window3D();

        BiFunction<Float, Float, Float> plotFunction = Functions.gaussian;

        //addSomeBoxes(wasdWindow);
        //wasdWindow.addShape(new Sphere(-.15f, .15f, .8f, .5f).withDotColor(Color.GREEN).withLineColor(Color.BLUE));
        //wasdWindow.addShape(new Sphere(1, .15f, .8f, .3f).withDotColor(Color.GREEN).withLineColor(Color.YELLOW));
        wasdWindow.addShape(new Plot(.5f, .3f, .8f, .5f, plotFunction).withDotColor(Color.GREEN).withLineColor(Color.YELLOW));
        wasdWindow.addShape(new Sphere(.5f, -.3f, .8f, .3f).withDotColor(Color.BLUE).withLineColor(Color.PINK));

        wasdWindow.letsGetStarted();
    }

    private static void addSomeBoxes(Window3D wasdWindow) {
        int numBoxes = 15;
        for (int i = 0; i < numBoxes; i++) {
            Box box = new Box(i / 10f - .3f,
                    -.3f + i / 20f,
                    .5f + i / 5f,
                    .09f);
            wasdWindow.addShape(box.withLineColor(Color.getHSBColor((float) i / numBoxes, 1, 1)));
            wasdWindow.addShape(box.withDotColor(Color.getHSBColor((float) i / numBoxes, .5f, .5f)));
        }
    }
}
