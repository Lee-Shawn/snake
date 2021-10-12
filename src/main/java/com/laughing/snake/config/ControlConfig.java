package com.laughing.snake.config;

import java.io.Serializable;

/**
 * @author : laughing
 * @create : 2021-10-06 15:17
 * @description : 控制按钮配置
 */
public class ControlConfig implements Serializable {
    /**
     * 控制按钮索引
     */
    private final int index;
    /**
     * 控制按钮 x 坐标
     */
    private final int x;
    /**
     * 控制按钮 y 坐标
     */
    private final int y;
    /**
     * 控制按钮宽度
     */
    private final int w;
    /**
     * 控制按钮高度
     */
    private final int h;

    public ControlConfig(int index, int x, int y, int w, int h) {
        this.index = index;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public int getIndex() {
        return index;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
