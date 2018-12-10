package com.bs.ssh.action.user.comment;

import com.bs.ssh.action.BaseAction;
import com.bs.ssh.service.user.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractCommentAction extends BaseAction {
    protected int aid;
    @Autowired protected UserCommentService commentService;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }
}
