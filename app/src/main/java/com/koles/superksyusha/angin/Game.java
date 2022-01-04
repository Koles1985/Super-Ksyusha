package com.koles.superksyusha.angin;

import com.koles.superksyusha.graphic.GLGraphics;
import com.koles.superksyusha.graphic.Graphics;
import com.koles.superksyusha.input.Input;
import com.koles.superksyusha.io.FileIO;
import com.koles.superksyusha.sound.Audio;

public interface Game {
    Input getInput();
    FileIO getFileIO();
    Graphics getGraphics();
    GLGraphics getGLGraphics();
    Audio getAudio();
    void setScreen(Screen screen);
    Screen getCurrentScreen();
    Screen getStartScreen();
}
