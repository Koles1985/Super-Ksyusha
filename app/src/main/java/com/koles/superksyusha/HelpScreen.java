package com.koles.superksyusha;

import com.koles.superksyusha.angin.GLScreen;
import com.koles.superksyusha.angin.Game;
import com.koles.superksyusha.content.Assets;
import com.koles.superksyusha.graphic.Camera2D;
import com.koles.superksyusha.graphic.GLGraphics;
import com.koles.superksyusha.graphic.SpriteBatcher;
import com.koles.superksyusha.graphic.Texture;
import com.koles.superksyusha.graphic.TextureRegion;
import com.koles.superksyusha.input.TouchEvent;
import com.koles.superksyusha.math.OverlapTester;
import com.koles.superksyusha.math.Rectangle;
import com.koles.superksyusha.math.Vector2;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class HelpScreen extends GLScreen {
    Camera2D guiCam;
    SpriteBatcher batcher;
    Rectangle nextBounds;
    Vector2 touchPoint;
    Texture helpImage;
    TextureRegion helpRegion;
    GLGraphics glGraphics;

    public HelpScreen(Game game) {
        super(game);
        glGraphics = game.getGLGraphics();
        guiCam = new Camera2D(glGraphics, 320, 480);
        nextBounds = new Rectangle(320 - 64, 0, 64, 64);
        touchPoint = new Vector2();
        batcher = new SpriteBatcher(glGraphics, 1);
    }

    @Override
    public void resume() {
        helpImage = new Texture(glGame, "background.png");
        helpRegion = new TextureRegion(helpImage, 0, 0, 320, 480);
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = glGame.getInput().getTouchEvents();
        int len = touchEvents.size();

        for(int i = 0; i < len; i++){
            TouchEvent event = touchEvents.get(i);
            touchPoint.set(event.getX(), event.getY());
            guiCam.touchToWorld(touchPoint);

            if(event.getType() == TouchEvent.TOUCH_UP){
                if(OverlapTester.pointInRectangle(nextBounds, touchPoint)){
                    Assets.playSound(Assets.clickSound);
                    game.setScreen(new HelpScreen2(game));
                    return;
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

        batcher.beginBatch(helpImage);
        batcher.drawSprite(160, 240, 320, 480, helpRegion);
        batcher.endBatch();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        batcher.beginBatch(Assets.items);
        batcher.drawSprite(320 - 32, 32, -64, 64, Assets.arrow);
        batcher.endBatch();

        gl.glDisable(GL10.GL_BLEND);
    }

    @Override
    public void pause() {
        helpImage.dispose();
    }

    @Override
    public void dispose() {

    }
}
