package com.bs.ssh.action.user.article;

import com.bs.ssh.action.BasePageAction;
import com.bs.ssh.service.user.UserArticleService;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractArticlePageAction extends BasePageAction {
    @Autowired protected UserArticleService articleService;
}
