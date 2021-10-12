package com.laughing.snake.view.ui;

import java.awt.*;

/**
 * @author : laughing
 * @create : 2021-09-13 20:52
 * @description : 按钮
 */
public class LayerButton extends Layer {
    public LayerButton(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void paint(Graphics g) {
        this.createWindow(g);
    }
}
