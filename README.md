# wasd-lib3d
Primitive 3D graphics.

###### Basic concepts

* Shape: Collection of PrimitiveShape.
* PrimitiveShape: A fundamental 3D-shape, dot or line.
* Drawable: Represents how a PrimitiveShape (3D) should be drawn on a 2D surface (your monitor).

The most intersting part, translates a 3D position to 2D based on camera position:

        float factor = 1 / (z - camera.getPosZ());
        drawable.updateX(factor * (x - camera.getPosX()));
        drawable.updateY(factor * (y - camera.getPosY()));

Currently found in updateDrawable() in Dot.java. Used in drawAllDots() in Panel3D.java for final translation to destination pixel.
