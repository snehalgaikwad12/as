package com.zensar.om.mns.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.zensar.om.mns.entity.UserLogin;

@Repository
public interface UserLoginRepo extends  CrudRepository<UserLogin,String>{
 

}
