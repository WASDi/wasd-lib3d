package com.wasd.lib3d.gui.input;

import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;

//TODO rename, not really a Listener
public interface CameraMovementListenerish {

    void cameraMovement(Float3 delta);

    void cameraRotation(Float2 delta);
}
