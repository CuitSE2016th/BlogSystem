package com.bs.ssh.bean;

import com.bs.ssh.entity.Comment;
import com.bs.ssh.entity.User;

import java.util.List;

/**
 * 为前端视图准备的评论Bean
 *
 * @author Egan
 * @date 2018/12/27 22:08
 **/
public class CommentViewBean {

    private Integer id;
    private Integer pid;
    private String uid;
    private String username;
    private String portrait;
    private String content;
    private Long createTime;
    private List<CommentViewBean> children;

    public CommentViewBean() {
    }

    public CommentViewBean(User user, Comment comment){
        this.id = comment.getId();
        this.pid = comment.getParentId();
        this.uid = user.getId();
        this.username = user.getNickname();
        this.portrait = user.getHeadPortrait();
        this.content = comment.getContent();
        this.createTime = comment.getCreateTime();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public List<CommentViewBean> getChildren() {
        return children;
    }

    public void setChildren(List<CommentViewBean> children) {
        this.children = children;
    }
}
