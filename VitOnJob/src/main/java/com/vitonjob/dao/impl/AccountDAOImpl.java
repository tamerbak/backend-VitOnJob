package com.vitonjob.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IAccountDAO;
import com.vitonjob.entities.Account;

@Repository("accountDAO")
public class AccountDAOImpl extends GenericDAOImpl<Account>implements IAccountDAO {

	@Override
	public Account findByLogin(String email) {
		Query query = getCurrentSession().createQuery("FROM Account acct WHERE acct.email = :email");
		query.setParameter("email", email);
		return (Account) query.uniqueResult();
	}

}
