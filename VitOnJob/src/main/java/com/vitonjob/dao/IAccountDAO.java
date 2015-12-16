package com.vitonjob.dao;

import com.vitonjob.entities.Account;

public interface IAccountDAO extends IGenericDao<Account> {

	/**
	 * r�cup�re le compte par email.
	 * 
	 * @return le compte associ� � l'email.
	 */
	Account findByLogin(String email);

}
