package com.wasd.lib3d.gui;

import java.awt.Cursor;
import java.util.function.Consumer;

public class CursorVisibilityController {

    private final Cursor normalCursor;
    private final Cursor invisibleCursor;

    private final Consumer<Cursor> cursorSettingFunction;

    public CursorVisibilityController(Cursor normalCursor, Cursor invisibleCursor, Consumer<Cursor> cursorSettingFunction) {
        this.normalCursor = normalCursor;
        this.invisibleCursor = invisibleCursor;
        this.cursorSettingFunction = cursorSettingFunction;
    }

    public void setVisible(boolean visible) {
        cursorSettingFunction.accept(visible ? normalCursor : invisibleCursor);
    }
}
