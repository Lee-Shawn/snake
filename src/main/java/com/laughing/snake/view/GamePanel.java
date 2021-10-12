package com.laughing.snake.view;

import com.laughing.snake.config.FrameConfig;
import com.laughing.snake.config.GameConfig;
import com.laughing.snake.config.LayerConfig;
import com.laughing.snake.controller.GameController;
import com.laughing.snake.controller.PlayerController;
import com.laughing.snake.dto.GameDTO;
import com.laughing.snake.util.MusicUtil;
import com.laughing.snake.view.ui.Img;
import com.laughing.snake.view.ui.Layer;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : laughing
 * @create : 2021-09-12 14:24
 * @description : 游戏面板
 */
public class GamePanel extends JPanel {
    private static final int BTN_SIZE_W = GameConfig.getFrameConfig().getButtonConfig().getButtonW();
    private static final int BTN_SIZE_H = GameConfig.getFrameConfig().getButtonConfig().getButtonH();
    private List<Layer> layers = null;
    private JButton btnStart;
    private JButton btnInit;
    private final GameController controller;

    public GamePanel(GameController controller, GameDTO gameDTO) {
        // 连接游戏控制器
        this.controller = controller;
        // 设置布局管理器为自由布局
        this.setLayout(null);
        // 初始化组件
        this.initComponent();
        // 初始化层
        this.initLayer(gameDTO);
        // 安装键盘监听器
        this.addKeyListener(new PlayerController(controller));
    }

    /**
     * 初始化组件
     */
    private void initComponent() {
        // 初始化开始按钮
        this.btnStart = new JButton(Img.BTN_START);
        // 设置开始按钮位置
        btnStart.setBounds(
                GameConfig.getFrameConfig().getButtonConfig().getStartX(),
                GameConfig.getFrameConfig().getButtonConfig().getStartY(),
                BTN_SIZE_W, BTN_SIZE_H);
        // 给开始按钮添加事件监听
        this.btnStart.addActionListener(e -> {
            controller.start();
            // TODO 重新播放音乐
            MusicUtil.playBGM();
            // 返回焦点
            requestFocus();
        });
        // 添加按钮到面板
        this.add(btnStart);
        // 初始化设置按钮
        this.btnInit = new JButton(Img.BTN_INIT);
        // 设置设置按钮
        btnInit.setBounds(
                GameConfig.getFrameConfig().getButtonConfig().getInitX(),
                GameConfig.getFrameConfig().getButtonConfig().getInitY(),
                BTN_SIZE_W, BTN_SIZE_H);
        // 给设置按钮添加事件监听
        this.btnInit.addActionListener(e -> controller.showUserConfig());
        // 添加按钮到面板
        this.add(btnInit);
    }

    /**
     * 初始化游戏界面层
     * @param gameDTO 游戏 dto 数据
     */
    private void initLayer(GameDTO gameDTO) {
        try {
            // 获得游戏配置
            FrameConfig config = GameConfig.getFrameConfig();
            // 获得层配置
            List<LayerConfig> layerConfigs = config.getLayerConfigs();
            // 创建游戏层集合
            layers = new ArrayList<>(layerConfigs.size());
            // 创建所有层对象
            for (LayerConfig layerConfig : layerConfigs) {
                // 获得类对象
                Class<?> clazz = Class.forName(layerConfig.getClassName());
                // 获得构造函数
                Constructor<?> constructor = clazz.getConstructor(int.class, int.class, int.class, int.class);
                // 调用构造函数创建对象
                Layer layer = (Layer) constructor.newInstance(layerConfig.getX(), layerConfig.getY(), layerConfig.getW(), layerConfig.getH());
                // 设置游戏数据对象
                layer.setGameDTO(gameDTO);
                layers.add(layer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 控制按钮是否可点击
     */
    public void buttonSwitch(boolean onOff) {
        this.btnInit.setEnabled(onOff);
        this.btnStart.setEnabled(onOff);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 循环刷新游戏画面
        for (Layer layer : layers) {
            // 刷新层窗口
            layer.paint(g);
        }
    }
}
