package com.koles.superksyusha.input;

import java.util.List;

public interface Input {
    boolean isKeyPressed(int keyCode);
    boolean isTouchDown(int pointer);
    int touchX(int pointer);
    int touchY(int pointer);
    float getAccelX();
    float getAccelY();
    float getAccelZ();
    List<KeyEvent> getKeyEvents();
    List<TouchEvent> getTouchEvents();
}
