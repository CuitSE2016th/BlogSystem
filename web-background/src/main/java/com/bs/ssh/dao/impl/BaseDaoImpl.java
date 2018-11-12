package com.bs.ssh.dao.impl;

import com.bs.ssh.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {

    private HibernateTemplate template;

    @Autowired
    public BaseDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public <S extends T> S save(S entity) {
        template.save(entity);
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        template.save(entities);
        return entities;
    }

    @Override
    public T findOne(String hql, Object ...values) {
        List<?> entities =  template.find(hql, values);
        return entities.size()>0? (T) entities.get(0) :null;
    }


    @Override
    public Iterable<T> findAll(String hql, Object ...values) {
        List<T> entities = (List<T>) template.find(hql,values);
        return entities.size()>0? entities :null;
    }

    @Override
    public void delete(T entity) {
        template.delete(entity);
    }

    @Override
    public void deleteAll(Iterator<T> entities) {
        template.delete(entities);
    }


}
