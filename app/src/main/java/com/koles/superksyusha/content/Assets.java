package com.koles.superksyusha.content;


import com.koles.superksyusha.Settings;
import com.koles.superksyusha.angin.GLGame;
import com.koles.superksyusha.graphic.Animation;
import com.koles.superksyusha.graphic.Font;
import com.koles.superksyusha.graphic.Texture;
import com.koles.superksyusha.graphic.TextureRegion;
import com.koles.superksyusha.sound.Music;
import com.koles.superksyusha.sound.Sound;


public class Assets {

    public static Texture backgroundTexture;
    public static Texture items;

    public static TextureRegion background;
    public static TextureRegion mainMenu;
    public static TextureRegion pauseMenu;
    public static TextureRegion ready;
    public static TextureRegion gameOver;
    public static TextureRegion highScoresRegion;
    public static TextureRegion logo;
    public static TextureRegion soundOn;
    public static TextureRegion soundOff;
    public static TextureRegion arrow;
    public static TextureRegion pause;
    public static TextureRegion spring;
    public static TextureRegion castle;
    public static Animation coinAnim;
    public static Animation bobJump;
    public static Animation bobFall;
    public static TextureRegion bobHit;
    public static Animation squirrelFly;
    public static TextureRegion platform;
    public static Animation brakingPlatform;
    public static Font font;
    public static Music music;
    public static Sound jumpSound;
    public static Sound highJumpSound;
    public static Sound hitSound;
    public static Sound coinSound;
    public static Sound clickSound;

    public static void load(GLGame glGame){
        backgroundTexture = new Texture(glGame, "background.png");
        background = new TextureRegion(backgroundTexture, 0, 0, 320, 480);
        items = new Texture(glGame, "texture_map.png");
        mainMenu = new TextureRegion(items, 200, 0, 180, 110);
        pauseMenu = new TextureRegion(items, 65,0,80 , 64 );
        ready = new TextureRegion(items, 310, 110, 80, 32);
        gameOver = new TextureRegion(items, 80, 100, 100, 32);
        highScoresRegion = new TextureRegion(items, 0, 215, 160, 120 / 5);
        logo = new TextureRegion(items, 0, 350, 274, 124);
        soundOn = new TextureRegion(items, 0, 0, 32, 32);
        soundOff = new TextureRegion(items, 32, 0, 32, 32);
        arrow = new TextureRegion(items, 0, 32, 32, 32);
        pause = new TextureRegion(items, 32, 32, 32, 32);
        spring = new TextureRegion(items, 270, 110, 32, 32 );
        castle = new TextureRegion(items, 0, 135, 64, 64);
        coinAnim = new Animation(0.2f,
                new TextureRegion(items,70, 65, 28, 32),
                new TextureRegion(items, 98, 65, 28, 32),
                new TextureRegion(items, 126, 65, 28, 32),
                new TextureRegion(items, 98, 65, 28, 32));
        bobJump = new Animation(0.2f,
                new TextureRegion(items, 28, 65, 14, 32),
                new TextureRegion(items, 0, 65, 14, 32));
        bobFall = new Animation(0.2f,
                new TextureRegion(items, 0, 65, 14, 32),
                new TextureRegion(items, 14, 65, 14, 32));
        bobHit = new TextureRegion(items, 42, 65, 14, 32);
        squirrelFly = new Animation(0.2f,
                new TextureRegion(items, 0, 100, 32, 32),
                new TextureRegion(items, 32, 100, 32, 32));
        platform = new TextureRegion(items, 200, 110, 64, 16);
        brakingPlatform = new Animation(0.2f,
                new TextureRegion(items, 200, 110, 64, 16),
                new TextureRegion(items, 200, 126, 64, 16),
                new TextureRegion(items, 200, 142, 64, 16));

        font = new Font(items, 0, 215, 12, 13, 24);
        music = glGame.getAudio().newMusic("music/background_sound.ogg");
        music.setLooping(true);
        music.setVolume(0.5f);
        if(Settings.soundEnabled){
            music.play();
        }

        jumpSound = glGame.getAudio().newSound("music/jamp.wav");
        highJumpSound = glGame.getAudio().newSound("music/super jamp.wav");
        hitSound = glGame.getAudio().newSound("music/bump.wav");
        coinSound = glGame.getAudio().newSound("music/coin.wav");
        clickSound = glGame.getAudio().newSound("music/click_2.ogg");
    }

    public static void reload(){
        backgroundTexture.reload();
        items.reload();
        if(Settings.soundEnabled){
            music.play();
        }
    }

    public static void playSound(Sound sound){
        if(Settings.soundEnabled){
            sound.play(1);
        }
    }


}
