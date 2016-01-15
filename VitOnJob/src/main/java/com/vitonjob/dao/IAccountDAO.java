package com.vitonjob.dao;

import com.vitonjob.dto.EmployeurDTO;
import com.vitonjob.dto.JobyerDTO;
import com.vitonjob.entities.Account;

public interface IAccountDAO extends IGenericDao<Account> {

	/**
	 * récupére le compte par email.
	 * 
	 * @return le compte associé à l'email.
	 */
	Account findByLogin(String email);

	EmployeurDTO findEmployeurByEmailAndPassword(String email, String password);

	EmployeurDTO findEmployeurByTelephoneAndPassword(String telephone, String password);
	
	JobyerDTO findJobyerByEmailAndPassword(String email, String password);

	JobyerDTO findJobyerByTelephoneAndPassword(String telephone, String password);

	Long countUsersWithEmail(String email);

	Long countUsersWithTelephone(String telephone);
}
