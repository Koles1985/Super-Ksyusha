package com.koles.superksyusha;

import android.app.Activity;

import com.koles.superksyusha.angin.GLGame;
import com.koles.superksyusha.angin.Screen;
import com.koles.superksyusha.content.Assets;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Launcher extends GLGame {
    boolean firstTimeCreate = true;

    @Override
    public Screen getStartScreen() {
        return new MainMenuScreen(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);
        if(firstTimeCreate){
            Settings.load(getFileIO());
            Assets.load(this);
            firstTimeCreate = false;
        }else{
            Assets.reload();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(Settings.soundEnabled){
            Assets.music.pause();
        }
    }
}
