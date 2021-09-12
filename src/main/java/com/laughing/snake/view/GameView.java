package com.laughing.snake.view;

import com.laughing.snake.common.Constant;
import com.laughing.snake.controller.StatusController;
import com.laughing.snake.dao.Record;
import com.laughing.snake.entity.Scene;
import com.laughing.snake.entity.Node;
import com.laughing.snake.entity.Snake;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;

/**
 * @author : laughing
 * @create : 2021-08-27 19:20
 * @description : panel面板
 */
public class GameView {
    private JPanel panel;
    private Scene scene;
    private Record record;
    private StatusController status;
    private JLabel recordLabel;
    private JButton start;

    private GameView() {
        this.initView();
    }

    private static class GameViewInstance {
        private static final GameView INSTANCE = new GameView();
    }

    public static GameView getInstance() {
        return GameViewInstance.INSTANCE;
    }

    private void initView() {
        this.scene = Scene.getInstance();
        this.record = new Record();
        this.status = new StatusController();
        this.recordLabel = new JLabel("得分：");
        this.start = new JButton("开始游戏");
    }

    /**
     * 初始化 panel
     */
    public void  init() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // 必须调用父类方法，否则不会覆盖之前的绘画
                super.paintComponent(g);
                drawBackground(g);
                gameData(g);
                drawSnake(g, scene.getSnake());
                drawFood(g, scene.getFood());
                drawWall(g);
            }
        };
    }

    /**
     * 界面组件
     */
    public void addComponent() {
        panel.setLayout(null);
        recordLabel.setBounds(900, 50, 60, 30);
        recordLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        panel.add(recordLabel);
        start.setBounds(850, 700, 100, 50);
        start.setUI(new BEButtonUI());
        panel.add(start);
        // 释放按钮焦点
        start.setFocusable(false);
    }

    /**
     * 事件监听
     */
    public void addControlListener() {
        start.addActionListener(e -> {
            if (!status.getStatus()) {
                status.setStatus(true);
                start.setText("暂停游戏");
            } else if (status.getStatus()) {
                status.setStatus(false);
                start.setText("开始游戏");
            }
        });
    }

    /**
     * 游戏信息
     * @param g 画笔
     */
    private void gameData(Graphics g) {
        g.setColor(Color.GREEN);
        g.setFont(new Font("楷体", Font.PLAIN, 20));
        g.drawString(record.getPlayerScore()+"", 970, 72);
        g.drawString("分", 1020, 72);
    }

    /**
     * 设置游戏背景色
     * @param g 画笔
     */
    private void drawBackground(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
    }

    /**
     * 画蛇
     * @param g 画笔
     * @param snake 蛇
     */
    private void drawSnake(Graphics g, Snake snake) {
        Node node = snake.getHead();
        // 蛇头
        this.drawSquare(g, node, Color.BLUE);
        // 遍历画出蛇身
        // ConcurrentModificationException，在遍历的时候另外的线程修改了LinkedList，所以不要使用Iterator
        for (int i = 1; i < snake.getBody().size(); i++) {
            node = snake.getBody().get(i);
            this.drawSquare(g, node, Color.CYAN);
        }
    }

    /**
     * 画食物
     * @param g 画笔
     * @param food 食物
     */
    private void drawFood(Graphics g, Node food) {
        this.drawCircle(g, food, Color.ORANGE);
    }

    /**
     * 画方形
     * @param graphics 画笔
     * @param squareArea Node
     * @param color 颜色
     */
    private void drawSquare(Graphics graphics, Node squareArea, Color color) {
        graphics.setColor(color);
        int size = Constant.NODE_SIZE;
        graphics.fillRect(squareArea.getX(), squareArea.getY(), size - 1, size - 1);

    }

    /**
     * 画圆形
     * @param graphics 画笔
     * @param squareArea Node
     * @param color 颜色
     */
    private void drawCircle(Graphics graphics, Node squareArea, Color color) {
        graphics.setColor(color);
        int size = Constant.NODE_SIZE;
        graphics.fillOval(squareArea.getX(), squareArea.getY(), size, size);
    }

    /**
     * 墙
     * @param g 画笔
     */
    private void drawWall(Graphics g) {
        g.setColor(Color.PINK);
        for (int i = 0; i <= Constant.GAME_AREA_WIDTH; i += Constant.NODE_SIZE) {
            g.fillRect(i, 0, Constant.NODE_SIZE, Constant.NODE_SIZE);
            g.fillRect(i, Constant.GAME_AREA_HEIGHT, Constant.NODE_SIZE, Constant.NODE_SIZE);
        }
        for (int i = 0; i <= Constant.GAME_AREA_HEIGHT; i += Constant.NODE_SIZE) {
            g.fillRect(0, i, Constant.NODE_SIZE, Constant.NODE_SIZE);
            g.fillRect(Constant.GAME_AREA_WIDTH, i, Constant.NODE_SIZE, Constant.NODE_SIZE);
        }
    }

    /**
     * 自动刷新
     */
    public void draw() {
        panel.repaint();
    }

    public JPanel getPanel() {
        return panel;
    }

    /**
     * 游戏结束提示
     * @return 用户选择结果
     */
    public int gameOver() {
        // 0：确认，1：取消
        return JOptionPane.showConfirmDialog(null, "游戏结束，是否重新开始游戏？", "游戏结束", JOptionPane.YES_NO_OPTION);
    }
}