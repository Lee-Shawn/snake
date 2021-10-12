package com.laughing.snake.view;

import com.laughing.snake.config.FrameConfig;
import com.laughing.snake.config.GameConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author : laughing
 * @create : 2021-09-12 14:22
 * @description : 游戏 frame
 */
public class GameFrame extends JFrame {
    public GameFrame(GamePanel panel, GameMenu menu) {
        // 获取 frame 配置
        FrameConfig frameConfig = GameConfig.getFrameConfig();
        // 设置游戏标题
        this.setTitle(frameConfig.getTitle());
        // 设置游戏窗口大小
        this.setSize(frameConfig.getWidth(), frameConfig.getHeight());
        // 设置窗口居中
        this.setLocationRelativeTo(null);
        // 设置窗口默认可见
        this.setVisible(true);
        // 设置窗口大小不可变
        this.setResizable(false);
        // 设置默认 panel
        this.setContentPane(panel);
        this.setJMenuBar(menu);
        // 退出确认
        EventQueue.invokeLater(() ->{
            // 不执行任何操作，在 windowClosing 中处理
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            // 监听窗口退出事件
            this.addWindowListener(new WindowAdapter() {
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
}
