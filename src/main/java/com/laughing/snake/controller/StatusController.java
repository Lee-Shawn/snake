package com.laughing.snake.controller;

/**
 * @author : laughing
 * @create : 2021-09-05 15:35
 * @description : 游戏状态
 */
public class StatusController {
    // 开始暂停状态
    private static boolean status = false;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        StatusController.status = status;
    }
}
