package com.bs.ssh.dao;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseDao<T> {
     <S extends T> S insert(S entity);

    <S extends T> Iterable<S> insert(Iterable<S> entities);

    <S extends T> S update(S entity);

    <S extends T> Iterable<S> update(Iterable<S> entities);

    T findOne(String hql, Object ...values);

    List<T> findAll(Pageable page, String hql, Object ...values);

    void delete(T entity);

    void deleteAll(Iterable<T> entities);

}
