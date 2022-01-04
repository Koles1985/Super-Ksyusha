package com.koles.superksyusha;

import com.koles.superksyusha.angin.GLScreen;
import com.koles.superksyusha.angin.Game;
import com.koles.superksyusha.content.Assets;
import com.koles.superksyusha.graphic.Camera2D;
import com.koles.superksyusha.graphic.SpriteBatcher;
import com.koles.superksyusha.input.TouchEvent;
import com.koles.superksyusha.math.OverlapTester;
import com.koles.superksyusha.math.Rectangle;
import com.koles.superksyusha.math.Vector2;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class HighscoreScreen extends GLScreen {
    Camera2D  guiCam;
    Rectangle backBounds;
    SpriteBatcher batcher;
    Vector2 touchPoint;
    String[] highScores;
    float xOffset = 0;
    public HighscoreScreen(Game game) {
        super(game);
        guiCam = new Camera2D(game.getGLGraphics(), 320, 480);
        backBounds = new Rectangle(0, 0, 64, 64);
        batcher = new SpriteBatcher(game.getGLGraphics(), 100);
        highScores = new String[5];
        for(int i = 0; i < 5; i++){
            highScores[i] = (i + 1) + ". " + Settings.highscores[i];
            xOffset = Math.max(highScores[i].length() * Assets.font.glyphWidth, xOffset);
        }
        xOffset = 160 - xOffset / 2;
        touchPoint = new Vector2();
    }

    @Override
    public void resume() {

    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++){
            TouchEvent event = touchEvents.get(i);
            touchPoint.set(event.getX(), event.getY());
            System.out.println("Event.x = " + touchPoint.x);
            System.out.println("Event.y = " + touchPoint.y);
            guiCam.touchToWorld(touchPoint);

            if(event.getType() == TouchEvent.TOUCH_UP){
                if(OverlapTester.pointInRectangle(backBounds, touchPoint)){
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = game.getGLGraphics().getGl10();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();

        gl.glEnable(GL10.GL_TEXTURE_2D);

        batcher.beginBatch(Assets.backgroundTexture);
        batcher.drawSprite(160, 240, 320, 480, Assets.background);
        batcher.endBatch();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        batcher.beginBatch(Assets.items);
        batcher.drawSprite(160, 360, 300, 33, Assets.highScoresRegion);

        float y = 240;
        for(int i = 4; i >= 0; i--){
            Assets.font.drawText(batcher, highScores[i], xOffset, y);
            y += Assets.font.glyphHeight;
        }

        batcher.drawSprite(32, 32, 64, 64, Assets.arrow);
        batcher.endBatch();

        gl.glDisable(GL10.GL_BLEND);
    }

    @Override
    public void pause() {

    }

    @Override
    public void dispose() {

    }
}
