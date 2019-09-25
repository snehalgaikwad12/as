package com.zensar.om.mns.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zensar.om.mns.entity.UserLogin;
import com.zensar.om.mns.repository.UserLoginRepo;

@Service
public class UserLoginImpl implements UserLoginService{

	@Autowired
	UserLoginRepo loginRepo;
	
	Optional<UserLogin> userInfo;
	
	@Override
	public Optional<UserLogin> getUserLoginDetail(String id) {
		
		userInfo=loginRepo.findById(id);
		System.out.println("UserDetail ... "+userInfo);
		return userInfo;
	}

}
