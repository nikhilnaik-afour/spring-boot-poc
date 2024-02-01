/**
 * 
 */
package com.poc.dellnxppoc.emgmt.service;

import java.util.List;
import com.afour.emgmt.model.UserRegistrationDTO;
import com.poc.dellnxppoc.emgmt.model.UserDTO;

/**
 * 
 */
public interface VisitorService {

	List<UserDTO> fetchAllVisitors();

	UserDTO findVisitorByID(Integer ID);

	UserDTO findVisitorByUserName(String USERNAME);

	UserDTO addVisitor(UserDTO orgDTO) ;

	UserDTO updateVisitor(UserDTO dto);

	boolean deleteVisitorByID(Integer ID);

	UserDTO registerVisitorForEvent(UserRegistrationDTO dto);

}
