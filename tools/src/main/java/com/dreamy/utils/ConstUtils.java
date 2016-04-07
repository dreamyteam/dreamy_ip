package com.dreamy.utils;

/**
 * Created by wangyongxing on 16/4/1.
 */
public class ConstUtils {
    public static final int CRAWLER_STATUS_PLACEHOLDER = 41;

    public static final int DAY_OF_SECOND = 24 * 60 * 60;

    /**
     * @author jared
     * @Description: 队列相关常量配置
     * @date Nov 5, 2014 2:31:35 PM
     */
    public static final class Queue {

        public static final String NEW_CONTENTS = "new_contents";
        public static final String FEED_QUERY = "content_feeds";
        public static final String DOWNLOAD_UPDATE_QUERY = "download_update";
        public static final String FOLLOW_UPDATE_QUERY = "follow_update";
        public static final String MESSAGE_PUSH_QUERY = "message_push";
        public static final String MESSAGE_PUSH_SLOW_QUERY = "message_push_slow";
        public static final String EMAIL_SEND_QUERY = "email_send";
        public static final String PODCASTER_REPLACE_QUEUE = "podcaster_replace";

        /**
         * @author jared
         * @Description: 监听队列常量名
         * @date Nov 5, 2014 2:32:57 PM
         */
        public static final class Handler {
            public static final String NEW_CONTENTS = "new_contents";
            public static final String CONTENT_FEEDS = "content_feeds";
            public static final String DOWNLOAD_UPDATE = "download_update";
            public static final String FOLLOW_UPDATE = "follow_update";
            public static final String MESSAGE_PUSH = "message_push";
            public static final String EMAIL_SEND = "email_send";
            public static final String PODCASTER_REPLACE = "podcaster_replace";
        }
    }

    /**
     * @author jared
     * @Description: redis相关常量
     * @date Nov 5, 2014 2:31:02 PM
     */
    public static final class RedisCache {
        public static final class CachePrefix {
            public static final String HAS_NEW_CONTENTS = "user_has_new_tracks_";
            public static final String RANDOM_LISTEN = "wechat_random_listem_";
            public static final String SUB_UPDATE_PUSH_USER = "sub_update_push_user_";
            public static final String FOLLOW_UPDATE_PUSH_USER = "follow_update_push_user_";
            public static final String NOTIFY_PUSH_TASK_KEY = "notify_push_task_";
            public static final String NOTIFY_PUSH_SUCCESS_TASK_KEY = "notify_push_success_task_";
            public static final String NOTIFY_PUSH_TASK_MESSAGE_KEY = "notify_push_task_message_";
            public static final String AD_CONGFI_KEY = "ad_config_";
        }

        public static final String USER_V_KEY = "user_v_key";
    }

    /**
     * @author jared
     * @Description: 服务器相关参数配置
     * @date Nov 5, 2014 2:30:25 PM
     */
    public final class Server {
        public static final String CHARSET = "UTF-8";
    }

    /**
     * @author jared
     * @Description: session中相关常量
     * @date Nov 7, 2014 4:29:57 PM
     */
    public final class Session {
        public static final int USERSESSION_TIMEOUT = 2700;
    }

    public final class Interface {
        public static final int ERROR_CODE_SYSTEM = -1;
        public static final int ERROR_CODE_SUCCESS = 0;
        public static final int ERROE_CODE_TOKEN_ILLEGAL = 1;
        public static final int ERROR_CODE_UNLOGIN = 2;
        public static final int ERROR_CODE_FIELD_NULL = 3;
        public static final int ERROR_CODE_DATASOURCE_EXCEPTIONAL = 4;
        public static final int ERROR_CODE_DATA_INVALID = 5;
        public static final int ERROR_CODE_FILE_UPLOAD_FAILURE = 7;

    }

    public final class Sso {
        public static final String API_LOGIN_TOKEN = "CgjgqswD8FE0vbyoaLYvgwhC80t9HHoc";
        public static final String COOKIE_SESSIONID = "DUOTIN_SSO_ADMIN_SESSIONID";
        public static final String HOST = "http://japi.duotin.com";
        public static final String HOST_DEV = "http://japi.danxinben.com";
        public static final String GET_LOGIN_USER_INFO = "/api/sso/admin/login";
    }

    public final class Push {
        public static final int APP_ID_DUOTINFM = 11;
        public static final int APP_ID_DUOTIN_V = 897;
        public static final String DEVICE_TYPE_IOS = "ios";
        public static final String DEVICE_TYPE_ANDIORD = "android";
    }

    public final class Feed {
        public static final int TYPE_NEW_CONTENTS = 2;
    }

    public final class Auth {
        public static final String ROOT = "root";
        public static final String STATIS = "statis";
        public static final String WECHAT = "wechat";
        public static final String NOTIFY = "notify";
        public static final String AD = "ad";
    }

    public final class Version {
        public static final int VERSION_CODE_IOS_REPLY = 361;
    }

    public final class Pages {

        public static final String PLAIN = "plain";
    }


}
