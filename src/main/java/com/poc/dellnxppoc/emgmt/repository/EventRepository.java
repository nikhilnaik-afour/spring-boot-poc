/**
 * 
 */
package com.poc.dellnxppoc.emgmt.repository;

import com.poc.dellnxppoc.emgmt.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
	
	@Query(value = "SELECT e from Event e WHERE e.isClosed= :status")
	List<Event> fetchEventsByStatus(@Param("status") boolean status);

}
