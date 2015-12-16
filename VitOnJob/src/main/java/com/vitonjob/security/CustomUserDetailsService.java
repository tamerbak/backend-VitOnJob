/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vitonjob.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vitonjob.dao.IAccountDAO;
import com.vitonjob.entities.Account;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private IAccountDAO accountDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountDAO.findByLogin(username);
		if (account == null) {
			throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
		}
		return new UserRepositoryUserDetails(account);
	}

	public IAccountDAO getAccountDAO() {
		return accountDAO;
	}

	@Autowired
	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO.setClazz(Account.class);
		this.accountDAO = accountDAO;
	}

}
