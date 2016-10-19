package com.wasd;

import com.wasd.lib3d.World;
import com.wasd.lib3d.gui.Window3D;
import com.wasd.lib3d.misc.Functions;
import com.wasd.lib3d.shapes.Box;
import com.wasd.lib3d.shapes.Plot;
import com.wasd.lib3d.shapes.Sphere;
import com.wasd.lib3d.shapes.primitives.Text;

import java.awt.Color;

public class Main {

    public static void main(String[] args) {
        //MAYBE TODO : port to OpenCL ! Calculations are easy, but what about rendering?

        World world = new World();

        addSomeBoxes(world);
        world.add(new Sphere(-.15f, .15f, .8f, .5f).withDotColor(Color.GREEN).withLineColor(Color.BLUE));
        world.add(new Plot(.5f, .3f, .8f, .5f, Functions.gaussian).withDotColor(Color.GREEN).withLineColor(Color.YELLOW));
        world.add(new Sphere(.5f, -.3f, .8f, .3f).withDotColor(Color.BLUE).withLineColor(Color.PINK));

        world.add(new Box(-.2f, -1f, .5f, .1f).withDotColor(Color.BLUE).withLineColor(Color.WHITE));
        world.add(new Box(.2f, -1f, .5f, .1f).withDotColor(Color.BLUE).withLineColor(Color.WHITE));
        world.add(new Text(0f, -1f, .5f, "Shit"));

        world.add(new Box(0, -1f, 1f, .1f).withDotColor(Color.BLUE).withLineColor(Color.WHITE));

        world.add(new Box(0, -1f, 2f, .1f).withDotColor(Color.BLUE).withLineColor(Color.WHITE));
        world.add(new Box(0, -1f, 3f, .1f).withDotColor(Color.BLUE).withLineColor(Color.WHITE));


        Window3D wasdWindow = new Window3D(world);

        wasdWindow.letsGetStarted();
    }

    private static void addSomeBoxes(World world) {
        int numBoxes = 15;
        for (int i = 0; i < numBoxes; i++) {
            Box box = new Box(i / 10f - .3f,
                    -.3f + i / 20f,
                    .5f + i / 5f,
                    .09f);
            world.add(box.withLineColor(Color.getHSBColor((float) i / numBoxes, 1, 1)));
            world.add(box.withDotColor(Color.getHSBColor((float) i / numBoxes, .5f, .5f)));
        }
    }
}
