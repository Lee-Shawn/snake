package com.laughing.snake.view.ui;

import com.laughing.snake.config.FrameConfig;
import com.laughing.snake.config.GameConfig;
import com.laughing.snake.dto.GameDTO;

import java.awt.*;

/**
 * @author : laughing
 * @create : 2021-09-12 14:37
 * @description : 绘制窗口
 */
public abstract class Layer {
    /**
     * 内边距
     */
    protected static final int PADDING;

    /**
     * 边框宽度
     */
    protected static final int BORDER;

    /**
     * 方框宽度
     */
    private static final int WINDOW_W = Img.IMG_WINDOW.getWidth(null);
    /**
     * 方框高度
     */
    private static final int WINDOW_H = Img.IMG_WINDOW.getHeight(null);

    /**
     * 数字切片的宽度
     */
    protected static final int IMG_NUMBER_W = Img.IMG_NUMBER.getWidth(null) / 10;

    /**
     * 数字切片的高度
     */
    private static final int IMG_NUMBER_H = Img.IMG_NUMBER.getHeight(null);

    /**
     * 字体
     */
    public static final Font DEFAULT_FONT = new Font("华文行楷", Font.BOLD, 32);

    /**
     * 窗口左上角x坐标
     */
    protected int x;
    /**
     * 窗口左上角y坐标
     */
    protected int y;
    /**
     * 窗口宽度
     */
    protected int w;
    /**
     * 窗口高度
     */
    protected int h;

    /**
     * 游戏数据
     */
    protected GameDTO gameDTO = null;

    static {
        // 获取游戏配置
        FrameConfig config = GameConfig.getFrameConfig();
        PADDING = config.getPadding();
        BORDER = config.getBorder();
    }

    protected Layer(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * 绘制窗口图片
     * @param g 画笔
     */
    protected void createWindow(Graphics g) {
        // 左上
        g.drawImage(Img.IMG_WINDOW, x, y, x+BORDER, y+BORDER, 0, 0, BORDER, BORDER, null);
        // 中上
        g.drawImage(Img.IMG_WINDOW, x+BORDER, y, x+w-BORDER, y+BORDER, BORDER, 0, WINDOW_W-BORDER, BORDER, null);
        // 右上
        g.drawImage(Img.IMG_WINDOW, x+w-BORDER, y, x+w, y+BORDER, WINDOW_W-BORDER, 0, WINDOW_W, BORDER, null);
        // 左中
        g.drawImage(Img.IMG_WINDOW, x, y+BORDER, x+BORDER, y+h-BORDER, 0, BORDER, BORDER, WINDOW_H-BORDER, null);
        // 正中
        g.drawImage(Img.IMG_WINDOW, x+BORDER, y+BORDER, x+w-BORDER, y+h-BORDER, BORDER, BORDER, WINDOW_W-BORDER, WINDOW_H-BORDER, null);
        // 右中
        g.drawImage(Img.IMG_WINDOW, x+w-BORDER, y+BORDER, x+w, y+h-BORDER, WINDOW_W-BORDER, BORDER, WINDOW_W, WINDOW_H-BORDER, null);
        // 左下
        g.drawImage(Img.IMG_WINDOW, x, y+h-BORDER, x+BORDER, y+h, 0, WINDOW_H-BORDER, BORDER, WINDOW_H, null);
        // 中下
        g.drawImage(Img.IMG_WINDOW, x+BORDER, y+h-BORDER, x+w-BORDER, y+h, BORDER, WINDOW_H-BORDER, WINDOW_W-BORDER, WINDOW_H, null);
        // 右下
        g.drawImage(Img.IMG_WINDOW, x+w-BORDER, y+h-BORDER, w+x, y+h, WINDOW_W-BORDER, WINDOW_H-BORDER, WINDOW_W, WINDOW_H, null);
    }

    /**
     * 显示数字
     * @param x 左上角x坐标
     * @param y 左上角y坐标
     * @param num 要显示的数字
     * @param maxBit 数字位数
     * @param g 画笔
     */
    protected void drawNumber(int x, int y, int num, int maxBit, Graphics g) {
        // 把要打印的数字转换成字符串
        String numString = Integer.toString(num);
        // 循环绘制数字右对齐
        for (int i = 0; i < maxBit; i++) {
            // 判断是否满足绘制条件
            if (maxBit - i <= numString.length()) {
                // 获得数字在字符串中的下标
                int index = i - maxBit + numString.length();
                // 把数字 number 中的每一位取出
                int bit = numString.charAt(index) - '0';
                // 绘制数字
                g.drawImage(Img.IMG_NUMBER,
                        this.x + x + IMG_NUMBER_W * i, this.y + y,
                        this.x + x + IMG_NUMBER_W * (i+1), this.y + y + IMG_NUMBER_H,
                        bit * IMG_NUMBER_W, 0, (bit + 1) * IMG_NUMBER_W, IMG_NUMBER_H, null);
            }
        }
    }

    /**
     * 居中绘图
     * @param image 图片
     * @param g 画笔
     */
    protected void drawImageAtCenter(Image image, Graphics g)  {
        int imageW = image.getWidth(null);
        int imageH = image.getHeight(null);
        int imageX = this.x + (this.w - imageW >> 1);
        int imageY = this.y + (this.h - imageH >> 1);
        g.drawImage(image, imageX, imageY, null);
    }

    public void setGameDTO(GameDTO gameDTO) {
        this.gameDTO = gameDTO;
    }

    /**
     * 刷新游戏具体内容
     * @param g 画笔
     */
    abstract public void paint(Graphics g);
}
