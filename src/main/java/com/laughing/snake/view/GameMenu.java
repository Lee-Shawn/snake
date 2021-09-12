package com.laughing.snake.view;

import com.laughing.snake.controller.SnakeController;
import org.jb2011.lnf.beautyeye.ch9_menu.BEMenuItemUI;
import org.jb2011.lnf.beautyeye.ch9_menu.BEMenuUI;

import javax.swing.*;

/**
 * @author : laughing
 * @create : 2021-09-04 23:37
 * @description : 游戏菜单
 */
public class GameMenu extends JMenuBar {
    // 一级菜单
    private JMenu menu;
    private JMenu help;
    private JMenu about;
    // 二级菜单
    private JMenuItem reset;
    private JMenuItem settings;
    private JMenuItem exist;
    private JMenuItem guide;
    private JMenuItem log;
    private JMenuItem auth;

    private final SnakeController controller;

    public GameMenu() {
        this.initMenu();
        this.setTheme();
        controller = SnakeController.getInstance();
    }

    /**
     * 初始化菜单名称
     */
    private void initMenu() {
        menu = new JMenu("设置");
        help = new JMenu("帮助");
        about = new JMenu("关于");
        reset = new JMenuItem("重置游戏");
        settings = new JMenuItem("设置");
        exist = new JMenuItem("退出");
        guide = new JMenuItem("说明");
        log = new JMenuItem("日志");
        auth = new JMenuItem("作者");
    }

    /**
     * 设置菜单按钮主题
     */
    private void setTheme() {
        menu.setUI(new BEMenuUI());
        help.setUI(new BEMenuUI());
        about.setUI(new BEMenuUI());
        reset.setUI(new BEMenuItemUI());
        settings.setUI(new BEMenuItemUI());
        exist.setUI(new BEMenuItemUI());
        guide.setUI(new BEMenuItemUI());
        log.setUI(new BEMenuItemUI());
        auth.setUI(new BEMenuItemUI());
    }

    /**
     * 添加菜单到 Frame
     * @param menuBar 菜单对象
     */
    public void addMenu(JMenuBar menuBar) {
        menuBar.add(menu);
        menuBar.add(help);
        menuBar.add(about);
        menu.add(reset);
        menu.add(settings);
        menu.add(exist);
        help.add(guide);
        about.add(log);
        about.add(auth);
    }

    /**
     * 事件监听
     */
    public void listener() {
        startListener();
        settingsListener();
        existListener();
        guideListener();
        logListener();
        authListener();
    }

    private void startListener() {
        reset.addActionListener(e -> {
            controller.quit();
            controller.restartGame();
        });
    }

    private void settingsListener() {

    }

    private void existListener() {
        exist.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "确认退出？", "确认", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    private void guideListener() {

    }

    private void logListener() {

    }

    private void authListener() {

    }
}
