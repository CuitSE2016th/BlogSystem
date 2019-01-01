package com.bs.ssh.utils;

/**
 *
 * @author 98620
 * @date 2018/11/16
 */
public class Constants {
    public final static int RESPONSE_SUCCEED = 100;

    public final static int RESPONSE_FAILED = 200;

    public final static String IMAGE_PATH = "/images";

    public final static String HOST_PATH = "http://www.lfork.top/blog/images";

    public static String FILE_IMAGE_RELATIVE_PATH = null;

    public static String IMAGE_URL_HEADER = "http://www.lfork.top/blog/images/";

    //审核中
    public final static int AUDIT_IN = 300;

    //待机器审核
    public final static int AUDIT_IN_COMPUTER = 400;

    //待管理员审核
    public final static int AUDIT_IN_ADMIN = 500;

    //审核通过
    public final static int AUDIT_COMPLETE = 600;

    //审核不通过
    public final static int AUDIT_FAILE = 700;

    /**
     * 文章已被删除
     **/
    public final static int DELETED_ARTICLE = 404;

    public final static int ROOT_ROLR_ID = 4;
    public final static int USERADMIN_ROLR_ID = 2;

    public final static int ARTICLEADMIN_ROLR_ID = 3;
}
