package com.wasd.lib3d.objects;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.objects.primitives.Dot;
import com.wasd.lib3d.objects.primitives.Line;
import com.wasd.lib3d.rendering.RenderGroup;
import com.wasd.lib3d.rendering.Renderer;

import java.awt.Color;
import java.util.ArrayList;

public abstract class BasicShape implements RenderGroup {

    //TODO array instead of List? As exact size is known
    protected final ArrayList<Dot> dots;
    protected final ArrayList<Line> lines;

    public BasicShape(int numDots, int numLines) {
        dots = new ArrayList<>(numDots);
        lines = new ArrayList<>(numLines);
    }

    @Override
    public void update(Camera camera) {
        dots.forEach(dot -> dot.update(camera));
        lines.forEach(line -> line.update(camera));
    }

    public BasicShape withDotColor(Color color) {
        dots.forEach(dot -> dot.getFrameData().setColor(color));
        return this;
    }

    public BasicShape withLineColor(Color color) {
        lines.forEach(line -> line.getFrameData().setColor(color));
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
