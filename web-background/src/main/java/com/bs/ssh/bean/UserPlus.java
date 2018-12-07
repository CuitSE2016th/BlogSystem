package com.bs.ssh.bean;

import com.bs.ssh.entity.Role;
import com.bs.ssh.entity.User;

/**
 * Create By ZZY on 2018/12/6
 */
public class UserPlus {

    private User user;
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserPlus{" +
                "user=" + user +
                ", role=" + role +
                '}';
    }
}
