package com.laughing.snake.dto;

import com.laughing.snake.config.Direction;
import com.laughing.snake.entity.Node;
import com.laughing.snake.entity.Snake;
import com.laughing.snake.util.GameUtil;

import java.util.Collections;
import java.util.List;

/**
 * @author : laughing
 * @create : 2021-09-15 20:05
 * @description : 游戏画面数据
 */
public class GameDTO {
    /**
     * 蛇
     */
    private Snake snake;

    /**
     * 食物
     */
    private Node food;

    /**
     * 方向
     */
    private Direction direction;

    /**
     * 等级
     */
    private int gameLevel;

    /**
     * 分数
     */
    private int gamePoint;

    /**
     * 游戏是否开始
     */
    private boolean start;

    /**
     * 蛇的生命
     */
    private boolean live;

    /**
     * 暂停
     */
    private boolean pause;

    /**
     * 作弊
     */
    private boolean cheat;

    /**
     * 线程睡眠时间
     */
    private long sleepTime;

    /**
     * 音乐开关
     */
    private boolean choose;

    /**
     * 网格
     */
    private boolean grid = true;

    /**
     * 排行榜
     */
    private List<Player> list;

    public GameDTO() {
        this.initDTO();
    }

    /**
     * DTO 初始化
     */
    public void initDTO() {
        this.gameLevel = 0;
        this.gamePoint = 0;
        this.live = true;
        this.pause = false;
        this.cheat = false;
        this.choose = true;
        this.sleepTime = GameUtil.getSleepTimeByLevel(this.gameLevel);
    }

    private List<Player> setFillList(List<Player> players) {
        // 如果进来的是空，就不显示
        if (players == null) {
            return null;
        }
        // 排序
        Collections.sort(players);
        // 判断记录是否超过5条，如果超过5条，去掉分数低的
        if (players.size() > 5) {
            players.subList(5, players.size()).clear();
        }
        return players;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Node getFood() {
        return food;
    }

    public void setFood(Node food) {
        this.food = food;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
        this.sleepTime = GameUtil.getSleepTimeByLevel(this.gameLevel);
    }

    public int getGamePoint() {
        return gamePoint;
    }

    public void setGamePoint(int gamePoint) {
        this.gamePoint = gamePoint;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public boolean isPause() {
        return pause;
    }

    public void changePause() {
        this.pause = !this.pause;
    }

    public boolean isCheat() {
        return cheat;
    }

    public void setCheat(boolean cheat) {
        this.cheat = cheat;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public boolean isChoose() {
        return choose;
    }

    public void changeChoose() {
        this.choose = !this.choose;
    }

    public boolean isGrid() {
        return grid;
    }

    public void setGrid(boolean grid) {
        this.grid = grid;
    }

    public List<Player> getList() {
        return list;
    }

    public void setList(List<Player> list) {
        this.list = this.setFillList(list);
    }
}
