package com.pc1crt.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pc1crt.application.model.User;
import com.pc1crt.application.repositories.UserRepository;
@Component("userPrincipalDetailsService")
public class UserPrincipalDetailsService implements UserDetailsService {
	
	private UserRepository repo;

	public UserPrincipalDetailsService(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUserName(username);
		UserPrincipal userPrincipal = new UserPrincipal(user);
		return userPrincipal;
	}

}
