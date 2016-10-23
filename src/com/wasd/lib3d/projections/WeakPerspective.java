package com.wasd.lib3d.projections;

import com.wasd.lib3d.Camera;
import com.wasd.lib3d.misc.Maths;
import com.wasd.lib3d.model.Float2;
import com.wasd.lib3d.model.Float3;

public class WeakPerspective implements Projection {

    private static final float MIN_Z_DISTANCE_FROM_CAMERA = .01f;

    private static final boolean ROTATION_DISABLED = true;

    @Override
    public Float2 locationOnScreen(Camera camera, Float3 pos) {
        Float3 delta = delta(camera, pos);
        if (delta.z < MIN_Z_DISTANCE_FROM_CAMERA) {
            return null;
        }
        float factor = 1 / (delta.z);

        float x = factor * (delta.x);
        float y = factor * (delta.y);

        return new Float2(x, y);
    }

    private Float3 delta(Camera camera, Float3 pos) {
        Float3 relativePos = new Float3(pos.x - camera.getX(), pos.y - camera.getY(), pos.z - camera.getZ());
        if (ROTATION_DISABLED) {
            return relativePos;
        }
        Float3 deltaAfterYRotation = deltaAfterYRotation(camera.getRotation().y, relativePos);
        return deltaAfterXRotation(camera.getRotation().x, deltaAfterYRotation);
    }

    private Float3 deltaAfterYRotation(float cameraRotation, Float3 relativePos) {
        Float2 xzPlane = new Float2(relativePos.x, relativePos.z);
        float xzDistance = xzPlane.length();

        float angleRelativeToCamera = Maths.atan(relativePos.x / relativePos.z);
        float v = angleRelativeToCamera - cameraRotation;
        float dx = Maths.sin(v) * xzDistance;
        float dz = Maths.cos(v) * xzDistance;

        return new Float3(dx, relativePos.y, dz);
    }

    private Float3 deltaAfterXRotation(float cameraRotation, Float3 relativePos) {
        Float2 yzPlane = new Float2(relativePos.y, relativePos.z);
        float yzDistance = yzPlane.length();

        float angleRelativeToCamera = Maths.atan(relativePos.y / relativePos.z);
        float v = angleRelativeToCamera - cameraRotation;
        float dy = Maths.sin(v) * yzDistance;
        float dz = Maths.cos(v) * yzDistance;

        return new Float3(relativePos.x, dy, dz);
    }
}
