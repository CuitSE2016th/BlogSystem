package com.bs.ssh.dao;


import com.bs.ssh.bean.PageRequest;

import java.util.List;

public interface BaseDao<T> {
     <S extends T> S insert(S entity);

    <S extends T> Iterable<S> insert(Iterable<S> entities);

    <S extends T> S update(S entity);

    <S extends T> Iterable<S> update(Iterable<S> entities);

    T findOne(String hql, Object ...values);

    List<T> findAll(PageRequest page, String hql, Object ...values);

    List<T> findAll(String hql, Object ...values);

    Integer count(String entityName);

    void delete(T entity);

    void deleteAll(List<T> entities);

}
