package com.laughing.snake.view.ui;

import com.laughing.snake.config.FrameConfig;
import com.laughing.snake.config.GameConfig;

import java.awt.*;

/**
 * @author : laughing
 * @create : 2021-09-12 17:17
 * @description : 背景图片
 */
public class LayerBackGround extends Layer {
    public LayerBackGround(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void paint(Graphics g) {
        FrameConfig config = GameConfig.getFrameConfig();
        // 根据等级换背景图片
        int bgIndex = this.gameDTO.getGameLevel() % Img.BG_LIST.size();
        g.drawImage(Img.BG_LIST.get(bgIndex), 0, 0, config.getWidth(), config.getHeight(), null);
    }
}
