package com.laughing.snake.util;

/**
 * @author : laughing
 * @create : 2021-09-15 20:29
 * @description :
 */
public class GameUtil {
    /**
     * 计算线程睡眠时间
     * @param level 游戏等级
     * @return 时间
     */
    public static long getSleepTimeByLevel(int level) {
        long sleep = (-40L * level + 740);
        sleep = sleep < 100 ? 100 : sleep;
        return sleep;
    }
}
