package com.zensar.om.mns.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zensar.om.mns.entity.Classification;

@Repository
public interface TicketRepository extends CrudRepository<Classification,String> {

	
}
