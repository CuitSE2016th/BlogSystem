package com.bs.ssh.service;

import com.bs.ssh.beans.Permission;

import java.util.List;

/**
 * Shiro服务
 * 提供权限控制和角色管理服务
 *
 * @author Egan
 * @date 2018/11/10 20:10
 **/
public interface ShiroService {

    /**
     * 根据角色ID查询权限
     *
     * @date 2018/11/10 20:11
     * @param roleID
     * @return java.util.List<com.bs.ssh.beans.Permission>
     **/
    List<Permission> queryPermissionsByRoleID(String roleID);

}
