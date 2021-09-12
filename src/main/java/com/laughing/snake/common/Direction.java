package com.laughing.snake.common;

/**
 * @author : laughing
 * @create : 2021-08-26 23:19
 * @description : 方向枚举类
 */
public enum Direction {
    UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);

    private final int direction;

    public int getDirection() {
        return direction;
    }

    Direction(int direction) {
        this.direction = direction;
    }

    /**
     * 用户按下的方向与当前运行方向对比
     * @param direction 用户按下的方向
     * @return 两个方向是否相同
     */
    public boolean compare(Direction direction) {
        // 按下相反方向，方向不变
        if (this.getDirection() == 0 && direction.direction == 2) {
            return false;
        } else if (this.getDirection() == 1 && direction.direction == 3) {
            return false;
        } else if (this.getDirection() == 2 && direction.direction == 0) {
            return false;
        } else if (this.getDirection() == 3 && direction.direction == 1) {
            return false;
        } else return this.getDirection() != direction.direction;
    }
}