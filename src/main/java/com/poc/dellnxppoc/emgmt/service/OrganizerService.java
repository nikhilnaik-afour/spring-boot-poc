/**
 * 
 */
package com.poc.dellnxppoc.emgmt.service;

import com.poc.dellnxppoc.emgmt.model.UserDTO;

import java.util.List;


/**
 * 
 */
public interface OrganizerService {
	
	List<UserDTO> fetchAllOrganizers();

	UserDTO addOrganizer(UserDTO orgDTO) ;

	UserDTO findOrganizerByID(final Integer ID);

	UserDTO findOrganizerByUserName(final String USERNAME);

	UserDTO updateOrganizer(UserDTO orgDTO);

	boolean deleteOrganizerByID(Integer iD);

}
