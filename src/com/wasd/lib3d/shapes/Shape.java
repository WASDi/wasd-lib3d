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
    protected final ArrayList<Dot> dots;
    protected final ArrayList<Line> lines;

    public Shape(int numDots, int numLines) {
        dots = new ArrayList<>(numDots);
        lines = new ArrayList<>(numLines);
    }

    @Override
    public void update(Camera camera) {
        dots.forEach(dot -> dot.updateDrawable(camera));
        lines.forEach(line -> line.updateDrawable(camera));
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
            dots.forEach(dot -> renderer.renderDot(dot.getDrawable()));
        } else if (layer == Layer.LINES) {
            lines.forEach(line -> renderer.renderLine(line.getDrawable()));
        }
    }
}
