# wasd-lib3d
Primitive 3D graphics.

###### Basic concepts

* Shape: Collection of PrimitiveShape.
* PrimitiveShape: A fundamental 3D-shape, dot or line.
* Drawable: Represents how a PrimitiveShape (3D) should be drawn on a 2D surface (your monitor).

The most intersting part, translates a 3D position to 2D based on camera position:

    float factor = 1 / (pos.z - camera.getZ());
    float x = factor * (pos.x - camera.getX());
    float y = factor * (pos.y - camera.getY());

Currently found in Projection.java. Used in renderDot() in Renderer.java for final translation to destination pixel. I made it up myself but apparently it is known as [Weak perspective projection](https://en.wikipedia.org/wiki/3D_projection#Weak_perspective_projection).
