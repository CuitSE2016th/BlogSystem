package com.bs.ssh.action.user.article;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.service.user.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractArticleAction extends BaseAction {
    @Autowired protected UserArticleService articleService;


}
