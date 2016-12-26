package com.wasd.lib3d.gui.input;

import com.wasd.lib3d.model.Float2;

/**
 * @deprecated This is probably shit, currently only used for the right-click movements
 */
@Deprecated
public interface CameraMovementController {

    void cameraRotation(Float2 delta);
}
