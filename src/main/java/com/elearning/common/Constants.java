package com.elearning.common;

import com.elearning.pojo.systemManage.Resource;
import com.elearning.util.PropertiesUtil;

import java.util.*;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/22 8:45
 */
public class Constants {

    public final static String USERNAME_KEY = "username";
    public final static String USERINFO_KEY = "userinfo";
    public final static String ADMINNAME_KEY = "adminname";
    public final static String YEAR_KEY = "year";         //当前年度
    public final static String ROOTORGID_KEY = "rootOrgId";       //用户最大权限根节点机构ID
    public final static String ROOTVORGID_KEY = "virtualOrgId";       //用户最大权限根节点虚拟机构ID

    public final static String USERROLE_KEY = "userrolemap";
    public final static String ROLERESOURCE_KEY="roleresourcemap";
    public final static String RESOURCERELATION_KEY="resourcerelation";
    public final static String LOGOUT_KEY = "logout";
    public final static String LOGON_KEY = "logon";
    public final static String SUCCESS_KEY = "success";
    public final static String FAILURE_KEY = "failure";
    public final static String PASSWORD_CHANGE = "enforcePwdchange";
    public final static String USERHASMENU_KEY="userHasMenu";
    public final static String VIDEO_CONVERT_STATUS="转化中";
    public final static String VIDEO_UNCONVERT_STATUS="不转化";
    public final static String VIDEO_CONVERT_COMPLETE="转化完成";

    public final static String VIDEO_POSTFIX = ".mp4";
    public final static String VIDEO_NAME_PREFIX = "sv";

    public static String FAVICON_ICO_URL = "http://159.226.3.197/uploadFile/favicon.ico";

    public static String STATICSERVER_URL = null;

    public static String orgSEQ;

    public final static int pageSize = 5;
    public final static int pageSize2 = 10;
    public final static int pageSize16 = 16;
    public final static int pageSize20 = 20;
    public final static int pageSize24 = 24;
    public final static int pageSize25 = 25;
    public final static int pageNo = 1;
    public static int pgSize = 5;
    //public final static String password = "Cast_654321";
    public final static String password = "999999";

    public final static String TRAIN="TRAIN";
    public final static String COURSELIST="COURSELIST";
    public final static String DEPTLIST="DEPTLIST";
    public final static String USERLIST="USERLIST";

    //对考题和考卷的分类（培训、课程、其它）
    public final static String I_CLASSIFY_TRAIN="0";
    public final static String I_CLASSIFY_COURSE="1";
    public final static String I_CLASSIFY_OTHER="2";
    public final static String I_CLASSIFY_ALL="3";

    public final static String S_CLASSIFY_TRAIN="培训";
    public final static String S_CLASSIFY_COURSE="课程";
    public final static String S_CLASSIFY_OTHER="其它";
    public final static String S_CLASSIFY_ALL="全部";

    //总培训时长
    public final static Integer Training_Total_Hour = 100;
    //  1小时=3600秒
    public final static Integer Hour_To_Seconds = 3600;

    //骨干学员评选，单次培训超过40小时
    public final static Integer BackbonePeople_SH = 40;
    //    骨干学员评选，总培训学时超过100小时
    public final static Integer BackbonePeople_TH = 100;
    //    网络学时标准,50小时
    public final static Integer S_OnlineTime = 50;
    //    在职自学标准,10小时
    public final static Integer S_SelfLearningTime = 10;
    //    公派留学标准,100小时
    public final static Integer S_OverSeasStudyTime = 100;

    //  骨干人才标准
    public final static Double S_BackbonePeople = 0.04;

    //不存在
    public final static Integer NO_EXIT=0;

    //审批参数列表
    public final static Integer I_AUDIT_NOTAUDIT = new Integer(1051);
    public final static Integer I_AUDIT_PASS = new Integer(1052);
    public final static Integer I_AUDIT_NOTPASS = new Integer(1053);
    public final static Integer I_AUDIT_AUDITING = new Integer(1054);


    //试卷批改状态
    public final static String S_PAPERAUDIT_NO = "未批改";
    public final static String S_PAPERAUDIT_YES = "已批改";
    public final static String S_PAPERAUDIT_NONEED = "线下考试，无需批改";
    public final static String S_EXAMPARTSTATUS_APPLY = "已考";
    public final static String S_EXAMPARTSTATUS_YES = "已考";
    public final static String S_EXAMPARTSTATUS_NO = "未考";
    public final static String S_EXAMPARTSTATUS_STOP = "考试已截止";
    public final static String S_EXAMPARTSTATUS_NONEED = "线下考试";
    public final static String S_EXAMAUDIT_AUDITING = "正在审批..";
    public final static String S_EXAM_APPLYABLE = "可以报名";

    //考试类型
    public final static Integer I_EXAMTYPE_ONLINE = new Integer(0);
    public final static String S_EXAMTYPE_ONLINE = "线上";
    public final static Integer I_EXAMTYPE_OFFLINE = new Integer(1);
    public final static String S_EXAMTYPE_OFFLINE = "线下";
    public final static String S_EXAMSTYLE_OPEN = "开卷";
    public final static String S_EXAMSTYLE_CLOSE = "闭卷";

    //课程学习情况
    public final static String NOT_ATTEMPTED = "未学习";
    public final static String COMPLETED = "已完成";
    public final static String INCOMPLETE = "未完成";

    //课程适用岗位类型
    public final static String GANGWEI001 = "科研类";
    public final static String GANGWEI002 = "支撑类";
    public final static String GANGWEI003 = "管理类";
    public final static String GANGWEI004 = "成果转移转化类";
    public final static String GANGWEI005 = "领导类";

    public final static String single_address_courseware = "单一网址课件";
    public final static String SCORM_courseware = "SCORM 课件";
    public final static String single_video_document_courseware="单一视频/文档课件";

    public final static String single_video_resource="单一视频资料";
    public final static String single_document_resource="单一文档资料";
    public final static String single_zip_resource="zip/rar压缩包资料";
    public final static String single_address_resource="单一网址资料";

    //  骨干人才上报开始时间、截止时间
    public final static String reportStartTime = "reportStartTime";
    public final static String reportEndTime = "reportEndTime";


    public static Properties elearningProperties;               //获取elearning.properties文件中保存的若干参数
    public static Properties videoProperties;
    public static String train_id=""; //用来同步的train_id
    public final static int  CLASSPATH_XML_APPLICATIONCONTEXT = 1;
    public final static int  FILESYSTEM_XML_APPLICATIONCONTEXT = 2;
    public static String systemName ="";
    public static int tenantId;

    //tenant对应的orgID
    public static int tenantOrgId ;
    public static String tenantName ="";
    public static String systemBottom = "";
    public static String tenantContext = "";

    //培训网站栏目ID号
    public final static Integer Train_News_Column = new Integer(40);//培训新闻栏目ID号
    public final static Integer Train_Object_Column = new Integer(36);//培训对象栏目ID号
    public final static Integer Train_Schedule_Column = new Integer(23);//培训日程栏目ID号
    public final static Integer Train_Teacher_Column = new Integer(6);//培训教师栏目ID号

    //系统皮肤地址和系统自定义的Name&Logo
    public static String SKINDIR="";
    public static String LEARNERSKINDIR="";
    public static Integer DEFAULTPAGEFLAG=0;
    public static String CUSTOMELEARNINGNAME="";
    public static String CUSTOMELEARNINGLOGO="";
    public static Integer CURRENTSKINID=4;

    public static List ICON_RESOURCE_lIST=new ArrayList();
    public static HashMap locationMap=new HashMap();

    //支持的文档资源格式-->针对课程类型单一文档课件
    public final static String SUPPORT_DOCUMENT_TYPE=".swf,.SWF,.pdf,.PDF,.html,.HTML,.doc,.docx,.ppt,.pptx,.txt,.DOC,.DOCX,.PPT,.PPTX,.TXT,.xls,.xlsx,.XLS,.XLSX";

    //支持的思维导图资源格式
    public final static String SUPPORT_MindMap_TYPE=".json,.JSON";

    //elnportal 应用地址
    public static String elnportalAppStr="";

    //试题类型
    public static final String IS_PARENT="1010";
    public static final String IS_SINGLE_ANSWER="1011";//单选题
    public static final String IS_MULTIPLE_CHOICE_ANSWERS="1012";//多选题
    public static final String IS_FILL_IN_THE_BLANK_ANSWERS="1013";//填空题
    public static final String IS_QUESTIONS_AND_ANSWERS="1014";//简答题
    public static final String IS_DISCUSSION_ESSAYS="1015";//论述题
    public static final String IS_PAPER_SIGN="1016";//试卷标识
    public static final String[] wordList={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    //试卷类型
    public static final Integer SPECIFIED_ITEMS = new Integer(0);//指定试题
    public static final Integer UNITE_TESTPAPER = new Integer(1);//统一组卷
    public static final Integer SELFSTUDY_TESTPAPER = new Integer(2); //自测组卷

    //策略类型
    public static final Integer UNITE_STRATEGY = new Integer(1);//统一考试策略
    public static final Integer SELFSTUDY_STRATEGY = new Integer(2); //自测策略

    /**
     * 每个研究所当前是否可以申报年度计划
     * key: tenantId
     * value: true 可以申报年度计划; false 不可以申报年度计划
     */
    public static Map<Integer,Boolean> canCreateYearPlan = new HashMap<Integer,Boolean>();

    //	查询时默认开始时间
    public static final String startTime = "2000-01-01";
    //	查询时默认截止时间
    public static final String endTime = "2050-12-31";

    //**********************重点信息类型划分所属父类型**********************************
    //培训类型
    public static final Integer TRAINTYPECODE=2100;
    //课程category
    public static final Integer COURSECATEGORY=4020;
    //课程Classification
    public static final Integer COURSECLASSIFICATION=4000;

    //课程最后一次学习进度，单位：s；用来实现记忆播放
    public static final String COURSE_LAST_LEARN_TIME = "course_last_learn_second_time";

    //直播系统对接用户，uid基数
    public static final Long LiveUidBaseValue = (long)1000000000;

    //直播状态，0，未开始；1，正在直播；2，暂停；3，结束
    public static final Integer CLASSLIVE_STATUS_NOSTART = 0;
    public static final Integer CLASSLIVE_STATUS_START = 1;
    public static final Integer CLASSLIVE_STATUS_PAUSE = 2;
    public static final Integer CLASSLIVE_STATUS_STOP = 3;

    //标识课件类型：1,普通课件；2是直播生成的课件；3是直播
    public static final Integer COURSE_TYPE_NORMAL = 1;
    public static final Integer COURSE_TYPE_CLASSLIVE_GENERATE = 2;
    public static final Integer COURSE_TYPE_CLASSLIVE = 3;

    //初始密码失效时间，单位：min；默认3天，可以通过数据库timesetting设置
    public static final Integer PWD_VALIDTIME = 3*24*60;

    //服务器端和客户端共同约定的固定字符串key，加密时使用
    public static final String key = "Casmooc2018";

    public static HashMap<String, Resource> resourceMap = new HashMap<String, Resource>();


}
