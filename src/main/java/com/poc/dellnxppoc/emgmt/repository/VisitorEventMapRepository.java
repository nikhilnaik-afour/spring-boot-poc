/**
 * 
 */
package com.poc.dellnxppoc.emgmt.repository;

import com.poc.dellnxppoc.emgmt.entity.UserEventMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 
 */
@Repository
public interface VisitorEventMapRepository extends JpaRepository<UserEventMap, Integer>{

}
