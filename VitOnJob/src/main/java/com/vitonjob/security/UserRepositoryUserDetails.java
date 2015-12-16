package com.vitonjob.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vitonjob.entities.Account;

public class UserRepositoryUserDetails extends Account implements UserDetails {

	private static final long serialVersionUID = 1L;

	public UserRepositoryUserDetails(Account account) {
		super(account);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> listRoles = new ArrayList<>();
		listRoles.add(new Role(getRole()));
		return listRoles;
	}

	@Override
	public String getPassword() {
		return getMotDePasse();
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
