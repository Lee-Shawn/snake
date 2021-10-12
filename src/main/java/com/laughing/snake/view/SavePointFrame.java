package com.laughing.snake.view;


import com.laughing.snake.controller.GameController;
import com.laughing.snake.view.ui.Layer;

import javax.swing.*;
import java.awt.*;

/**
 * @author : laughing
 * @create : 2021-10-03 12:15
 * @description : 保存分数
 */
public class SavePointFrame extends JFrame {
    /**
     * 分数提示信息
     */
    private JLabel labelPoint = null;

    /**
     * 玩家名称提示信息
     */
    private JTextField username = null;

    /**
     * 确定按钮
     */
    private JButton buttonOK = null;

    /**
     * 错误信息
     */
    private JLabel errorMessage = null;

    /**
     * 游戏控制器
     */
    private final GameController controller;

    public SavePointFrame(GameController controller) {
        this.controller = controller;
        this.setTitle("保存记录");
        this.setSize(256, 192);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.addControls();
        this.eventListener();
    }

    /**
     * 显示保存分数窗口
     * @param point 玩家分数
     */
    public void show(int point) {
        this.setFont(Layer.DEFAULT_FONT);
        this.labelPoint.setText("你的得分：" + point);
        this.setVisible(true);
    }

    /**
     * 添加事件监听
     */
    private void eventListener() {
        this.buttonOK.addActionListener(e -> {
            String name = username.getText();
            if (name.length() > 20 || "".equals(name)) {
                errorMessage.setText("玩家名称错误！");
            } else {
                setVisible(false);
                controller.savePoint(name);
                // 保存分数后，清除窗口中的用户信息
                username.setText("");
            }
        });
    }

    /**
     * 添加控件
     */
    private void addControls() {
        // 创建北部面板（流式布局）
        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // 显示分数
        this.labelPoint = new JLabel();
        // 添加分数到北部面板
        north.add(this.labelPoint);
        // 创建错误信息控件
        this.errorMessage = new JLabel();
        // 设置前景色
        this.errorMessage.setForeground(Color.RED);
        // 添加错误信息到北部面板
        north.add(this.errorMessage);
        // 北部面板添加到主面板
        this.add(north, BorderLayout.NORTH);

        // 创建中间面板（流式布局）
        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // 创建文本框
        this.username = new JTextField(10);
        // 显示名字
        center.add(new JLabel("请输入你的名字："));
        // 添加名字到中间面板
        center.add(this.username);
        // 中间面板添加到主面板
        this.add(center, BorderLayout.CENTER);

        // 创建确定按钮
        this.buttonOK = new JButton("确定");
        // 创建南部面板（流式布局）
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // 按钮添加到南部面板
        south.add(buttonOK);
        // 南部面板添加到主面板
        this.add(south, BorderLayout.SOUTH);
    }
}
