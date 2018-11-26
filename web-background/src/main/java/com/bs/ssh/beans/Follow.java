package com.bs.ssh.beans;

import javax.persistence.*;

/**
 * 用户关注实体
 *
 * @author Egan
 * @date 2018/11/26 8:41
 **/
@Entity
@Table(name = "follow")
public class Follow {
    private Integer id;
    /**
     * 被关注者
     **/
    private Integer follwerId;
    /**
     * 关注者
     **/
    private Integer followingId;
    private Long createTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "follower_id")
    public Integer getFollwerId() {
        return follwerId;
    }

    public void setFollwerId(Integer follwerId) {
        this.follwerId = follwerId;
    }

    @Column(name = "following_id")
    public Integer getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Integer followingId) {
        this.followingId = followingId;
    }

    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
