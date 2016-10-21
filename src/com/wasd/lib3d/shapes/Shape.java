package com.wasd.lib3d.shapes;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.rendering.RenderGroup;
import com.wasd.lib3d.rendering.Renderer;
import com.wasd.lib3d.shapes.primitives.Dot;
import com.wasd.lib3d.shapes.primitives.Line;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Shape implements RenderGroup {

    //TODO array instead of List? As exact size is known
    protected final ArrayList<Dot> dots;
    protected final ArrayList<Line> lines;

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
    public void renderLineLayer(Renderer renderer) {
        lines.forEach(line -> line.render(renderer));
    }

    @Override
    public void renderDotLayer(Renderer renderer) {
        dots.forEach(dot -> dot.render(renderer));
    }

    @Override
    public void renderTextLayer(Renderer renderer) {
    }
}
