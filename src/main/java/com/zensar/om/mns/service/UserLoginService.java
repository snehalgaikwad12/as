package com.zensar.om.mns.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zensar.om.mns.entity.UserLogin;

@Service
public interface UserLoginService {
	
	public Optional<UserLogin> getUserLoginDetail(String id);

}