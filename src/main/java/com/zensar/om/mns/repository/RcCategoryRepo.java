package com.zensar.om.mns.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zensar.om.mns.entity.RcCategory;

@Repository
public interface RcCategoryRepo extends CrudRepository<RcCategory, String>  {

}
