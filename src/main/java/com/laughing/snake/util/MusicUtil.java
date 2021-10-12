package com.laughing.snake.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.InputStream;

/**
 * @author : laughing
 * @create : 2021-09-28 22:33
 * @description : 背景音乐
 */
public class MusicUtil {
    private static final String PATH = "music/你一定要幸福.wav";
    private static Clip clip = null;

    static {
        try {
            clip = AudioSystem.getClip();
            InputStream is = MusicUtil.class.getClassLoader().getResourceAsStream(PATH);
            AudioInputStream ais = AudioSystem.getAudioInputStream(is);
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放音乐
     */
    public static void playBGM() {
        // 设置音量
        FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        floatControl.setValue(-10.0f);
        // 循环播放
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * 停止播放
     */
    public static void stopBGM() {
        clip.stop();
    }
}
