package com.bs.ssh.entity;

import java.io.Serializable;

/**
 * 两个用户id组成的联合主键
 *
 * @author Egan
 * @date 2018/11/26 21:19
 **/
public class FollowKey implements Serializable {
    private String followerId;
    private String followingId;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FollowKey){
            FollowKey key = (FollowKey) obj;
            return followerId.equals(key.followerId) && followingId.equals(key.followingId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return followerId.hashCode();
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    public String getFollowingId() {
        return followingId;
    }

    public void setFollowingId(String followingId) {
        this.followingId = followingId;
    }
}
