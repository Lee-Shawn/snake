package com.laughing.snake.service;

import com.laughing.snake.config.Direction;

/**
 * @author : laughing
 * @create : 2021-09-15 21:02
 * @description : 游戏业务接口
 */
public interface GameService {
    /**
     * 方向上
     */
    boolean keyUp();

    /**
     * 方向下
     */
    boolean keyDown();

    /**
     * 方向左
     */
    boolean keyLeft();

    /**
     * 方向右
     */
    boolean keyRight();

    /**
     * 暂停
     */
    boolean keySpace();

    /**
     * 加速
     */
    boolean keySpeed();

    /**
     * 作弊
     */
    boolean keyCheating();

    /**
     * 启动游戏运动线程
     */
    void startGame();

    /**
     * 改变方向
     */
    void changeDirection(Direction direction);

    /**
     * 游戏主行为
     */
    void mainAction();
}
