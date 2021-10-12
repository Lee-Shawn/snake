package com.laughing.snake.config;

import org.dom4j.Element;

import java.io.Serializable;

/**
 * @author : laughing
 * @create : 2021-09-12 14:54
 * @description : 按钮属性
 */
public class ButtonConfig implements Serializable {
    /**
     * 按钮框边框宽度
     */
    private final int buttonW;
    /**
     * 按钮框边框高度
     */
    private final int buttonH;
    /**
     * 开始按钮 x 坐标
     */
    private final int startX;
    /**
     * 开始按钮 y 坐标
     */
    private final int startY;
    /**
     * 重新游戏 x 坐标
     */
    private final int initX;
    /**
     * 重新游戏 y 坐标
     */
    private final int initY;

    public ButtonConfig(Element button) {
        this.buttonW = Integer.parseInt(button.attributeValue("w"));
        this.buttonH = Integer.parseInt(button.attributeValue("h"));
        this.startX = Integer.parseInt(button.element("start").attributeValue("x"));
        this.startY = Integer.parseInt(button.element("start").attributeValue("y"));
        this.initX = Integer.parseInt(button.element("init").attributeValue("x"));
        this.initY = Integer.parseInt(button.element("init").attributeValue("y"));
    }

    public int getButtonW() {
        return buttonW;
    }

    public int getButtonH() {
        return buttonH;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getInitX() {
        return initX;
    }

    public int getInitY() {
        return initY;
    }
}
