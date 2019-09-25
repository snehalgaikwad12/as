package com.zensar.om.mns.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.zensar.om.mns.entity.SmeName;

@Repository
public interface SmeNameRepo extends CrudRepository<SmeName,String>{

}
