package com.bs.ssh.action.user.comment;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.service.user.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractCommentAction extends BaseAction {

    @Autowired protected UserCommentService commentService;


}
