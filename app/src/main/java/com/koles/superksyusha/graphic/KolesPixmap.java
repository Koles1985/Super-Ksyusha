package com.koles.superksyusha.graphic;

import android.graphics.Bitmap;
import com.koles.superksyusha.graphic.Graphics.PixmapFormat;

public class KolesPixmap implements Pixmap{
    private Bitmap bitmap;
    private PixmapFormat format;

    public KolesPixmap(Bitmap bitmap, PixmapFormat format){
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public PixmapFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }
}
