package com.ypika.mp.common;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 静态变量文件
 * Created by yangchao on 17/12/27.
 */
public class ConstantConfig {

    //微信授权地址
    public final static String AUTHORIZATION_URL = "https://api.weixin.qq.com/sns/jscode2session";
    //微信获取token地址
    public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    //微信推送模版消息地址
    public final static String PUSH_TEMPLATE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send";
    //公众号&app用户授权路径
    public static final String APP_PUBLIC_OPENDID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    //公众号&app，access_token刷新接口
    public static final String APP_PUBLIC_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    //公众号&app获取用户基本信息路径
    public static final String HULUO_PUBLIC_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";

    /*轻题库*/
    private final static String MASTER_APP_ID = "xxxx";//轻题库微信小程序app_id
    private final static String MASTER_APP_SECRET = "xxx";//轻题库微信小程序密钥
    /*轻题库*/
    private final static String ACCOUNTING_APP_ID = "xxxx";//轻题库微信小程序app_id
    private final static String ACCOUNTING_APP_SECRET = "xxxx";//题库微信小程序密钥
    /*会计*/
    private final static String MANGO_APP_ID = "xxxx";//会计微信小程序app_id
    private final static String MANGO_APP_SECRET = "xxxx";//会计微信小程序密钥
    /*移动客户端*/
    private final static String IOS_ANDROID_APP_ID = "xxxx";//app客户端app_id
    private final static String IOS_ANDROID_APP_SECRET = "xxxx";//app客户端密钥

    //随机生成题目数量
    public static final int RANDOM_QUESTION_SUM = 50;

    //系统首次登录获取金豆数量
    public static final Integer LOGION_FIRST_GOLD_COIN = 30;

    //学习计划每日最多获得的金豆数量--轻题库
    public static final Integer LEARNING_PLAN_DAILY_MAX_COIN_MASTER = 90;

    //学习计划每日最多获得的金豆数量--芒果会计
    public static final Integer LEARNING_PLAN_DAILY_MAX_COIN_MG = 70;

    //完成所有任务所获得的金豆数量
    public static final Integer LEARNING_PLAN_DAILY_TASK_FINISH_MAX_COIN = 50;


    //每日任务，完成增加10个 青豆/金芒果
    public static final Integer LEARNING_PLAN_GOLD_COIN = 10;
    //题目步进数量
    public static final Integer STEP_SUM = 10;
    //题目步进数量
    public static final Integer MAX_LEARNING_PLAN_SUM = 80;
    //题目步进数量
    public static final Integer MIN_LEARNING_PLAN_SUM = 5;
    //题目步进数量
    public static final Long INIT_LEARNING_PLAN_PEOPLE = 2523L;
    //学习计划考前7天
    public static final Long SEVEN_DAY_BEFORE_EXAM = 7L;
    //学习计划每日答题量时间（周）
    public static final Integer LEARNING_PLAN_DAILY_ANSWER_QUESTION_WEEK = 7;
    //学习计划每日答题量时间（月）
    public static final Integer LEARNING_PLAN_DAILY_ANSWER_QUESTION_MONTH = 30;

    //每日公开课可发起助力的次数
    public static final Integer ASSIST_OPEN_CLASS_MAX_CREATE_NUM = 10;
    //每日可助力好友的次数
    public static final Integer ASSIST_FRIENDS_MAX_NUM = 3;
    //助力次数步进数量
    public static final Integer ASSIST_STEP_NUM = 1;

    //管理员角色
    public static final String ADMIN_ROLE_NAME = "ADMIN";
    //普通用户角色
    public static final String USER_ROLE_NAME = "USER";
    /*轻体库微信token，一天只能调用2000次*/
    private static final String MASTER_REDIS_WX_ACCESS_TOKEN = "MASTER:WX:ACCESS_TOKEN:";
    /*会计轻体库微信token，一天只能调用2000次*/
    private static final String ACCOUNTING_REDIS_WX_ACCESS_TOKEN = "ACCOUNTING:WX:ACCESS_TOKEN:";
    /*会计轻体库微信token，一天只能调用2000次*/
    private static final String MANGO_REDIS_WX_ACCESS_TOKEN = "MANGO:WX:ACCESS_TOKEN:";

    //用户没有头像情况下默认头像
    private static final String DEFAULT_MASTER_AVATARURL = "https://r.exam.xxxx.site/s2/image/we_head.png";
    //用户没有头像情况下默认头像
    private static final String DEFAULT_ACCOUNTING_AVATARURL = "https://r.exam.xxxx.site/s2/image/accontant/mongo.jpg";
    /*首页520 活动icon*/
    public static final String HOME_ACTIVITY_ICON = "https://r.exam.xxxx.site/s3/image/icon.gif";
    //世界杯活动icon
    public static final String HOME_WORLD_CUP_ICON = "/image/world_center.gif";

    //pk每日增加金豆场次
    public static final Integer ADD_GOLD_COIN_COUNT = 3;
    //pk每场增加金豆数量
    public static final Integer ADD_GOLD_COIN_COUNT_ONE = 3;
    //pk默认角色昵称
    private static final String PK_DEFAULT_MASTER_NICKNAME = "尚小牛";
    //pk默认角色昵称（会计轻题库，芒果会计）
    private static final String PK_DEFAULT_ACCOUNTING_NICKNAME = "小芒果";
    //pk默认角色段位
    public static final String PK_LEVEL_NUM = "未知";

    //好友对战mark
    public static final String FRIENDS_PLAY = "friendsPlay";
    //实时pk在线用户列表
    public static final String ONLINE_USERS = "PK_ONLINE_USERS:";
    //实时pk分享标识对战信息缓存
    public static final String ONLINE_PK_MARK = "PK_ONLINE_MARK:";
    //实时pk开局对战信息缓存
    public static final String ONLINE_PK_PKLOGID = "PK_ONLINE_PKLOGID:";
    //pk胜利超越排名信息
    public static final String PK_SURPASS = "PK_SURPASS:";
    //pk自我对战mark
    public static final String PK_GOLD_COIN_SHOW = "PK_GOLD_COIN_SHOW:";
    //自我对战mark
    public static final String ONLINE_SELF_PK = "ONLINE_SELF_PK:";
    //redis锁
    public static final String BACKEND_REDIS_LOCK_KEY = "BACKEND_REDIS_LOCK_KEY:";

    //手机号授权开关是否打开
    public static final String AUTH_PHONE_OPEN = "AUTH_PHONE_OPEN:";
    //首页学习计划参与人数
    public static final String HOME_LEARNING_PLAN_PEOPLE = "HOME_LEARNING_PLAN_PEOPLE";
    //白名单人员列表
    public static final String WHITE_OPEN_IDS = "WHITE_OPEN_IDS";

    //抽签活动共有资料
    public static final int MAX_GOODS_SCHEDULE = 7;

    /*token刷新时间*/
    public static final int LOGIN_TOKEN_EXPIRE = 30;
    /*维护自我挑战对局过期时间（十五秒）*/
    public static final Long ONLINE_SELF_PK_TIMEOUT = 15L;
    /*在线用户最长过期时间（三分钟）*/
    public static final Long ONLINE_USERS_TIMEOUT = 180L;
    /*在线用户最长过期时间（三十秒）*/
    public static final Long ONLINE_USERS_READY = 30L;
    /*缓存过期时间（一小时）*/
    public static final Long ONE_HOUR_SECOND = 3600L;
    /*缓存过期时间（一天）*/
    public static final Long ONE_HOUR_DAY = 86400L;

    /*正则表达式*/
    public static final String REGULAR_CAMEL2UNDERLINE = "[A-Z]([a-z\\d]+)?";
    public static final String REGULAR_UNDERLINE2CAMEL = "([A-Za-z\\d]+)(_)?";
    /*线程池参数*/
    public static final Integer CORE_POOL_SIZE = 1;
    public static final String PATTERN = "example-schedule-pool-%d";

    /*
    * 用户数据主键，用户答题主键
    * 触发重置缓存接口：
    *  1，切换科目
    *  2，切换考试类型
    *  3，判题
    *  4，pk
    *  5，pk退出
    *  6，考神抽签
    *  7，收藏
    *  8，每日一练打卡
    *
    *  重置缓存后默认异步缓存：首页通过率,me接口
    */
    public static final String USER_INFO = "USER:INFO:";
    public static final String USER_INFO_ANSWER = "USER:INFO:ANSWER:";

    //章节列表 （章节练习/只做错题/我的收藏/我的错题） （一小时）
    //pk 我的数据 （一小时）
    public static final String USER_PK_ME = "USER_PK_ME";
    //欢迎页 （一小时）
    public static final String USER_HOME = "USER_HOME";
    //全国排行 （一小时）
    public static final String NATIONWIDE = "USER_RANKING_NATIONWIDE";
    //好友排行 （一小时）
    public static final String FRIENDS = "USER_RANKING_FRIENDS";
    //好友排行 （一小时）
    public static final String LEARNING_PLAN = "USER_RANKING_LEARNING_PLAN:";
    //首页用户通过率 （一小时）
    public static final String USER_PASS_RATE = "USER_PASS_RATE";
    //首页热点缓存 （一小时）
    public static final String USER_PUSH = "USER_PUSH_";
    //me接口考试时间字段 （一小时）
    public static final String USER_ME = "USER_ME";
    //判题临时字段 （3秒）
    public static final String USER_ANSWER_TOTAL = "USER_ANSWER_TOTAL";
    public static final String USER_ANSWER_CURRENT = "USER_ANSWER_CURRENT";

    //用户手速
    public static final String ACTIVITY_USER_APM = "ACTIVITY:USER:APM:USERINFO";
    //小星星活动
    public static final String ACTIVITY_USER_APM_PEOPLE_SUM = "ACTIVITY:USER:APM:PEOPLE:SUM";
    //520活动
    public static final String ACTIVITY_USER_VALENTINES_PEOPLE_SUM = "ACTIVITY:USER:VALENTINES:PEOPLE:SUM";
    //七夕节活动人数
    public static final String ACTIVITY_USER_MAGPIE_FESTIVAL_PEOPLE_SUN = "ACTIVITY:USER:MAGPIEFESTIVAL:PEOPLE:SUM";
    //中秋节活动人数
    public static final String ACTIVITY_USER_MOON_FESTIVAL_PEOPLE_SUN = "ACTIVITY:USER:MOONFESTIVAL:PEOPLE:SUM";
    //520活动，清理池子用户锁
    public static final String ACTIVITY_USER_VALENTINES_LOCK = "ACTIVITY_USER_VALENTINES_LOCK";
    //520活动结束，清理池子用户锁
    public static final String ACTIVITY_USER_VALENTINES_LOCK_END = "ACTIVITY_USER_VALENTINES_LOCK_END";
    //背题模式状态缓存
    public static final String USER_RECITE_STATUS = "USER:RECITE:STATUS";
    //随机匹配题目ids缓存
    public static final String PK_EXAM_TYPE_USER_IDS = "PK_EXAM_TYPE_USER_IDS";

    //全國最短做题时长
    public static final String MINDURATION = "MINDURATION";

    //提醒好友
    public static final String ACTIVITY_WORLD_CUP_REMIND = "ACTIVITY_WORLD_CUP_REMIND";

    //世界杯活动
    public static final String ACTIVITY_USER_WORLDCUP_PEOPLE_SUM = "ACTIVITY:USER:WORLDCUP:PEOPLE:SUM";
    //世界杯活动id
    public static final Long WORLD_CUP_ACTIVITY_ID = 5L;
    //世界杯换队友限制
    public static final Long WORLD_CUP_CHANGE_TEAMMATES_LIMIT = 48 * 60 * 60L;
    //世界杯提醒队友限制
    public static final Long WORLD_CUP_URGE_TEAMMATES_LIMIT_SECONDS = 12 * 60 * 60L;
    //public static Long WORLD_CUP_URGE_TEAMMATES_LIMIT_SECONDS =   2* 60L;
    //世界杯显示倒计时时间
    public static final Long WORLD_CUP_COUNTDOWN_LIMIT_SECONDS = 3 * 24 * 60 * 60L;
    //世界杯排行榜显示数量
    public static final Long WORLD_CUP_LEADER_BOARDS_NUM = 40L;
    //签到
    public static final String SIGN_IN_DAY_Key = "SIGN_IN_DAY_Key";
    //签到总数
    public static final String SIGN_IN_PEOPLE_SUM = "signInTotalKey";
    //签到机器人
    public static final String PUSH_MESSAGE_ROBOT_USER = "PUSH_MESSAGE_ROBOT_USER";
    //用户昨日每日计划完成情况
    public static final String LEARNING_PALN_USER_YTD_DAILY_FINISH = "LEARNINGPLAN:USER:YTD:DAILYPLAN:FINISH";
    //助力MD5
    public static final String ASSIST_MD5_PREFIX = "ASSIST_MD5_PREFIX_";
    //助力活动成功列表
    public static final String ASSIST_LIST_SUCCESS = "ASSIST_LIST_SUCCESS";
    //助力对象锁
    public static final String ASSIST_LOCK_USER_OBJECT = "ASSIST:LOCK:USER:OBJECT";
    //学习计划弹幕用户
    public static final String LEARNING_PLAN_BARRAGE_USER = "LEARNINGPLAN:BARRAGE:USER";
    //学习计划弹幕用户
    public static final String LEARNING_PLAN_RANKING_HEADIMAGEURL = "LEARNING_PLAN_RANKING_HEADIMAGEURL:";
    public static final Long LEARNING_PLAN_RANKING_TIME = 60L;

    //七夕/中秋活动类型标准
    public static final Set<Integer> DIFFICULTY_SET = IntStream.range(1, 17)
            .boxed().collect(Collectors.toSet());

    public static final Set<Integer> SIMPLE_SET = IntStream.range(1, 5)
            .boxed().collect(Collectors.toSet());

    public static final Set<Integer> MEDIUM_SET = IntStream.range(1, 10)
            .boxed().collect(Collectors.toSet());

    public static final String DAILYPRACTICE_TASK = "DAILYPRACTICE:TASK:";

    /**
     * 答题计划需要弹窗KEY
     *
     * @param userId 用户id
     * @return 弹窗KEY
     */
    public static String LEARNING_PLAN_POP_UP(Long userId) {
        return "LEARNING:PLAN:POP:UP".concat(":").concat(userId.toString());
    }

    public static final Number ONE = 1;

    public static final Number TEN = 10;

    public static final Number FIVE = 5;

    public static final Number THREE = 3;

    public static final Number ELEVEN = 11;

    public static final String PASS_RATE_SUBJECT = "PASS:RATE:SUBJECT";

    public static final String SUBJECT_CUT_PEOPLE_SUM ="SUBJECT:CUT:PEOPLE:SUM";

    /**
     * app绑定手机号验证白名单
     */
    public static final String[] BINDING_PHONE_NUM_WHITE_LIST = {"18612135527", "0000"};

    /**
     * 芒果会计学习计划考试时间（暂定）
     */
    public static final String EXAM_TIME_MANGO_ACCOUNTING_DATE = "2019-05-01 09:00:00";

    /**
     * 学习计划-成绩预约人数 redis key
     */
    public static final String LEARNING_PLAN_SCORE_RESERVATION_PEOPLE = "LEARNINGPLAN:SCORERESERVATION:PEOPLE";

    /**
     * 学习计划-成绩预约基础人数
     */
    public static final Long LEARNING_PLAN_SCORE_RESERVATION_PEOPLE_INIT = 307L;


}
