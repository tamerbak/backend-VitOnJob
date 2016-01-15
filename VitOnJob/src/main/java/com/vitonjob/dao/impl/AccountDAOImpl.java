package com.vitonjob.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vitonjob.dao.IAccountDAO;
import com.vitonjob.dto.EmployeurDTO;
import com.vitonjob.dto.JobyerDTO;
import com.vitonjob.entities.Account;

@Repository("accountDAO")
public class AccountDAOImpl extends GenericDAOImpl<Account>implements IAccountDAO {

	@Override
	public Account findByLogin(String email) {
		Query query = getCurrentSession().createQuery("FROM Account acct WHERE acct.email = :email");
		query.setParameter("email", email);
		return (Account) query.uniqueResult();
	}

	@Override
	public EmployeurDTO findEmployeurByEmailAndPassword(String email, String password) {
		Query query = getCurrentSession().createQuery(
				"SELECT new com.vitonjob.dto.EmployeurDTO(emp.id, acct.email) FROM Entreprise entr INNER JOIN entr.account acct INNER JOIN entr.employeur emp WHERE acct.email = :email AND acct.motDePasse = :motDePasse");
		query.setParameter("email", email);
		query.setParameter("motDePasse", password);
		return (EmployeurDTO) query.uniqueResult();
	}

	@Override
	public EmployeurDTO findEmployeurByTelephoneAndPassword(String telephone, String password) {
		Query query = getCurrentSession().createQuery(
				"SELECT new com.vitonjob.dto.EmployeurDTO(emp.id, acct.email) FROM Entreprise entr INNER JOIN entr.account acct INNER JOIN entr.employeur emp WHERE acct.telephone = :telephone AND acct.motDePasse = :motDePasse");
		query.setParameter("telephone", telephone);
		query.setParameter("motDePasse", password);
		return (EmployeurDTO) query.uniqueResult();
	}

	@Override
	public JobyerDTO findJobyerByEmailAndPassword(String email, String password) {
		Query query = getCurrentSession().createQuery(
				"SELECT new com.vitonjob.dto.JobyerDTO(job.id, acct.email) FROM Jobyer job INNER JOIN job.account acct WHERE acct.email = :email AND acct.motDePasse = :motDePasse");
		query.setParameter("email", email);
		query.setParameter("motDePasse", password);
		return (JobyerDTO) query.uniqueResult();
	}

	@Override
	public JobyerDTO findJobyerByTelephoneAndPassword(String telephone, String password) {
		Query query = getCurrentSession().createQuery(
				"SELECT new com.vitonjob.dto.JobyerDTO(job.id, acct.email) FROM Jobyer entr INNER JOIN job.account acct WHERE acct.telephone = :telephone AND acct.motDePasse = :motDePasse");
		query.setParameter("telephone", telephone);
		query.setParameter("motDePasse", password);
		return (JobyerDTO) query.uniqueResult();
	}
	
	@Override
	public Long countUsersWithEmail(String email) {
		Query query = getCurrentSession().createQuery("SELECT count(1) FROM Account acct WHERE acct.email = :email");
		query.setParameter("email", email);
		return (Long) query.uniqueResult();
	}

	@Override
	public Long countUsersWithTelephone(String telephone) {
		Query query = getCurrentSession()
				.createQuery("SELECT count(1) FROM Account acct WHERE acct.telephone = :telephone");
		query.setParameter("telephone", telephone);
		return (Long) query.uniqueResult();
	}

}
