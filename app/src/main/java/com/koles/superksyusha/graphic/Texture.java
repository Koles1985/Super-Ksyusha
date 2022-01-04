package com.koles.superksyusha.graphic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.koles.superksyusha.angin.GLGame;
import com.koles.superksyusha.io.FileIO;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

public class Texture {
    private GLGraphics glGraphics;
    private FileIO fileIO;
    private String fileName;
    private int textureId;
    private int minFilter;
    private int magFilter;
    private float width;
    private float height;

    public Texture(GLGame glGame, String fileName){
        this.glGraphics = glGame.getGLGraphics();
        this.fileIO = glGame.getFileIO();
        this.fileName = fileName;
        load();
    }

    public void load(){
        GL10 gl = glGraphics.getGl10();
        int[] textureIds = new int[1];
        gl.glGenTextures(1, textureIds, 0);
        textureId = textureIds[0];

        InputStream in = null;
        try{
            in = fileIO.readAssets(fileName);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            width = bitmap.getWidth();
            height = bitmap.getHeight();
            gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
            setFilters(GL10.GL_NEAREST, GL10.GL_NEAREST);
            gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
        }catch(IOException e){
            throw new RuntimeException("Couldn't load texture '"
            + fileName + "'", e);
        }finally{
            if(in != null){
                try{
                    in.close();
                }catch(IOException e){

                }
            }
        }
    }

    public void reload(){
        load();
        bind();
        setFilters(minFilter, magFilter);
        glGraphics.getGl10().glBindTexture(GL10.GL_TEXTURE_2D, 0);
    }

    public void setFilters(int minFilter, int magFilter){
        this.minFilter = minFilter;
        this.magFilter = magFilter;
        GL10 gl = glGraphics.getGl10();
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, minFilter);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, magFilter);
    }

    public void bind(){
        GL10 gl = glGraphics.getGl10();
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
    }

    public float getWidth(){
        if(width == 0){
            return 1.0f;
        }else {
            return width;
        }
    }

    public float getHeight(){
        if(height == 0){
            return 1.0f;
        }else {
            return height;
        }
    }

    public void dispose(){
        GL10 gl = glGraphics.getGl10();
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
        int[] textureIds = {textureId};
        gl.glDeleteTextures(1, textureIds, 0);
    }


}
