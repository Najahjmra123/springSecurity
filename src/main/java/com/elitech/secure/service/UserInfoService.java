package com.elitech.secure.service;

import java.util.Collection;
import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elitech.secure.entity.UserInfo;
import com.elitech.secure.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService {
	@Autowired
	private UserInfoRepository userInfoRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo>userInfo=userInfoRepository.findByEmail(username);
		return userInfo.map(UserInfoDetails::new)
				.orElse(null);
	}
public UserInfo addUser(UserInfo userInfo) {
	userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
	return userInfoRepository.save(userInfo);}


public List<UserInfo> getAllUsers() {
    return userInfoRepository.findAll();
}

public UserInfo getUser(long id) { 
    return userInfoRepository.findById(id).orElse(null);
}
}
