package com.laughing.snake.service.impl;

import com.laughing.snake.config.Direction;
import com.laughing.snake.config.GameConfig;
import com.laughing.snake.dto.GameDTO;
import com.laughing.snake.entity.Node;
import com.laughing.snake.entity.Snake;
import com.laughing.snake.service.GameService;
import com.laughing.snake.util.MusicUtil;

/**
 * @author : laughing
 * @create : 2021-09-15 21:05
 * @description : 业务逻辑
 */
public class GameServiceImpl implements GameService {

    /**
     * 游戏数据对象
     */
    private final GameDTO gameDTO;

    /**
     * 升级需要吃的食物个数
     */
    private static final int LEVEL_UP = GameConfig.getSysConfig().getLevelUp();

    /**
     * 每吃一个食物增加的经验值
     */
    private static final int EXPERIENCE = GameConfig.getSysConfig().getExperience();

    public GameServiceImpl(GameDTO gameDTO) {
        this.gameDTO = gameDTO;
    }

    /**
     *  控制器方向键（上）
     */
    @Override
    public boolean keyUp() {
        if (this.gameDTO.isPause()) {
            return true;
        }
        synchronized (this.gameDTO) {
            if (this.gameDTO.isStart()) {
                // 按相反方向不让蛇改变方向
                if (this.gameDTO.getDirection() == Direction.DOWN) {
                    return false;
                }
                changeDirection(Direction.UP);
            }
        }
        return true;
    }

    /**
     * 控制器方向键（下）
     */
    @Override
    public boolean keyDown() {
        if (this.gameDTO.isPause()) {
            return true;
        }
        synchronized (this.gameDTO) {
            if (this.gameDTO.isStart()) {
                if (this.gameDTO.getDirection() == Direction.UP) {
                    return false;
                }
                changeDirection(Direction.DOWN);
            }
        }
        return true;
    }

    /**
     * 控制器方向键（左）
     */
    @Override
    public boolean keyLeft() {
        if (this.gameDTO.isPause()) {
            return true;
        }
        synchronized (this.gameDTO) {
            if (this.gameDTO.isStart()) {
                if (this.gameDTO.getDirection() == Direction.RIGHT) {
                    return false;
                }
                changeDirection(Direction.LEFT);
            }
        }
        return true;
    }

    /**
     * 控制器方向键（右）
     */
    @Override
    public boolean keyRight() {
        if (this.gameDTO.isPause()) {
            return true;
        }
        synchronized (this.gameDTO) {
            if (this.gameDTO.isStart()) {
                if (this.gameDTO.getDirection() == Direction.LEFT) {
                    return false;
                }
                changeDirection(Direction.RIGHT);
            }
        }
        return true;
    }

    /**
     * 开始暂停
     * @return 暂停
     */
    @Override
    public boolean keySpace() {
        if (this.gameDTO.isStart()) {
            this.gameDTO.changePause();
            this.setBGM();
        }
        return true;
    }

    /**
     * 加速功能
     */
    @Override
    public boolean keySpeed() {
        if (this.gameDTO.isPause()) {
            return true;
        }
        synchronized (this.gameDTO) {
            if (this.gameDTO.isStart()) {
                this.growUp();
                this.running(this.gameDTO.getDirection());
            }
        }
        return true;
    }

    /**
     * 作弊加分
     */
    @Override
    public boolean keyCheating() {
        this.gameDTO.setCheat(true);
        this.upgrade();
        return true;
    }

    /**
     * 开始游戏
     */
    @Override
    public void startGame() {
        // 初始化蛇
        this.gameDTO.setSnake(this.init());
        // 初始化蛇运动方向
        this.gameDTO.setDirection(Direction.LEFT);
        // 设置蛇的生命为活着状态
        this.gameDTO.setLive(true);
        // 随机生成食物
        this.gameDTO.setFood(this.createFood());
        // 游戏状态设为开始
        this.gameDTO.setStart(true);
        // DTO 初始化
        this.gameDTO.initDTO();
    }

    /**
     * 改变蛇运动方向
     * @param direction 用户按下的方向
     */
    @Override
    public void changeDirection(Direction direction) {
        if (this.gameDTO.getDirection().compare(direction)) {
            this.gameDTO.setDirection(direction);
        }
    }

    /**
     * 蛇自动移动
     */
    @Override
    public void mainAction() {
        // 锁住线程
        synchronized (this.gameDTO) {
            // 蛇吃食物变大
            this.growUp();
            // 游戏结束
            if (this.gameOver()) {
                this.gameDTO.setStart(false);
                this.gameDTO.setLive(false);
                MusicUtil.stopBGM();
            }
        }
    }

    /**
     * 初始化蛇
     * @return 蛇
     */
    private Snake init() {
        Snake snake = new Snake();
        int x = GameConfig.getFrameConfig().getWidth() / 2;
        int y = GameConfig.getFrameConfig().getWidth() / 2;
        for (int i = 0; i < 3; i++) {
            snake.addTail(new Node(x, y));
            x += GameConfig.getFrameConfig().getInterval();
        }
        return snake;
    }

    /**
     * 随机生成食物
     * @return 食物
     */
    private Node createFood() {
        int x = (int) (Math.random() * GameConfig.getSysConfig().getMaxX());
        int y = (int) (Math.random() * GameConfig.getSysConfig().getMaxY());
        int nodeX = x * GameConfig.getFrameConfig().getInterval();
        int nodeY = y * GameConfig.getFrameConfig().getInterval();
        Node nextFood = new Node(nodeX, nodeY);
        // 生成的食物不能与蛇重叠
        while (this.gameDTO.getSnake().isFoodAndSnakeCoincidence(nextFood)) {
            x = (int) (Math.random() * GameConfig.getSysConfig().getMaxX());
            y = (int) (Math.random() * GameConfig.getSysConfig().getMaxY());
            nodeX = x * GameConfig.getFrameConfig().getInterval();
            nodeY = y * GameConfig.getFrameConfig().getInterval();
            nextFood = new Node(nodeX, nodeY);
        }
        return nextFood;
    }

    /**
     * 移动
     * @param direction 方向
     */
    private boolean running(Direction direction) {
        boolean outside = true;
        if (direction == Direction.UP) {
            outside = this.gameDTO.getSnake().move(0, -1);
        } else if (direction == Direction.DOWN) {
            outside = this.gameDTO.getSnake().move(0, 1);
        } else if (direction == Direction.LEFT) {
            outside = this.gameDTO.getSnake().move(-1, 0);
        } else if (direction == Direction.RIGHT) {
            outside = this.gameDTO.getSnake().move(1, 0);
        }
        return outside;
    }

    /**
     * 检查游戏是否结束
     * @return 结束
     */
    private boolean gameOver() {
        return !this.running(this.gameDTO.getDirection())
                || this.gameDTO.getSnake().isSnakeCoincidenceSelf(this.gameDTO.getSnake().getHead());
    }

    /**
     * 升级操作
     */
    private void upgrade() {
        int level = this.gameDTO.getGameLevel();
        int point = this.gameDTO.getGamePoint() + EXPERIENCE;
        this.gameDTO.setGamePoint(point);
        // 每吃20个食物升一级
        int upgrade = point / EXPERIENCE;
        if (upgrade / LEVEL_UP != 0 && upgrade % LEVEL_UP == 0) {
            this.gameDTO.setGameLevel(++level);
        }
    }

    /**
     * 吃食物
     * @return 结果
     */
    private boolean eatFood() {
        return this.gameDTO.getSnake().eat(this.gameDTO.getFood());
    }

    /**
     * 蛇变大
     */
    private void growUp() {
        // 吃到食物
        if (eatFood()) {
            // 升级
            this.upgrade();
            // 蛇增加长度
            this.gameDTO.getSnake().addTail(this.gameDTO.getFood());
            // 生成新食物
            this.gameDTO.setFood(createFood());
        }
    }

    /**
     * 设置背景音乐
     */
    private void setBGM() {
        if (this.gameDTO.isChoose()) {
            // 暂停游戏关闭音乐
            if (this.gameDTO.isPause()) {
                MusicUtil.stopBGM();
            } else {
                MusicUtil.playBGM();
            }
        }
    }
}
