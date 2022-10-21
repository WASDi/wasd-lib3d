package com.wasd.lib3d.objects.atoms;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.objects.atoms.framedata.FrameData;
import com.wasd.lib3d.rendering.Renderer;

public interface Atom<FRAMEDATA extends FrameData> {

    void update(Camera camera);

    FRAMEDATA getFrameData();

    void render(Renderer renderer);

}