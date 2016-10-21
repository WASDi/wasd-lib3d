package com.wasd.lib3d.rendering;

import com.sun.istack.internal.Nullable;
import com.wasd.lib3d.Settings;
import com.wasd.lib3d.objects.atoms.framedata.FrameData;

import java.awt.Color;

public class Colors {

    /**
     * @return null if too far away and should not be drawn.
     */
    @Nullable
    static Color colorBasedOnDistance(FrameData frameData) {
        float zDistance = frameData.getZDistanceFromCamera();
        Color oldColor = frameData.getColor();

        float fogFactor = fogFactor(zDistance);

        if (fogFactor <= 0f) {
            return oldColor;
        } else if (fogFactor >= 1f) {
            return null;
        }

        float red = oldColor.getRed() + fogFactor * (Settings.BACKGROUND_COLOR.getRed() - oldColor.getRed());
        float green = oldColor.getGreen() + fogFactor * (Settings.BACKGROUND_COLOR.getGreen() - oldColor.getGreen());
        float blue = oldColor.getBlue() + fogFactor * (Settings.BACKGROUND_COLOR.getBlue() - oldColor.getBlue());

        return new Color(
                (int) red,
                (int) green,
                (int) blue
        );
    }

    private static float fogFactor(float zDistance) {
        if (zDistance < Settings.FOG_START_DISTANCE) {
            return 0f;
        } else if (zDistance > Settings.MAX_DISTANCE_TO_RENDER) {
            return 1f;
        }
        return (zDistance - Settings.FOG_START_DISTANCE) /
                (Settings.MAX_DISTANCE_TO_RENDER - Settings.FOG_START_DISTANCE);
    }
}
