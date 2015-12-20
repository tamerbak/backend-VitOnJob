package com.vitonjob.dao;

import com.vitonjob.dto.EmployeurDTO;
import com.vitonjob.entities.Account;

public interface IAccountDAO extends IGenericDao<Account> {

	/**
	 * r�cup�re le compte par email.
	 * 
	 * @return le compte associ� � l'email.
	 */
	Account findByLogin(String email);

	EmployeurDTO findEmployeurByEmailAndPassword(String email, String password);

	EmployeurDTO findEmployeurByTelephoneAndPassword(String telephone, String password);

	Long countUsersWithEmail(String email);

	Long countUsersWithTelephone(String telephone);
}
