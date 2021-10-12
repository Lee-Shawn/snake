package com.laughing.snake.view.ui;

import java.awt.*;

/**
 * @author : laughing
 * @create : 2021-09-12 20:44
 * @description : 玩家分数
 */
public class LayerPoint extends Layer {
    /**
     * 分数最大位数
     */
    private static final int POINT_BIT = 5;

    /**
     * 分数 y 坐标
     */
    private final int pointY;

    /**
     * 等级 x 坐标
     */
    private final int levelX;

    /**
     * 等级 y 坐标
     */
    private final int levelY;

    public LayerPoint(int x, int y, int w, int h) {
        super(x, y, w, h);
        // 初始化分数显示的 y 坐标
        this.pointY = PADDING;
        this.levelX = this.w - IMG_NUMBER_W * POINT_BIT - PADDING;
        this.levelY = this.pointY + Img.IMG_POINT.getHeight(null) + PADDING;
    }

    @Override
    public void paint(Graphics g) {
        this.createWindow(g);
        // 分数文字
        g.drawImage(Img.IMG_POINT, this.x + PADDING, this.y + pointY, null);
        // 显示分数
        this.drawNumber(levelX, pointY, this.gameDTO.getGamePoint(), POINT_BIT, g);
        // 等级文字
        g.drawImage(Img.IMG_LEVEL, this.x + PADDING, this.y + pointY + (PADDING << 1), null);
        // 显示等级
        this.drawNumber(levelX, levelY, this.gameDTO.getGameLevel(), POINT_BIT, g);
    }
}
