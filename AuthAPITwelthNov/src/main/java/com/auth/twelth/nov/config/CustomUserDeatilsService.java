package com.auth.twelth.nov.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.twelth.nov.entity.User;
import com.auth.twelth.nov.entity.repository.UserRepository;

@Service
public class CustomUserDeatilsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found with email: "+username));
		return new CustomUserDetails(user);
	}

}
