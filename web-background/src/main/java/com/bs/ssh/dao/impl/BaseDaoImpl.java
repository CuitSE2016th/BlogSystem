package com.bs.ssh.dao.impl;

import com.bs.ssh.bean.PageRequest;
import com.bs.ssh.dao.BaseDao;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository("base")
public class BaseDaoImpl<T> implements BaseDao<T> {

    private HibernateTemplate template;


    @Autowired
    public BaseDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    public HibernateTemplate getTemplate() {
        return template;
    }

    @Override
    public <S extends T> S insert(S entity) {
        template.save(entity);
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> insert(Iterable<S> entities) {
        template.save(entities);
        return entities;
    }

    @Override
    public <S extends T> S update(S entity) {
        template.update(entity);
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> update(Iterable<S> entities) {
        template.update(entities);
        return entities;
    }

    @Override
    public T findOne(String hql, Object ...values) {

        List<?> entities =  template.find(hql, values);
        return entities.size()>0? (T) entities.get(0) :null;
    }


    @Override
    public List<T> findAll(PageRequest page, String hql, Object ...values) {
        return template.execute(
                session -> {
                    Query query =  session.createQuery(hql)
                            .setFirstResult(
                                    (page.getPageNumber()-1) * page.getPageSize())
                            .setMaxResults(page.getPageSize());
                    for (int i=0; i<values.length; i++)
                        query.setParameter(i, values[i]);
                    return query.list();
                });
    }

    @Override
    public Integer count(String entityName) {
        return template.execute(session ->
                ((Long) session.createQuery("select count(*) from " + entityName)
                        .uniqueResult()).intValue());
    }

    @Override
    public void delete(T entity) {
        template.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<T> entities) {
        entities.forEach(e->template.delete(e));
    }


}
