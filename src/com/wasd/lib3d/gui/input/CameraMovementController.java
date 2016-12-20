package com.wasd.lib3d.gui.input;

import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;

public interface CameraMovementController {

    void cameraMovement(Float3 delta);

    void cameraRotation(Float2 delta);
}
