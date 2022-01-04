package com.koles.superksyusha;

import com.koles.superksyusha.angin.GLScreen;
import com.koles.superksyusha.angin.Game;
import com.koles.superksyusha.content.Assets;
import com.koles.superksyusha.graphic.Camera2D;
import com.koles.superksyusha.graphic.GLGraphics;
import com.koles.superksyusha.graphic.SpriteBatcher;
import com.koles.superksyusha.input.TouchEvent;
import com.koles.superksyusha.math.OverlapTester;
import com.koles.superksyusha.math.Rectangle;
import com.koles.superksyusha.math.Vector2;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class MainMenuScreen extends GLScreen {
    GLGraphics glGraphics;
    Camera2D guiCam;
    SpriteBatcher batcher;
    Rectangle soundBounds;
    Rectangle playBounds;
    Rectangle highscoresBounds;
    Rectangle helpBounds;
    Vector2 touchPoint;


    public MainMenuScreen(Game game){
        super(game);
        glGraphics = game.getGLGraphics();
        guiCam = new Camera2D(glGraphics, 320, 480);
        batcher = new SpriteBatcher(glGraphics, 100);
        soundBounds = new Rectangle(0,0, 64, 64);
        playBounds = new Rectangle(10, 200 + 18, 300, 36);
        highscoresBounds = new Rectangle(10, 200 - 18, 300, 36);
        helpBounds = new Rectangle(10, 200 - 18 - 36, 300, 36);
        touchPoint = new Vector2();
        System.out.println("MainMenu Go!");
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++){
            TouchEvent event = touchEvents.get(i);
            if(event.getType() == TouchEvent.TOUCH_UP){
                touchPoint.set(event.getX(), event.getY());
                guiCam.touchToWorld(touchPoint);
                System.out.println("Касание х = " + touchPoint.x);
                System.out.println("Касание y = " + touchPoint.y);

                if(OverlapTester.pointInRectangle(playBounds, touchPoint)){
                    Assets.playSound(Assets.clickSound);
                    game.setScreen(new GameScreen(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(highscoresBounds, touchPoint)){
                    Assets.playSound(Assets.clickSound);
                    game.setScreen(new HighscoreScreen(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(helpBounds, touchPoint)){
                    Assets.playSound(Assets.clickSound);
                    game.setScreen(new HelpScreen(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(soundBounds, touchPoint)){
                    Assets.playSound(Assets.clickSound);
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if(Settings.soundEnabled){
                        Assets.music.play();
                    }else{
                        Assets.music.pause();
                    }
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGl10();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();
        gl.glEnable(GL10.GL_TEXTURE_2D);

        batcher.beginBatch(Assets.backgroundTexture);
        batcher.drawSprite(160, 240, 320, 480, Assets.background);
        batcher.endBatch();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        batcher.beginBatch(Assets.items);
        batcher.drawSprite(160, 480 - 10 - 71, 274, 142, Assets.logo );
        batcher.drawSprite(160, 200, 300, 110, Assets.mainMenu);
        batcher.drawSprite(32, 32, 64, 64,
                Settings.soundEnabled ? Assets.soundOn : Assets.soundOff);
        batcher.endBatch();

        gl.glDisable(GL10.GL_BLEND);
    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {
        Settings.save(game.getFileIO());
    }

    @Override
    public void dispose() {
        GL10 gl = glGraphics.getGl10();
        gl.glDisable(GL10.GL_TEXTURE_2D);
        gl.glClearColor(1,0,0,1);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

    }
}
