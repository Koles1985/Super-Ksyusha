package com.koles.superksyusha.graphic;

import com.koles.superksyusha.graphic.Graphics.PixmapFormat;

public interface Pixmap {
    int getWidth();
    int getHeight();
    PixmapFormat getFormat();
    void dispose();
}
