package com.laughing.snake.entity;

/**
 * @author : laughing
 * @create : 2021-08-26 23:22
 * @description : 蛇身节点
 */
public class Node {
    private final int x;
    private final int y;

    public Node(int x, int y) {
        // 让食物的坐标都是10的整数倍
        this.x = (x/10)*10;
        this.y = (y/10)*10;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

    /**
     * 重写 equals 方法，比较蛇和食物位置是否重合
     * @param object 食物
     * @return 重合结果
     */
    @Override
    public boolean equals(Object object) {
        Node node;
        if (object instanceof Node) {
            node = (Node) object;
            return node.getX() == this.x && node.getY() == this.y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}