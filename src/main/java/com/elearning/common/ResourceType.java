package com.elearning.common;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/15 14:25
 */
public enum ResourceType {

    //课程
    COURSE(0,"课件资源","/onlineCourse/detail.do?bookId="),
    //培训
    TRAIN(1,"培训项目","onlineTraining.do?method=viewTrain4Admin&trainId="),
    //新闻
    NEWS(2,"新闻报道","mtMixTrainNewsAction.do?method=getNewsDetailFromMainPage&newsType=0&newsId="),
    //通知
    NOTICE(3,"培训通知","msgMessageInfoAction.do?method=getNoticeDetail&id="),
    //专题
    TOPIC(4,"系列专题","/recommendSeries/intoSeriesItemFrame.do?seriesId="),
    //直播
    LIVE(5,"直播","live.do?method=intoLiveZone&live_id="),
    //培训计划
    PLAN(6,"培训计划",""),
    //组织机构
    ORG(7,"组织机构","");


    private ResourceType(Integer typeCode,String name,String openUrl){
        this.openUrl=openUrl;
        this.name=name;
        this.typeCode=typeCode;
    }

    public static ResourceType findByCode(Integer code){
        switch(code){
            case 0:return ResourceType.COURSE;
            case 1:return ResourceType.TRAIN;
            case 2:return ResourceType.NEWS;
            case 3:return ResourceType.NOTICE;
            case 4:return ResourceType.TOPIC;
            case 5:return ResourceType.LIVE;
            case 6:return ResourceType.PLAN;
            case 7:return ResourceType.ORG;
            default:return null;
        }
    }

    private final String openUrl;

    private final String name;

    private final Integer typeCode;

    public String getOpenUrl() {
        return openUrl;
    }

    public String getName() {
        return name;
    }

    public Integer getTypeCode() {
        return typeCode;
    }
}
