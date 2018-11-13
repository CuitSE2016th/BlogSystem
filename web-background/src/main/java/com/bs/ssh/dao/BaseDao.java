package com.bs.ssh.dao;

import java.util.Iterator;

public interface BaseDao<T> {
     <S extends T> S insert(S entity);

    <S extends T> Iterable<S> insert(Iterable<S> entities);

    <S extends T> S update(S entity);

    <S extends T> Iterable<S> update(Iterable<S> entities);

    T findOne(String hql, Object ...values);

    Iterable<T> findAll(String hql, Object ...values);

    void delete(T entity);

    void deleteAll(Iterator<T> entities);

}
