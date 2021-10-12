package com.laughing.snake.view;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author : laughing
 * @create : 2021-09-19 19:49
 * @description : 控制按钮设置文本框
 */
public class ControlTextField extends JTextField {
    private int keyCode;

    private final String methodName;

    public ControlTextField(int x, int y, int w, int h, String methodName) {
        // 设置文本框位置
        this.setBounds(x, y, w, h);
        // 初始化方法名
        this.methodName = methodName;
        // 增加事件监听
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            /**
             * 键盘松开
             */
            @Override
            public void keyPressed(KeyEvent e) {
                setKeyCode(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
        this.setText(KeyEvent.getKeyText(this.keyCode));
    }

    public String getMethodName() {
        return methodName;
    }
}
