package com.zensar.om.mns.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zensar.om.mns.entity.ReasonForHelp;

@Repository
public interface ReasonForHelpRepo extends CrudRepository<ReasonForHelp,String> {

}
