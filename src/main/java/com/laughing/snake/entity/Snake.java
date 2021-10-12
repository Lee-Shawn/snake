package com.laughing.snake.entity;

import com.laughing.snake.config.GameConfig;

import java.util.LinkedList;

/**
 * @author : laughing
 * @create : 2021-09-14 23:24
 * @description : 蛇
 */
public class Snake {
    /**
     * 获取游戏区域边框宽度左位移
     */
    private static final int MIN_X = GameConfig.getSysConfig().getMinX();
    /**
     * 获取游戏区域边框宽度右位移
     */
    private static final int MAX_X = GameConfig.getSysConfig().getMaxX();
    /**
     * 获取游戏区域边框高度左位移
     */
    private static final int MIN_Y = GameConfig.getSysConfig().getMinY();
    /**
     * 获取游戏区域边框高度右位移
     */
    private static final int MAX_Y = GameConfig.getSysConfig().getMaxY();
    /**
     * 游戏区域间隔
     */
    private static final int INTERVAL = GameConfig.getFrameConfig().getInterval();

    /**
     * LinkedList 存储蛇
     */
    private final LinkedList<Node> snake = new LinkedList<>();

    /**
     * 移动
     * @param moveX x 坐标偏移量
     * @param moveY y 坐标偏移量
     * @return 是否出界
     */
    public boolean move(int moveX, int moveY) {
        int newX = this.getHead().getX() + moveX * INTERVAL;
        int newY = this.getHead().getY() + moveY * INTERVAL;
        if (this.isOverZone(newX, newY)) {
            return false;
        }
        Node head = new Node(newX, newY);
        this.snake.addFirst(head);
        this.snake.removeLast();
        return true;
    }

    /**
     * 判断蛇是否吃到食物
     * @param food 食物
     * @return 吃到食物结果
     */
    public boolean eat(Node food) {
        // 如果食物和蛇头碰撞，则将食物加入蛇的身体
        return isFoodAndSnakeCollision(snake.getFirst(), food);
    }

    /**
     * 判断是否超出边界
     * @param x 横坐标
     * @param y 纵坐标
     * @return  是否出界
     */
    private boolean isOverZone(int x, int y) {
        return x < MIN_X || x > MAX_X * INTERVAL || y < MIN_Y || y > MAX_Y * INTERVAL;
    }

    /**
     * 获取蛇的头部
     * @return 蛇头
     */
    public Node getHead() {
        return snake.getFirst();
    }

    /**
     * 获取蛇的尾巴
     */
    public void addTail(Node node) {
        this.snake.addLast(node);
    }

    /**
     * 获取蛇的身体
     * @return 身体
     */
    public LinkedList<Node> getBody()  {
        return snake;
    }

    /**
     * 生成的食物是否与蛇重合
     * @param food 食物
     * @return 是否重合
     */
    public boolean isFoodAndSnakeCoincidence(Node food) {
        for (Node s : snake) {
            if (isCoincidence(s.getX(), s.getY(), food.getX(), food.getY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 蛇是否碰撞到自己身体
     * @param head 蛇头
     * @return 是否碰撞
     */
    public boolean isSnakeCoincidenceSelf(Node head) {
        for (int i = 1; i < snake.size(); i++) {
            if (isCoincidence(snake.get(i).getX(), snake.get(i).getY(), head.getX(), head.getY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 蛇头是否吃到食物
     * @param head 蛇头
     * @param food 食物
     * @return     碰撞
     */
    private boolean isFoodAndSnakeCollision(Node head, Node food) {
        return isCoincidence(head.getX(), head.getY(), food.getX(), food.getY());
    }

    /**
     * 节点是否重合
     * @param x1 a 节点 x 坐标
     * @param y1 a 节点 y 坐标
     * @param x2 b 节点 x 坐标
     * @param y2 b 节点 y 坐标
     * @return 是否重合
     */
    private boolean isCoincidence(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2) < GameConfig.getFrameConfig().getInterval();
    }
}
