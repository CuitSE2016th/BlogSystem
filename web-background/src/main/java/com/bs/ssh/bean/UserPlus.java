package com.bs.ssh.bean;

import com.bs.ssh.entity.Role;
import com.bs.ssh.entity.User;

/**
 * Create By ZZY on 2018/12/6
 */
public class UserPlus extends User{

    private Role role;


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserPlus{" +
                "role=" + role +
                '}';
    }

    public UserPlus(String id, String nickname, String headPortrait, Long birthday, String sex, String email, String phone, String password, String salt, Integer roleId, Long lastLoginTime, Long createTime) {
        super(id, nickname, headPortrait, birthday, sex, email, phone, password, salt, roleId, lastLoginTime, createTime);
    }
}
