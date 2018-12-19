package com.bs.ssh.action.user.comment;

import com.bs.ssh.action.BasePageAction;
import com.bs.ssh.service.user.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractCommentPageAction extends BasePageAction {

    @Autowired protected UserCommentService commentService;

}
