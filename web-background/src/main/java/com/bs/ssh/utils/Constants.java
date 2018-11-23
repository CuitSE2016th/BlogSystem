package com.bs.ssh.utils;

/**
 *
 * @author 98620
 * @date 2018/11/16
 */
public class Constants {
    public final static int RESPONSE_SUCCEED = 100;

    public final static int RESPONSE_FAILED = 200;

    //审核中
    public final static int AUDIT_IN = 300;

    //待机器审核
    public final static int AUDIT_IN_COMPUTER = 400;

    //待管理员审核
    public final static int AUDIT_IN_ADMIN = 500;

    //审核通过
    public final static int AUDIT_COMPLETE = 600;
}
