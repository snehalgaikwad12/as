package com.zensar.om.mns.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.om.mns.entity.UserLogin;
import com.zensar.om.mns.exception.GlobalException;
import com.zensar.om.mns.service.UserLoginService;

@CrossOrigin(origins="*")
@RestController
public class UserLoginApi {
	@Autowired
	UserLoginService userLoginService;
	Optional<UserLogin> userLoginDetail;
	UserLogin loginDeatil=new UserLogin();
	@GetMapping(value="/api/UserLoginDetails")
	public Optional<UserLogin> getUserLoginDetail()
	{
		userLoginDetail=userLoginService.getUserLoginDetail("abc");
		return userLoginDetail;
	}
	
	@PostMapping(value="/api/UserLogin")
	public UserLogin userLoginDetail(@RequestBody UserLogin userLogin)
	{
		
		System.out.println("user name "+userLogin.getUserName());
		System.out.println("user name "+userLogin.getUserPassword());
		userLoginDetail=userLoginService.getUserLoginDetail(userLogin.getUserName());
		if(userLoginDetail.isPresent()) {
		if(userLoginDetail.get().getUserName().equals(userLogin.getUserName()) && userLoginDetail.get().getUserPassword().equals(userLogin.getUserPassword()))
		{
			return new UserLogin(userLogin.getUserName(),userLoginDetail.get().getUserRole(),userLoginDetail.get().getProjectName());
		}
		}
		throw new GlobalException("Not An Authorized User !! ");
		
	}
}



