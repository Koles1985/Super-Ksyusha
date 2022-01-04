package com.koles.superksyusha.angin;

import com.koles.superksyusha.angin.GLGame;
import com.koles.superksyusha.angin.Game;
import com.koles.superksyusha.angin.Screen;
import com.koles.superksyusha.graphic.GLGraphics;

public abstract class GLScreen extends Screen {
    protected final GLGraphics glGraphics;
    protected final GLGame glGame;
     public GLScreen(Game game){
         super(game);
         glGame = (GLGame)game;
         glGraphics = ((GLGame)game).getGLGraphics();
     }
}
