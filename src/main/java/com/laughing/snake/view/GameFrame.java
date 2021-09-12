package com.laughing.snake.view;

import com.laughing.snake.common.Constant;
import com.laughing.snake.controller.SnakeController;
import com.laughing.snake.entity.Scene;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author : laughing
 * @create : 2021-08-24 19:34
 * @description : 游戏窗体
 */
public class GameFrame {
    Scene scene;
    GameView view;
    GameMenu menu;
    JFrame frame;
    Container container;
    SnakeController controller;

    public GameFrame() {
        scene = Scene.getInstance();
        view = GameView.getInstance();
        menu = new GameMenu();
        frame = new JFrame();
        container = frame.getContentPane();
        controller = SnakeController.getInstance();
    }

    public void init() {
        // 窗口大小
        frame.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        // 窗口居中
        frame.setLocationRelativeTo(null);
        // 窗口标题
        frame.setTitle("贪吃蛇");
        // 显示窗口
        frame.setVisible(true);
        // 设置窗口大小不可变
        frame.setResizable(false);
        // panel 添加到 frame 中
        view.init();
        view.addComponent();
        // 设置 JPanel 大小
        view.getPanel().setPreferredSize(new Dimension(Constant.GAME_WIDTH, Constant.GAME_HEIGHT));
        // view 加入到 JPanel 窗体中
        container.add(view.getPanel(), BorderLayout.CENTER);
        // 添加菜单
        frame.setJMenuBar(menu);
        menu.addMenu(menu);
        menu.listener();
        // 获取焦点
        frame.setFocusable(true);
        // 添加事件监听
        frame.addKeyListener(controller);
        view.addControlListener();
        // 启动线程
        //new Thread(controller).start();

        // 退出确认
        EventQueue.invokeLater(() ->{
            // 不执行任何操作，在 windowClosing 中处理
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            // 监听窗口退出事件
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int result = JOptionPane.showConfirmDialog(null, "确认退出？", "确认", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            });
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 修改主题
            try {
                BeautyEyeLNFHelper.launchBeautyEyeLNF();
                UIManager.put("RootPane.setupButtonVisible", false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            GameFrame frame = new GameFrame();
            frame.init();
        });
    }
}