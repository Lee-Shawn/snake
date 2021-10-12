package com.laughing.snake.view.ui;

import java.awt.*;

/**
 * @author : laughing
 * @create : 2021-10-01 23:12
 * @description : 排行榜
 */
public class LayerDisk extends LayerData {
    public LayerDisk(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void paint(Graphics g) {
        this.createWindow(g);
        this.showData(Img.IMG_LIST, this.gameDTO.getList(), g);
    }
}
