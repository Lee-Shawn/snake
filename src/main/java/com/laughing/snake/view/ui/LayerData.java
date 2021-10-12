package com.laughing.snake.view.ui;

import com.laughing.snake.config.GameConfig;
import com.laughing.snake.dto.Player;

import java.awt.*;
import java.util.List;

/**
 * @author : laughing
 * @create : 2021-09-13 20:25
 * @description : 记录接口
 */
abstract class LayerData extends Layer {
    /**
     * 最大数据行
     */
    private static final int MAX_ROW = GameConfig.getDataConfig().getMaxRow();

    /**
     * 起始y坐标
     */
    private static int START_Y = 0;

    /**
     * 间距
     */
    private static int SPACING = 0;

    /**
     * 每条记录的高度
     */
    private static final int RECORD_H = PADDING;

    public LayerData(int x, int y, int w, int h) {
        super(x, y, w, h);
        // 计算记录绘制间距
        SPACING = (this.h - RECORD_H * 5 - (PADDING << 1) - (BORDER << 1) - Img.IMG_LIST.getHeight(null)) / MAX_ROW;
        // 计算起始y坐标
        START_Y = BORDER + PADDING / 2 + Img.IMG_LIST.getHeight(null) + SPACING;
    }

    /**
     * 显示排行榜记录
     * @param title 窗口标题
     * @param players 记录
     * @param g 画笔
     */
    public void showData(Image title, List<Player> players, Graphics g) {
        // 绘制标题
        g.drawImage(title, this.x + PADDING, this.y + PADDING, null);
        if (players == null) {
            g.setColor(Color.RED);
            g.setFont(DEFAULT_FONT);
            g.drawString("等你来挑战~~~", this.x + (PADDING * 2), this.y + (PADDING * 6));
            return;
        }
        for (Player player : players) {
            // 获取每条记录的分数
            //int recordPoint = player.getPoint();
            String recordPoint = Integer.toString(player.getPoint());
            g.setColor(Color.RED);
            g.setFont(DEFAULT_FONT);
            // drawString 是从字符串左下角开始算位置，不是左上角
            int recordY = this.y +  START_Y + (SPACING + RECORD_H) * (players.indexOf(player) + 1);
            g.drawString(player.getName(), this.x + PADDING, recordY);
            g.drawString(recordPoint, this.x + this.w - PADDING - BORDER - 15 * 4, recordY);
            //this.drawNumber(100, recordY, recordPoint, 5, g);
        }
    }

    @Override
    public abstract void paint(Graphics g);
}
