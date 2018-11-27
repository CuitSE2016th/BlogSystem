package com.bs.ssh.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户关注实体
 *
 * @author Egan
 * @date 2018/11/26 8:41
 **/
@Entity
@Table(name = "follow")
//@IdClass(FollowKey.class)
public class Follow implements Serializable{
    /**
     * 被关注者
     **/
    private String followerId;
    /**
     * 关注者
     **/
    private String followingId;
    private Long createTime;

    @Id
    @Column(name = "follower_id")
    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    @Id
    @Column(name = "following_id")
    public String getFollowingId() {
        return followingId;
    }

    public void setFollowingId(String followingId) {
        this.followingId = followingId;
    }

    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public int hashCode() {
        return followerId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Follow){
            Follow key = (Follow) obj;
            return followerId.equals(key.followerId) && followingId.equals(key.followingId);
        }
        return false;
    }
}
