package com.laughing.snake.view.ui;

import com.laughing.snake.config.Direction;
import com.laughing.snake.config.GameConfig;
import com.laughing.snake.entity.Node;
import com.laughing.snake.entity.Snake;

import java.awt.*;

/**
 * @author : laughing
 * @create : 2021-09-12 18:41
 * @description : 游戏区域窗口
 */
public class LayerGame extends Layer {
    /**
     * 方格大小
     */
    private static final int INTERVAL = GameConfig.getFrameConfig().getInterval();

    public LayerGame(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void paint(Graphics g) {
        this.createWindow(g);
        // 可选择是否显示网格
        if (this.gameDTO.isGrid()) {
            this.drawGrid(g);
        }
        // 画食物
        Node food = this.gameDTO.getFood();
        if (food != null) {
            this.drawFood(food, g);
        }
        // 画蛇
        Snake snake = this.gameDTO.getSnake();
        if (snake != null) {
            this.drawSnake(snake, g);
        }
        // 暂停
        if (this.gameDTO.isPause()) {
            this.drawImageAtCenter(Img.IMG_PAUSE, g);
        }
        // 游戏结束
        if (!this.gameDTO.isLive()) {
            this.drawImageAtCenter(Img.IMG_FAIL, g);
        }
    }

    /**
     * 画蛇
     * @param snake 蛇
     * @param g 画笔
     */
    private void drawSnake(Snake snake, Graphics g) {
        Node node = snake.getHead();
        int headX = node.getX();
        int headY = node.getY();
        // 游戏结束蛇的图片换成灰色
        if (!this.gameDTO.isStart()) {
            for (int i = 0; i < snake.getBody().size(); i++) {
                node = snake.getBody().get(i);
                this.drawSquare(Img.IMG_OVER, node.getX(), node.getY(), g);
            }
        } else {
            // 根据蛇移动的方向绘制不同方向的蛇头
            if (this.gameDTO.getDirection() == Direction.UP) {
                this.drawSquare(Img.IMG_HEAD_UP, headX, headY, g);
            } else if (this.gameDTO.getDirection() == Direction.DOWN) {
                this.drawSquare(Img.IMG_HEAD_DOWN, headX, headY, g);
            } else if (this.gameDTO.getDirection() == Direction.LEFT) {
                this.drawSquare(Img.IMG_HEAD_LEFT, headX, headY, g);
            } else if (this.gameDTO.getDirection() == Direction.RIGHT) {
                this.drawSquare(Img.IMG_HEAD_RIGHT, headX, headY, g);
            }
            // 循环绘制蛇的身体
            for (int i = 1; i < snake.getBody().size(); i++) {
                node = snake.getBody().get(i);
                this.drawSquare(Img.IMG_BODY, node.getX(), node.getY(), g);
            }
        }
    }

    /**
     * 画食物
     * @param food 食物
     * @param g 画笔
     */
    private void drawFood(Node food, Graphics g) {
        this.drawSquare(Img.IMG_FOOD, food.getX(), food.getY(), g);
    }

    /**
     * 画网格
     * @param g 画笔
     */
    private void drawGrid(Graphics g) {
        g.setColor(Color.GREEN);
        // w - (BORDER << 1) 游戏区域长度
        for (int i = this.x + BORDER + INTERVAL; i <= (w - (BORDER << 1)); i += INTERVAL) {
            for (int j = this.y + BORDER; j < this.y + BORDER + (w - (BORDER << 1)); j++) {
                g.drawLine(i, j, i, j);
            }
        }
        for (int i = this.y + BORDER + INTERVAL; i <= (w - (BORDER << 1)); i += INTERVAL) {
            for (int j = this.x + BORDER; j < this.x + BORDER + (w - (BORDER << 1)); j++) {
                g.drawLine(j, i, j, i);
            }
        }
    }

    /**
     * 画方块图片
     * @param image 图片
     * @param x 左上角 x 坐标
     * @param y 左上角 y 坐标
     * @param graphics 画笔
     */
    private void drawSquare(Image image, int x, int y, Graphics graphics) {
        // 游戏结束图片变色
        graphics.drawImage(image, this.x + x + BORDER, this.y + y + BORDER, null);
    }
}
