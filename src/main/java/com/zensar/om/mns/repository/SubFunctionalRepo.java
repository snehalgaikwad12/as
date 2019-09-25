package com.zensar.om.mns.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zensar.om.mns.entity.Classification;
import com.zensar.om.mns.entity.SubFunction;

@Repository
public interface SubFunctionalRepo extends CrudRepository<SubFunction,String>{

}
