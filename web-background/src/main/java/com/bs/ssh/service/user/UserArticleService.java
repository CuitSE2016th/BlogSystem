package com.bs.ssh.service.user;


import com.bs.ssh.bean.PageBean;
import com.bs.ssh.bean.PageRequest;

/**
 * 文章服务类
 *
 * @author Egan
 * @date 2018/11/23 20:49
 **/
public interface UserArticleService {

    PageBean getAllArticle(PageRequest pageRequest);

    void giveLike(Integer userId, Integer articleId);

    void starArticle(Integer userId, Integer articleId);

}
