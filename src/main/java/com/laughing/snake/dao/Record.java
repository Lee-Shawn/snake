package com.laughing.snake.dao;

/**
 * @author : laughing
 * @create : 2021-09-04 22:48
 * @description : 游戏积分
 */
public class Record {
    // 当前玩家得分
    private static int playerScore = 0;
    // 最高得分 TODO
    private int maxScore;

    public void setPlayerScore(int playerScore) {
        Record.playerScore = playerScore;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setMaxScore(int maxScore) {
        // TODO 最高分
        this.maxScore = maxScore;
    }

    public int getMaxScore() {
        return maxScore;
    }
}
