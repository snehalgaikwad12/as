package com.zensar.om.mns.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zensar.om.mns.entity.FunctionalArea;

@Repository
public interface FunctionalAreaRepo extends CrudRepository<FunctionalArea,String>{

}
