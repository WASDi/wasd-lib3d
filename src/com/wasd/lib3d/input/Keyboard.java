package com.wasd.lib3d.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
            return;
        }
        switch (e.getKeyChar()) {
            case 'W':
            case 'A':
            case 'S':
            case 'D':
        }
        //Or use KeyBindings?
        //http://stackoverflow.com/questions/22741215/how-to-use-key-bindings-instead-of-key-listeners
    }
}
