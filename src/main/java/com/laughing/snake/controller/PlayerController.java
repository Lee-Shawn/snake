package com.laughing.snake.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author : laughing
 * @create : 2021-09-19 17:08
 * @description :玩家控制器
 */
public class PlayerController extends KeyAdapter {
    private final GameController gameController;

    public PlayerController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * 键盘按下事件
     * @param e 事件
     */
    @Override
    public void keyPressed(KeyEvent e) {
        this.gameController.actionByKeyCode(e.getKeyCode());
    }
}
