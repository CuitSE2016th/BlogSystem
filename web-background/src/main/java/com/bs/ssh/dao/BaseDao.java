package com.bs.ssh.dao;

import java.io.Serializable;
import java.util.Iterator;

public interface BaseDao<T> {
    abstract <S extends T> S save(S entity);

    <S extends T> Iterable<S> save(Iterable<S> entities);

    T findOne(String hql, Object ...values);

    Iterable<T> findAll(String hql, Object ...values);

    void delete(T entity);

    void deleteAll(Iterator<T> entities);

}
