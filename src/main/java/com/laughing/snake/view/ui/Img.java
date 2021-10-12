package com.laughing.snake.view.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : laughing
 * @create : 2021-09-12 17:19
 * @description :
 */
public class Img {
    private Img() {}

    /**
     * 图片路径
     */
    public static final String GRAPHICS_PATH = "src/main/resources/image/";
    public static final String DEFAULT_PATH = "default";

    /**
     * 蛇头
     */
    public static Image IMG_HEAD_LEFT = null;
    public static Image IMG_HEAD_RIGHT = null;
    public static Image IMG_HEAD_UP = null;
    public static Image IMG_HEAD_DOWN = null;

    /**
     * 蛇身
     */
    public static Image IMG_BODY = null;

    /**
     * 食物
     */
    public static Image IMG_FOOD = null;

    /**
     * 游戏结束蛇的样子
     */
    public static Image IMG_OVER = null;

    /**
     * 方框图片
     */
    public static Image IMG_WINDOW = null;

    /**
     * 分数图片
     */
    public static Image IMG_POINT = null;

    /**
     * 游戏等级
     */
    public static Image IMG_LEVEL = null;

    /**
     * 数字图片
     */
    public static Image IMG_NUMBER = null;

    /**
     * 排行榜
     */
    public static Image IMG_LIST = null;

    /**
     * 暂停
     */
    public static Image IMG_PAUSE = null;

    /**
     * 游戏结束
     */
    public static Image IMG_FAIL = null;

    /**
     * 个人签名
     */
    public static Image IMG_SIGN = null;

    /**
     * 开始按钮
     */
    public static ImageIcon BTN_START = null;

    /**
     * 重新游戏按钮
     */
    public static ImageIcon BTN_INIT = null;

    /**
     * 背景图片
     */
    public static List<Image> BG_LIST = null;

    /**
     * 控制设置背景图
     */
    public static Image IMG_CONTROL_SET = null;

    /**
     * 基础设置背景图
     */
    public static Image IMG_BASIC = null;

    /**
     * 设置图片
     * @param path 路径
     */
    public static void setSkin(String path) {
        String skinPath = GRAPHICS_PATH + path;
        IMG_HEAD_LEFT = new ImageIcon(skinPath + "/object/left.png").getImage();
        IMG_HEAD_RIGHT = new ImageIcon(skinPath + "/object/right.png").getImage();
        IMG_HEAD_UP = new ImageIcon(skinPath + "/object/up.png").getImage();
        IMG_HEAD_DOWN = new ImageIcon(skinPath + "/object/down.png").getImage();
        IMG_BODY = new ImageIcon(skinPath + "/object/body.png").getImage();
        IMG_FOOD = new ImageIcon(skinPath + "/object/food.png").getImage();
        IMG_OVER = new ImageIcon(skinPath + "/object/over.png").getImage();
        IMG_WINDOW = new ImageIcon(skinPath + "/window/Window.png").getImage();
        IMG_POINT = new ImageIcon(skinPath + "/string/point.png").getImage();
        IMG_LEVEL = new ImageIcon(skinPath + "/string/level.png").getImage();
        IMG_NUMBER = new ImageIcon(skinPath + "/string/num.png").getImage();
        IMG_LIST = new ImageIcon(skinPath + "/string/list.png").getImage();
        IMG_PAUSE = new ImageIcon(skinPath + "/string/pause.png").getImage();
        IMG_FAIL = new ImageIcon(skinPath + "/string/fail.png").getImage();
        BTN_START = new ImageIcon(skinPath + "/string/start.png");
        BTN_INIT = new ImageIcon(skinPath + "/string/config.png");
        IMG_SIGN = new ImageIcon(skinPath + "/string/sign.png").getImage();
        IMG_CONTROL_SET = new ImageIcon(skinPath + "/setting/control.png").getImage();
        IMG_BASIC = new ImageIcon(skinPath + "/setting/basic.png").getImage();
        // 背景图片
        File dir = new File(skinPath + "/background");
        File[] files = dir.listFiles();
        if (files == null) {
            throw new RuntimeException("图片不存在！");
        }
        BG_LIST = new ArrayList<>();
        for (File file : files) {
            if (!file.isDirectory()) {
                BG_LIST.add(new ImageIcon(file.getPath()).getImage());
            }
        }
    }

    // 初始化图片
    static {
        setSkin(DEFAULT_PATH);
    }
}
