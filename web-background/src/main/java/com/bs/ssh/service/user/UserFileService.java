package com.bs.ssh.service.user;

import java.io.File;
import java.io.IOException;

/**
 * 文件相关服务
 *
 * @author Egan
 * @date 2018/12/18 23:08
 **/
public interface UserFileService {

    /**
     * 上传图片
     *
     * @date 2018/12/18 23:11
     * @param uid 用户ID
	 * @param files 图片
	 * @param filenames 图片文件名
     * @return java.lang.String[] 图片保存路径
     **/
    String[] uploadPicture(String uid, File[] files, String[] filenames) throws IOException;

    /**
     * 引用图片
     * 设置图片的所属文章，使其进入被引用状态
     *
     * @date 2018/12/18 23:59
     * @param uid
	 * @param iid
	 * @param aid
     * @return void
     **/
    void referencePicture(String uid, String[] iid, Integer aid);
}
