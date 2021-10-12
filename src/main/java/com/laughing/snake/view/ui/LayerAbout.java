package com.laughing.snake.view.ui;

import java.awt.*;

/**
 * @author : laughing
 * @create : 2021-09-13 20:56
 * @description : 版权标识
 */
public class LayerAbout extends Layer {
    public LayerAbout(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void paint(Graphics g) {
        this.createWindow(g);
        this.drawImageAtCenter(Img.IMG_SIGN, g);
    }
}
