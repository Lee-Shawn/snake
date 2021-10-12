package com.laughing.snake.main;

import com.laughing.snake.controller.GameController;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;

/**
 * @author : laughing
 * @create : 2021-09-12 13:44
 * @description : 项目入口
 */
public class GameMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 修改主题
            try {
                BeautyEyeLNFHelper.launchBeautyEyeLNF();
                UIManager.put("RootPane.setupButtonVisible", false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            new GameController();
        });
    }
}
