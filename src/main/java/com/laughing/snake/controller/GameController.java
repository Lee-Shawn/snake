package com.laughing.snake.controller;


import com.laughing.snake.config.DataInterfaceConfig;
import com.laughing.snake.config.GameConfig;
import com.laughing.snake.dao.Data;
import com.laughing.snake.dto.GameDTO;
import com.laughing.snake.dto.Player;
import com.laughing.snake.service.GameService;
import com.laughing.snake.service.impl.GameServiceImpl;
import com.laughing.snake.util.MusicUtil;
import com.laughing.snake.view.*;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author : laughing
 * @create : 2021-09-12 14:07
 * @description : 接受玩家键盘事件，控制画面，控制游戏逻辑
 */
public class GameController {
    /**
     * 数据访问接口
     */
    private final Data dataFile;

    /**
     * 游戏逻辑层
     */
    private final GameService gameService;

    /**
     * 游戏控制窗口
     */
    private final GameSetting gameSetting;

    /**
     * 保存分数
     */
    private final SavePointFrame savePoint;

    /**
     * 游戏菜单
     */
    private final GameMenu gameMenu;

    /**
     * 游戏行为控制
     */
    private Map<Integer, Method> actionList;

    /**
     * 游戏界面层
     */
    private final GamePanel panel;

    /**
     * 游戏数据源
     */
    private final GameDTO gameDTO;

    /**
     * 游戏线程
     */
    private Thread gameThread;

    public GameController() {
        // 创建游戏数据源
        this.gameDTO = new GameDTO();
        // 创建游戏逻辑块（连接游戏数据源）
        this.gameService = new GameServiceImpl(gameDTO);
        // 数据接口获得本地记录，如果需要连接连接数据，将 getDataFile() 方法换成 getDataBase()
        this.dataFile = createDateObject(GameConfig.getDataConfig().getDataFile());
        // 设置本地记录到游戏
        if (dataFile != null) {
            this.gameDTO.setList(dataFile.loadData());
        }
        // 创建游戏面板
        this.panel = new GamePanel(this, gameDTO);
        // 读取用户控制设置
        this.setControlConfig();
        // 初始化用户设置窗口
        this.gameSetting = new GameSetting(this, gameDTO);
        // 初始化保存分数窗口
        this.savePoint = new SavePointFrame(this);
        // 初始化游戏菜单
        this.gameMenu = new GameMenu(this);
        this.gameMenu.initMenu(gameMenu);
        // 创建游戏主窗口，安装游戏面板
        new GameFrame(this.panel, this.gameMenu);
    }

    /**
     * 玩家行为
     * @param keyCode 键盘按键码
     */
    public void actionByKeyCode(int keyCode) {
        /*switch (keyCode) {
            case 37:
                this.gameService.keyLeft();
                break;
            case 38:
                this.gameService.keyUp();
                break;
            case 39:
                this.gameService.keyRight();
                break;
            case 40:
                this.gameService.keyDown();
                break;
            case 32:
                this.gameService.keySpace();
                break;
            default:
                break;
        }*/
        try {
            if (this.actionList.containsKey(keyCode)) {
                // 反射用户行为，调用控制方法
                this.actionList.get(keyCode).invoke(this.gameService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 按下方向键后要重绘
        this.panel.repaint();
    }

    /**
     * 创建数据对象
     * @param config 数据解析器
     * @return 数据对象
     */
    private Data createDateObject(DataInterfaceConfig config) {
        try {
            // 获得类对象
            Class<?> cls = Class.forName(config.getClassName());
            // 获得构造器
            Constructor<?> ctr = cls.getConstructor(HashMap.class);
            // 创建对象
            return (Data) ctr.newInstance(config.getParam());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 刷新画面
     */
    public void repaint() {
        this.panel.repaint();
    }

    /**
     * 子窗口关闭事件
     */
    public void setOver() {
        this.panel.repaint();
        // 修改配置后重新读取配置
        this.setControlConfig();
    }

    /**
     * 读取用户控制设置
     */
    private void setControlConfig() {
        // 创建键盘码与方法名的映射数组
        this.actionList = new HashMap<>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("src/main/resources/data/control.dat"));
            HashMap<Integer, String> config = (HashMap<Integer, String>) ois.readObject();
            Set<Map.Entry<Integer, String>> entrySet = config.entrySet();
            for (Map.Entry<Integer, String> entry : entrySet) {
                actionList.put(entry.getKey(), this.gameService.getClass().getMethod(entry.getValue()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 显示玩家控制窗口
     */
    public void showUserConfig() {
        this.gameSetting.setVisible(true);
    }

    /**
     * 开始按钮事件
     */
    public void start() {
        // 面板按钮设置为不可点击
        this.panel.buttonSwitch(false);
        // 关闭窗口
        this.gameSetting.setVisible(false);
        this.savePoint.setVisible(false);
        // 游戏数据初始化
        this.gameService.startGame();
        // 创建线程
        this.gameThread = new MainThread() ;
        // 启动线程
        this.gameThread.start();
        // 刷新画面
        this.panel.repaint();
    }

    /**
     * 保存游戏分数
     * @param name 玩家用户名
     */
    public void savePoint(String name) {
        Player player = new Player(name, this.gameDTO.getGamePoint());
        // 保存记录到本地磁盘
        this.dataFile.saveData(player);
        // 设置磁盘记录到游戏
        this.gameDTO.setList(dataFile.loadData());
        // 刷新画面
        this.panel.repaint();
    }

    /**
     * 游戏失败之后的操作
     */
    private void afterLose() {
        // 没有使用作弊功能，才能保存分数
        if (!this.gameDTO.isCheat()) {
            this.savePoint.show(this.gameDTO.getGamePoint());
        } else {
            this.warning();
        }
        // 使按钮可以点击
        this.panel.buttonSwitch(true);
    }

    /**
     * 作弊警告
     */
    public void warning() {
        if (this.gameDTO.isCheat()) {
            JOptionPane.showMessageDialog(null, "作弊得分不能保存！", "警告", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * 音乐开关
     */
    public void musicSwitch() {
        this.gameDTO.changeChoose();
        if (this.gameDTO.isChoose()) {
            if (this.gameDTO.isStart()) {
                MusicUtil.playBGM();
            }
        } else {
            MusicUtil.stopBGM();
        }
    }

    private class MainThread extends Thread {
        public void run() {
            // 刷新画面
            panel.repaint();
            // 主循环
            while (gameDTO.isStart()) {
                try {
                    // 线程睡眠
                    Thread.sleep(gameDTO.getSleepTime());
                    // 如果暂停，不执行主行为
                    if (gameDTO.isPause()) {
                        continue;
                    }
                    // 游戏主行为
                    gameService.mainAction();
                    // 刷新画面
                    panel.repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 游戏失败后的处理
            afterLose();
        }
    }
}
