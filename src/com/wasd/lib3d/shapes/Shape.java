package com.wasd.lib3d.shapes;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.rendering.Layer;
import com.wasd.lib3d.rendering.Renderable;
import com.wasd.lib3d.rendering.Renderer;
import com.wasd.lib3d.shapes.primitives.Dot;
import com.wasd.lib3d.shapes.primitives.Line;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Shape implements Renderable {

    //TODO array instead of List? As exact size is known
    final ArrayList<Dot> dots;
    final ArrayList<Line> lines;

    public Shape(int numDots, int numLines) {
        dots = new ArrayList<>(numDots);
        lines = new ArrayList<>(numLines);
    }

    @Override
    public void update(Camera camera) {
        dots.forEach(dot -> dot.update(camera));
        lines.forEach(line -> line.update(camera));
    }

    public Shape withDotColor(Color color) {
        dots.forEach(dot -> dot.getDrawable().setColor(color));
        return this;
    }

    public Shape withLineColor(Color color) {
        lines.forEach(line -> line.getDrawable().setColor(color));
        return this;
    }

    @Override
    public void render(Renderer renderer, Layer layer) {
        //TODO refactor
        if (layer == Layer.DOTS) {
            for (Dot dot : dots) {
                if (dot.getDrawable().shouldRender()) {
                    renderer.render(dot.getDrawable());
                }
            }
        } else if (layer == Layer.LINES) {
            for (Line line : lines) {
                if (line.getDrawable().shouldRender()) {
                    renderer.render(line.getDrawable());
                }
            }
        }
    }
}
