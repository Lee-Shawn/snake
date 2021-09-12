package com.laughing.snake.entity;

import com.laughing.snake.common.Constant;
import com.laughing.snake.common.Direction;
import com.laughing.snake.dao.Record;

/**
 * @author : laughing
 * @create : 2021-08-26 23:18
 * @description : 游戏场景
 */
public class Scene {
    private int width;
    private int height;
    // 蛇在网格中运动
    private Snake snake;
    // 食物在网格中生成
    private Node food;
    // 得分
    private Record record;
    private int score;
    // 蛇的初试方向
    private Direction snakeDirection;

    private Scene() {
        this.init();
    }

    /**
     * 单例模式
     */
    private static class SceneInstance {
        private static final Scene INSTANCE = new Scene();
    }

    public static Scene getInstance() {
        return SceneInstance.INSTANCE;
    }

    /**
     * 初始化
     */
    public void init() {
        this.width = Constant.GAME_WIDTH;
        this.height = Constant.GAME_HEIGHT;
        this.record = new Record();
        this.snakeDirection = Direction.LEFT;
        this.snake = initSnake();
        this.food = createFood();
    }

    /**
     * 初始化蛇
     */
    public Snake initSnake() {
        snake = new Snake();
        int x = width / 2;
        int y = height / 2;
        // 设置蛇的身体
        for (int i = 0; i < 3; i++) {
            snake.addTail(new Node(x, y));
            x += Constant.NODE_SIZE;
        }
        return snake;
    }

    /**
     * 随机生成食物
     */
    public Node createFood() {
        int x, y;
        // random * (800 - 20 * 3) + 20,(0,760]
        x = (int) (Math.random() * (Constant.GAME_AREA_WIDTH - 3 * Constant.NODE_SIZE)) + Constant.NODE_SIZE;
        y = (int) (Math.random() * (Constant.GAME_AREA_WIDTH - 3 * Constant.NODE_SIZE)) + Constant.NODE_SIZE;
        Node nextFood = new Node(x, y);
        while (snake.isCoincidence(nextFood)) {
            x = (int) (Math.random() * (Constant.GAME_AREA_WIDTH - 3 * Constant.NODE_SIZE)) + Constant.NODE_SIZE;
            y = (int) (Math.random() * (Constant.GAME_AREA_WIDTH - 3 * Constant.NODE_SIZE)) + Constant.NODE_SIZE;
            nextFood = new Node(x, y);
        }
        food = nextFood;
        return food;
    }

    /**
     * 后续可进行的操作
     * @return 操作结果
     */
    public boolean nextRound() {
        // 之前的尾巴
        Node preTail;
        // 如果蛇头碰到身体，游戏结束
        if (snake.isSelfCollision(snake.getHead())) {
            return false;
        }
        // 按当前方向移动蛇
        preTail = snake.move(snakeDirection);
        if (snake.getHead().getX() >= Constant.NODE_SIZE && snake.getHead().getX() <= Constant.GAME_AREA_WIDTH - Constant.NODE_SIZE
                && snake.getHead().getY() >= Constant.NODE_SIZE && snake.getHead().getY() <= Constant.GAME_AREA_HEIGHT - Constant.NODE_SIZE) {
            // 如果头部碰到了食物，就吃掉食物
            if (snake.eat(food)) {
                playerScore();
                // 把原来删除的尾巴再加回来
                snake.addTail(preTail);
                // 再创建一个食物
                food = createFood();
            }
            return true;
        }
        // 游戏结束，得分置0
        //record.setPlayerScore(0);
        return false;
    }

    /**
     * 记录游戏得分
     */
    public void playerScore() {
        score += 100;
        record.setPlayerScore(score);
    }

    /**
     * 改变蛇的移动方向
     * @param direction 用户按下的方向
     */
    public void changeDirection(Direction direction) {
        if (snakeDirection.compare(direction)) {
            snakeDirection = direction;
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public Node getFood() {
        return food;
    }

    public Direction getSnakeDirection() {
        return snakeDirection;
    }

    public void setSnakeDirection(Direction snakeDirection) {
        this.snakeDirection = snakeDirection;
    }

    public void setScore(int score) {
        this.score = score;
    }
}