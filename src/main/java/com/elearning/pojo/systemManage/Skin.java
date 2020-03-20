package com.elearning.pojo.systemManage;

public class Skin {
    private Integer skinId;

    private String skinName;

    private String defaultLogo;

    private String defaultBottom;

    private String color;

    private String defaultElearningName;

    private String customBottom;

    private String customElearningName;

    private Integer useFlag;

    private Integer modifyFlag;

    private String cssDir;

    private Integer defaultPageFlag;

    private byte[] customLogo;

    public Skin(Integer skinId, String skinName, String defaultLogo, String defaultBottom, String color, String defaultElearningName, String customBottom, String customElearningName, Integer useFlag, Integer modifyFlag, String cssDir, Integer defaultPageFlag, byte[] customLogo) {
        this.skinId = skinId;
        this.skinName = skinName;
        this.defaultLogo = defaultLogo;
        this.defaultBottom = defaultBottom;
        this.color = color;
        this.defaultElearningName = defaultElearningName;
        this.customBottom = customBottom;
        this.customElearningName = customElearningName;
        this.useFlag = useFlag;
        this.modifyFlag = modifyFlag;
        this.cssDir = cssDir;
        this.defaultPageFlag = defaultPageFlag;
        this.customLogo = customLogo;
    }

    public Skin() {
        super();
    }

    public Integer getSkinId() {
        return skinId;
    }

    public void setSkinId(Integer skinId) {
        this.skinId = skinId;
    }

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName == null ? null : skinName.trim();
    }

    public String getDefaultLogo() {
        return defaultLogo;
    }

    public void setDefaultLogo(String defaultLogo) {
        this.defaultLogo = defaultLogo == null ? null : defaultLogo.trim();
    }

    public String getDefaultBottom() {
        return defaultBottom;
    }

    public void setDefaultBottom(String defaultBottom) {
        this.defaultBottom = defaultBottom == null ? null : defaultBottom.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getDefaultElearningName() {
        return defaultElearningName;
    }

    public void setDefaultElearningName(String defaultElearningName) {
        this.defaultElearningName = defaultElearningName == null ? null : defaultElearningName.trim();
    }

    public String getCustomBottom() {
        return customBottom;
    }

    public void setCustomBottom(String customBottom) {
        this.customBottom = customBottom == null ? null : customBottom.trim();
    }

    public String getCustomElearningName() {
        return customElearningName;
    }

    public void setCustomElearningName(String customElearningName) {
        this.customElearningName = customElearningName == null ? null : customElearningName.trim();
    }

    public Integer getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Integer useFlag) {
        this.useFlag = useFlag;
    }

    public Integer getModifyFlag() {
        return modifyFlag;
    }

    public void setModifyFlag(Integer modifyFlag) {
        this.modifyFlag = modifyFlag;
    }

    public String getCssDir() {
        return cssDir;
    }

    public void setCssDir(String cssDir) {
        this.cssDir = cssDir == null ? null : cssDir.trim();
    }

    public Integer getDefaultPageFlag() {
        return defaultPageFlag;
    }

    public void setDefaultPageFlag(Integer defaultPageFlag) {
        this.defaultPageFlag = defaultPageFlag;
    }

    public byte[] getCustomLogo() {
        return customLogo;
    }

    public void setCustomLogo(byte[] customLogo) {
        this.customLogo = customLogo;
    }
}