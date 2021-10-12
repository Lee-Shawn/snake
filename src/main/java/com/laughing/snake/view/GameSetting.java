package com.laughing.snake.view;

import com.laughing.snake.config.ControlConfig;
import com.laughing.snake.config.GameConfig;
import com.laughing.snake.config.MethodConfig;
import com.laughing.snake.config.SettingConfig;
import com.laughing.snake.controller.GameController;
import com.laughing.snake.dto.GameDTO;
import com.laughing.snake.view.ui.Img;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : laughing
 * @create : 2021-09-19 19:45
 * @description : 游戏设置面板
 */
public class GameSetting extends JFrame {
    /**
     * 控制按钮个数
     */
    private static final int CONTROL_SIZE = GameConfig.getSettingConfig().getMethod().size();

    /**
     * 控制按钮映射文件
     */
    private final static String PATH = GameConfig.getSettingConfig().getParam().get("path");

    /**
     * 确定按钮
     */
    private final JButton btnOk = new JButton(" 确定");

    /**
     * 取消按钮
     */
    private final JButton btnCancel = new JButton("取消");

    /**
     * 应用按钮
     */
    private final JButton btnUse = new JButton("应用");

    /**
     * 错误信息提示
     */
    private final JLabel errorMsg = new JLabel();

    /**
     * 控制按钮框
     */
    private final ControlTextField[] keyText = new ControlTextField[CONTROL_SIZE];

    /**
     * 方法名数组
     */
    private String[] METHOD_NAMES = null;

    /**
     * 皮肤选项卡预览框
     */
    private JPanel skinView = null;

    /**
     * 背景预览图
     */
    private Image[] skinViewList = null;

    /**
     * 皮肤选项卡数据
     */
    private JList<String> skinList = null;

    /**
     * 皮肤选项卡
     */
    private final DefaultListModel<String> skinData = new DefaultListModel<>();

    /**
     * 网格按钮
     */
    private JCheckBox gridCheckBox = null;

    /**
     * 穿墙开关
     */
    private JToggleButton clipToggle = null;

    /**
     * 游戏控制器
     */
    private final GameController gameController;

    /**
     * 游戏数据
     */
    private final GameDTO gameDTO;

    public GameSetting(GameController gameController, GameDTO gameDTO) {
        // 获取游戏设置配置
        SettingConfig settingConfig = GameConfig.getSettingConfig();
        // 获得游戏控制器对象
        this.gameController = gameController;
        // 获取游戏数据
        this.gameDTO = gameDTO;
        // 设置布局管理器为“边界布局”
        this.setLayout(new BorderLayout());
        // 初始化方法名数组
        this.initMethodName();
        // 初始化按键输入框
        this.initKeyText();
        // 添加主面板
        this.add(this.createMainPanel(), BorderLayout.CENTER);
        // 添加按钮面板
        this.add(this.createButtonPanel(), BorderLayout.SOUTH);
        // 设置标题
        this.setTitle(settingConfig.getTitle());
        // 游戏基本设置界面添加监听器
        this.basicPanelListener();
        // 设置窗口大小不能改变
        this.setResizable(false);
        // 设置窗口大小
        this.setSize(settingConfig.getWidth(), settingConfig.getHeight());
        // 设置窗口居中
        this.setLocationRelativeTo(null);
    }

    /**
     * 初始化方法名数组
     */
    private void initMethodName() {
        List<MethodConfig> methods = GameConfig.getSettingConfig().getMethod();
        METHOD_NAMES = new String[methods.size()];
        for (int i = 0; i < METHOD_NAMES.length; i++) {
            METHOD_NAMES[i] = methods.get(i).getName();
        }
    }

    /**
     * 初始化按键输入框
     */
    private void initKeyText() {
        // 控制按钮配置框位置
        SettingConfig settingConfig = GameConfig.getSettingConfig();
        List<ControlConfig> controlConfigs = settingConfig.getControlConfigs();
        for (ControlConfig c : controlConfigs) {
            keyText[c.getIndex()] = new ControlTextField(c.getX(), c.getY(), c.getW(), c.getH(), METHOD_NAMES[c.getIndex()]);
        }
        // 读取配置文件中的数据
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
            HashMap<Integer, String> cfgSet = (HashMap<Integer, String>) ois.readObject();
            ois.close();
            Set<Map.Entry<Integer, String>> entrySet = cfgSet.entrySet();
            for (Map.Entry<Integer, String> e: entrySet) {
                for (ControlTextField settingPanel : keyText) {
                    if (settingPanel.getMethodName().equals(e.getValue())) {
                        settingPanel.setKeyCode(e.getKey());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建主面板(选项卡面板)
     */
    private JTabbedPane createMainPanel() {
        JTabbedPane jtp = new JTabbedPane();
        jtp.addTab("控制设置", this.createControlPanel());
        jtp.addTab("皮肤设置", this.createSkinPanel());
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(Img.IMG_BASIC, 0, 0, null);
            }
        };
        JPanel p1 = this.createBasicPanel();
        p1.setBorder(BorderFactory.createTitledBorder("游戏界面设置"));
        JPanel p2 = this.createSkillsPanel();
        p2.setBorder(BorderFactory.createTitledBorder("游戏技能设置"));
        panel.add(p1);
        panel.add(p2);
        jtp.addTab("基本设置", panel);
        return jtp;
    }

    /**
     * 创建按钮面板
     */
    private JPanel createButtonPanel() {
        // 创建按钮面板流式布局
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        // 给确定按钮添加事件监听
        this.btnOk.addActionListener(e -> {
            if(writeConfig()) {
                setVisible(false);
                gameController.setOver();
            }
        });
        // 设置错误信息前景色
        this.errorMsg.setForeground(Color.RED);
        // 增加错误信息到面板
        jp.add(errorMsg);
        // 增加确定按钮到面板
        jp.add(this.btnOk);
        // 给取消按钮添加事件监听
        this.btnCancel.addActionListener(e -> {
            setVisible(false);
            gameController.setOver();
        });
        // 增加取消按钮到面板
        jp.add(this.btnCancel);
        // 给应用按钮添加事件监听
        this.btnUse.addActionListener(e -> {
            writeConfig();
            // 刷新主窗口
            gameController.repaint();
        });
        // 增加应用按钮到面板
        jp.add(this.btnUse);
        return jp;
    }

    /**
     * 玩家皮肤面板
     */
    private Component createSkinPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        File dir = new File(Img.GRAPHICS_PATH);
        File[] files = dir.listFiles();
        if (files == null) {
            throw new RuntimeException("目录不存在！");
        }
        this.skinViewList = new Image[files.length];
        for (int i = 0; i < files.length; i++) {
            // 增加选项
            this.skinData.addElement(files[i].getName());
            // 增加预览图
            this.skinViewList[i] =new ImageIcon(files[i].getPath() + "\\view.png").getImage();
        }
        // 添加列表数据到列表
        this.skinList = new JList<>(this.skinData);
        // 默认选择第一个
        this.skinList.setSelectedIndex(0);
        this.skinList.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                repaint();
            }
        });
        this.skinView = new JPanel() {
            public void paintComponent(Graphics g) {
                Image showImg = skinViewList[skinList.getSelectedIndex()];
                // 预览图居中显示
                int x = this.getWidth() - showImg.getWidth(null) >> 1;
                int y = this.getHeight() - showImg.getHeight(null) >> 1;
                g.drawImage(showImg, x, y, null);
            }
        };
        panel.add(new JScrollPane(this.skinList), BorderLayout.WEST);
        panel.add(this.skinView, BorderLayout.CENTER);
        return panel;
    }

    /**
     * 游戏界面设置
     * @return 游戏界面设置面板
     */
    private JPanel createBasicPanel() {
        JPanel panel = new JPanel();
        this.gridCheckBox = new JCheckBox("网格", true);
        panel.setPreferredSize(new Dimension(600, 300));
        panel.add(gridCheckBox);
        return panel;
    }

    /**
     * 游戏技能设置
     * @return 游戏技能设置面板
     */
    private JPanel createSkillsPanel() {
        JPanel panel = new JPanel();
        this.clipToggle = new JToggleButton("穿墙", false);
        panel.setPreferredSize(new Dimension(600, 300));
        panel.add(clipToggle);
        return panel;
    }

    /**
     * 玩家控制设置面板
     */
    private JPanel createControlPanel() {
        JPanel jp = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(Img.IMG_CONTROL_SET, 0, 0, null);
            }
        };
        // 设置布局管理器
        jp.setLayout(null);
        for (ControlTextField setting : keyText) {
            jp.add(setting);
        }

        return jp;
    }

    /**
     * 游戏基本设置界面监听
     */
    private void basicPanelListener() {
        // 网格设置监听
        this.gridCheckBox.addActionListener(e -> this.gameDTO.setGrid(gridCheckBox.isSelected()));
        // 穿墙设置监听
        this.clipToggle.addActionListener(e -> {

        });
    }

    /**
     * 写游戏配置
     */
    public boolean writeConfig() {
        HashMap<Integer, String> keySet = new HashMap<>();
        for (ControlTextField setting : keyText) {
            int keyCode = setting.getKeyCode();
            if (keyCode == 0) {
                this.errorMsg.setText("错误按键！");
                return false;
            }
            keySet.put(keyCode, setting.getMethodName());
        }
        // 设置了重复的按键
        if (keySet.size() != keyText.length) {
            this.errorMsg.setText("重复按键！");
            return false;
        }

        try {
            // 切换皮肤
            Img.setSkin(this.skinData.get(this.skinList.getSelectedIndex()));
            // 写入控制配置
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
            oos.writeObject(keySet);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            this.errorMsg.setText(e.getMessage());
            return false;
        }
        this.errorMsg.setText(null);

        return true;
    }
}
