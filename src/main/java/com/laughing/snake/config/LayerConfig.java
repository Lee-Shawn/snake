package com.laughing.snake.config;

import java.io.Serializable;

/**
 * @author : laughing
 * @create : 2021-09-12 14:51
 * @description : 图层配置
 */
public class LayerConfig implements Serializable {
    /**
     * 类名
     */
    private final String className;
    /**
     * 图层 x 坐标
     */
    private final int x;
    /**
     * 图层 y 坐标
     */
    private final int y;
    /**
     * 图层宽度
     */
    private final int w;
    /**
     * 图层高度
     */
    private final int h;

    public LayerConfig(String className, int x, int y, int w, int h) {
        this.className = className;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public String getClassName() {
        return className;
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
