package com.laughing.snake.controller;

import com.laughing.snake.common.Constant;
import com.laughing.snake.common.Direction;
import com.laughing.snake.dao.Record;
import com.laughing.snake.entity.Scene;
import com.laughing.snake.view.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : laughing
 * @create : 2021-08-24 23:32
 * @description : 游戏控制
 */
public class SnakeController implements KeyListener {
    // 场景
    private Scene scene;
    // panel
    private GameView view;
    // 得分
    private Record record;
    // 开始暂停状态
    private StatusController status;
    // 线程
    private ScheduledExecutorService executor;
    // 是否重新开始游戏
    private int isRestart;
    // 游戏状态
    private boolean isLive;

    private SnakeController() {
        this.snakeControllerInit();
    }

    /**
     * 单例模式创建对象
     */
    private static class SnakeControllerInstance {
        private static final SnakeController INSTANCE = new SnakeController();
    }

    /**
     * 对外开放对象调用方法
     * @return 控制器对象
     */
    public static SnakeController getInstance() {
        return SnakeControllerInstance.INSTANCE;
    }

    /**
     * 控制器初始化
     */
    public void snakeControllerInit() {
        this.scene = Scene.getInstance();
        this.view = GameView.getInstance();
        this.record = new Record();
        this.status = new StatusController();
        this.isRestart = 1;
        this.isLive = true;
        this.run();
    }

    /**
     * 游戏结束
     * @param over 是否有下一步动作
     * @return 游戏是否结束
     */
    public boolean isOver(boolean over) {
        if (!over) {
            // 暂停状态
            status.setStatus(false);
            // 游戏状态
            isLive = false;
            isRestart = view.gameOver();
            return false;
        }
        return true;
    }

    /**
     * 重新开始游戏
     */
    public void restartGame() {
        // 恢复蛇的初试样子和方向
        scene.initSnake();
        scene.setSnakeDirection(Direction.LEFT);
        // 游戏状态初始化
        isLive = true;
        status.setStatus(true);
        // 分数清零
        record.setPlayerScore(0);
        scene.setScore(0);
        // 重绘画面
        view.draw();
    }

    /**
     * 开启蛇运动线程
     */
    public void run() {
        try {
            executor = Executors.newSingleThreadScheduledExecutor();
            executor.scheduleAtFixedRate(() -> {
                // 重新开始游戏
                if (isRestart == 0) {
                    System.out.println("a");
                    isRestart = 1;
                    this.restartGame();
                }
                if (status.getStatus()) {
                    // 游戏一直运行
                    if (isOver(scene.nextRound())) {
                        if (isLive) {
                            view.draw();
                        }
                    }
                }
            }, 0, Constant.DEFAULT_MOVE_INTERVAL, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 退出线程
     */
    public void quit() {
        executor.shutdown();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 暂停时不允许改变方向
        if (!status.getStatus()) {
            return;
        }
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                // 按相反方向不让蛇加速
                if (scene.getSnakeDirection() == Direction.DOWN) {
                    return;
                }
                scene.changeDirection(Direction.UP);
                break;
            case KeyEvent.VK_RIGHT:
                if (scene.getSnakeDirection() == Direction.LEFT) {
                    return;
                }
                scene.changeDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_DOWN:
                if (scene.getSnakeDirection() == Direction.UP) {
                    return;
                }
                scene.changeDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                if (scene.getSnakeDirection() == Direction.RIGHT) {
                    return;
                }
                scene.changeDirection(Direction.LEFT);
                break;
            default:
                // 按其他键不许有其他响应
                return;
        }
        // 按下方向键后要重绘
        if (status.getStatus()) {
            if (isOver(scene.nextRound())) {
                if (isLive) {
                    view.draw();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}