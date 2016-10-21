package com.wasd.lib3d.objects;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.objects.primitives.Dot;
import com.wasd.lib3d.objects.primitives.Line;
import com.wasd.lib3d.rendering.Renderer;

import java.awt.Color;
import java.util.ArrayList;

public abstract class AbstractShape implements Object3D {

    //TODO array instead of List? As exact size is known
    protected final ArrayList<Dot> dots;
    protected final ArrayList<Line> lines;

    public AbstractShape(int numDots, int numLines) {
        dots = new ArrayList<>(numDots);
        lines = new ArrayList<>(numLines);
    }

    @Override
    public void update(Camera camera) {
        dots.forEach(dot -> dot.update(camera));
        lines.forEach(line -> line.update(camera));
    }

    public AbstractShape withDotColor(Color color) {
        dots.forEach(dot -> dot.getFrameData().setColor(color));
        return this;
    }

    public AbstractShape withLineColor(Color color) {
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
