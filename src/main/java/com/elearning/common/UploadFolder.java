package com.elearning.common;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/8/1 13:50
 */
public enum UploadFolder {



    seriesPic((short)0,"recommendSeries"),

    seriesMiniPic((short)1,"recommendSeries"),

    topicBanner((short)2,"topicBanner"),

    coursePic((short)3,"coursePic"),

    trainMiniPic((short)4,"trainPic"),

    trainZonePic((short)5,"trainZonePic"),

    teacherPic((short)6,"teacher"),

    bannerTemplate((short)7,"bannerTemplate"),

    userPic((short)8,"user"),

    banner((short)9,"banner"),

    orgPic((short)10,"orgPic");

    private UploadFolder(Short code,String folder){
        this.code=code;
        this.folder=folder;
    }


    private Short code;

    private String folder;

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public static UploadFolder findByCode(short code){
        switch(code){
            case (short)0:return seriesPic;
            case (short)1:return seriesMiniPic;
            case (short)2:return topicBanner;
            case (short)3:return coursePic;
            case (short)4:return trainMiniPic;
            case (short)5:return trainZonePic;
            case (short)6:return teacherPic;
            case (short)7:return bannerTemplate;
            case (short)8:return userPic;
            case (short)9:return banner;
            case (short)10:return orgPic;
            default:return null;
        }
    }




}
