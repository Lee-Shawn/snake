package com.laughing.snake.config;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;

/**
 * @author : laughing
 * @create : 2021-09-12 14:27
 * @description : 游戏配置
 */
public class GameConfig implements Serializable {
    private static FrameConfig FRAME_CONFIG = null;
    private static DataConfig DATA_CONFIG = null;
    private static SysConfig SYS_CONFIG = null;
    private static SettingConfig SETTING_CONFIG = null;
    // 是否从xml中读取配置
    private static final boolean isDebug = false;

    static {
        ObjectInputStream ois = null;
        try {
            // 开发时从 xml 文件读取配置文件，发布时写入对象流文件
            if (isDebug) {
                // 创建 XML 读取器
                SAXReader reader = new SAXReader();
                // 读取 XML 文件
                Document document = reader.read("src/main/resources/config/snake.xml");
                // 获取 XML 文件的根节点
                Element root = document.getRootElement();
                // 创建界面配置对象
                FRAME_CONFIG = new FrameConfig(root.element("frame"));
                // 创建系统对象
                SYS_CONFIG = new SysConfig(root.element("system"));
                // 创建数据访问配置对象
                DATA_CONFIG = new DataConfig(root.element("data"));
                // 创建游戏设置配置对象
                SETTING_CONFIG = new SettingConfig(root.element("setting"));
            } else {
                ois = new ObjectInputStream(new FileInputStream("src/main/resources/data/frameConfig.dat"));
                FRAME_CONFIG = (FrameConfig) ois.readObject();
                ois = new ObjectInputStream(new FileInputStream("src/main/resources/data/sysConfig.dat"));
                SYS_CONFIG = (SysConfig) ois.readObject();
                ois = new ObjectInputStream(new FileInputStream("src/main/resources/data/dataConfig.dat"));
                DATA_CONFIG = (DataConfig)ois.readObject();
                ois = new ObjectInputStream(new FileInputStream("src/main/resources/data/settingConfig.dat"));
                SETTING_CONFIG = (SettingConfig)ois.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private GameConfig() {}

    /**
     * 获取窗口配置
     * @return 窗口配置
     */
    public static FrameConfig getFrameConfig() {
        return FRAME_CONFIG;
    }

    /**
     * 获取游戏配置
     * @return 游戏配置
     */
    public static SysConfig getSysConfig() {
        return SYS_CONFIG;
    }

    /**
     * 获取数据配置
     * @return 数据配置
     */
    public static DataConfig getDataConfig() {
        return DATA_CONFIG;
    }

    /**
     * 获取游戏设置配置
     * @return 游戏设置配置
     */
    public static SettingConfig getSettingConfig() {
        return SETTING_CONFIG;
    }

    public static void main(String[] args) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/data/frameConfig.dat"));
        oos.writeObject(FRAME_CONFIG);
        oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/data/sysConfig.dat"));
        oos.writeObject(SYS_CONFIG);
        oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/data/dataConfig.dat"));
        oos.writeObject(DATA_CONFIG);
        oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/data/settingConfig.dat"));
        oos.writeObject(SETTING_CONFIG);
    }
}
