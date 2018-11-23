package com.bs.ssh.dao.impl;

import com.bs.ssh.dao.BaseDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository("base")
public class BaseDaoImpl<T> implements BaseDao<T> {

    private HibernateTemplate template;

//    public String entityName;

    @Autowired
    public BaseDaoImpl(HibernateTemplate template) {
        this.template = template;
//        Class c = this.getClass();
//        Type t = c.getGenericSuperclass();
//        if (t instanceof ParameterizedType) {
////          System.out.println("in if");
//            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
////          System.out.println(Arrays.toString(p));
//            String type = p[0].getTypeName();
//            String[] names = p[0].getTypeName().split("\\.");
//            this.entityName = names[names.length-1];
//        }
    }


    private void initEntityName(){

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
    public List<T> findAll(Pageable pageable, String hql, Object ...values) {
        List entities = template.execute(
                session -> {
                    Query query =  session.createQuery(hql)
                            .setFirstResult(pageable.getOffset())
                            .setMaxResults(pageable.getPageSize());
                    for (int i=0; i<values.length; i++)
                        query.setParameter(i, values[i]);
                    return query.list();
                });
        return entities.size()>0? entities :null;
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
