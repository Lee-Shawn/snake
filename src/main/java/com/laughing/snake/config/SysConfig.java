package com.laughing.snake.config;

import org.dom4j.Element;

import java.io.Serializable;

/**
 * @author : laughing
 * @create : 2021-09-15 20:07
 * @description :  游戏区域配置
 */
public class SysConfig implements Serializable {
    /**
     * 游戏区域 x 左边界
     */
    private final int minX;

    /**
     * 游戏区域 x 右边界
     */
    private final int maxX;

    /**
     * 游戏区域 y 左边界
     */
    private final int minY;

    /**
     * 游戏区域 y 右边界
     */
    private final int maxY;

    /**
     * 游戏等级
     */
    private final int levelUp;

    /**
     * 游戏经验值
     */
    private final int experience;

    public SysConfig(Element system) {
        this.minX = Integer.parseInt(system.attributeValue("minX"));
        this.maxX = Integer.parseInt(system.attributeValue("maxX"));
        this.minY = Integer.parseInt(system.attributeValue("minY"));
        this.maxY = Integer.parseInt(system.attributeValue("maxY"));
        this.levelUp = Integer.parseInt(system.attributeValue("levelUp"));
        this.experience = Integer.parseInt(system.attributeValue("experience"));
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getLevelUp() {
        return levelUp;
    }

    public int getExperience() {
        return experience;
    }
}
