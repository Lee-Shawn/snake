package com.laughing.snake.view;

import com.laughing.snake.controller.GameController;

import javax.swing.*;

/**
 * @author : laughing
 * @create : 2021-09-13 21:27
 * @description : 游戏菜单
 */
public class GameMenu extends JMenuBar {
    private final JMenu setting = new JMenu("设置");

    private final JMenu about = new JMenu("关于");

    private final JCheckBox music = new JCheckBox("音乐");

    private final JMenuItem exist = new JMenuItem("退出");

    private final JMenuItem help = new JMenuItem("帮助");

    private final JMenuItem auth = new JMenuItem("作者");

    private final GameController gameController;

    public GameMenu(GameController gameController) {
        this.gameController = gameController;
        this.listener();
    }

    /**
     * 初始化菜单控件
     * @param bar 菜单按钮
     */
    public void initMenu(JMenuBar bar) {
        bar.add(setting);
        bar.add(about);
        setting.add(music);
        setting.add(exist);
        about.add(help);
        about.add(auth);
        // 设置音乐按钮默认选中
        music.setSelected(true);
    }

    /**
     * 菜单事件监听
     */
    private void listener() {
        this.musicListener();
        this.existListener();
        this.helpListener();
        this.authInfoListener();
    }

    /**
     * 音乐开关
     */
    private void musicListener() {
        music.addActionListener(e -> this.gameController.musicSwitch());
    }

    /**
     * 退出游戏
     */
    private void existListener() {
        exist.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "确认退出？", "确认", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    /**
     * 游戏帮助说明
     */
    private void helpListener() {
        help.addActionListener(e -> {
            String message = "使用说明：\n" +
                    "1. 点击设置按钮\n" +
                    "2. 选择控制设置，设置游戏按钮\n" +
                    "3. 选择皮肤设置，选择游戏皮肤\n" +
                    "4. 点击开始按钮，开始游戏\n" +
                    "5. 游戏结束，输入玩家信息保存数据";
            JOptionPane.showMessageDialog(null, message,"帮助",JOptionPane.INFORMATION_MESSAGE);
        });
    }

    /**
     * 版权信息
     */
    private void authInfoListener() {
        auth.addActionListener(e -> {
            String message = "Version: V2.0.0\n" +
                    "Auth: 恰似你的温柔\n" +
                    "GitHub: https://github.com/Lee-Shawn/snake\n" +
                    "Copyright: laughing 2021";
            JOptionPane.showMessageDialog(null, message,"作者",JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
