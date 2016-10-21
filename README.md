# wasd-lib3d
Primitive 3D graphics.

###### Basic concepts

* World: Contains every 3D object to be rendered.
* Object3D: A 3D object, consisting of one or more PrimitiveShape.
* PrimitiveShape/Renderable: A fundamental 3D-shape which can be projected onto a 2D surface.
* FrameData: Represents how a Renderable (3D) should be drawn on a 2D surface (your monitor). Valid for one frame

The most interesting part, translates a 3D position to 2D based on camera position:

    float factor = 1 / (pos.z - camera.getZ());
    float x = factor * (pos.x - camera.getX());
    float y = factor * (pos.y - camera.getY());

Currently found in Projection.java. Used in renderDot() in Renderer.java for final translation to destination pixel. I made it up myself but apparently it is known as [Weak perspective projection](https://en.wikipedia.org/wiki/3D_projection#Weak_perspective_projection).
