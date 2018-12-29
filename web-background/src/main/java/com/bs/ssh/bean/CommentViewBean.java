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

    protected Integer id;
    protected String username;
    protected String portrait;
    protected String content;
    protected Long createTime;
    private List<Child> children;

    public CommentViewBean() {
    }

    public CommentViewBean(User user, Comment comment){
        this.id = comment.getId();
        this.username = user.getNickname();
        this.portrait = user.getHeadPortrait();
        this.content = comment.getContent();
        this.createTime = comment.getCreateTime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public static class Child extends CommentViewBean{

        public Child(User user, Comment comment) {
            super(user, comment);
        }
    }
}
