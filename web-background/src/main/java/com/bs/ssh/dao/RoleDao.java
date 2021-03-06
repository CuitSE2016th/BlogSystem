package com.bs.ssh.dao;

import com.bs.ssh.entity.Role;

import java.util.Set;

/**
 * 角色仓库
 *
 * @author Egan
 * @date 2018/11/11 19:46
 **/
public interface RoleDao extends BaseDao<Role> {

    Set<String> findRoleName(Integer roleId);
}
