package com.bs.ssh.dao.impl;

import com.bs.ssh.beans.Token;
import com.bs.ssh.dao.TokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDaoImpl extends BaseDaoImpl<Token> implements TokenDao{
    @Autowired
    public TokenDaoImpl(HibernateTemplate template) {
        super(template);
    }
}
