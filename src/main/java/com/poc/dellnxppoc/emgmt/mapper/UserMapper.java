/**
 * 
 */
package com.poc.dellnxppoc.emgmt.mapper;


import com.poc.dellnxppoc.emgmt.entity.User;
import com.poc.dellnxppoc.emgmt.model.UserDTO;

import java.util.List;
import java.util.Set;

/**
 * 
 */
public interface UserMapper {
	
	UserDTO entityToDTO(User entity);
	
	User DTOToEntity(UserDTO dto);
	
	List<UserDTO> entityToDTO(List<User> entities);
	
	List<User> DTOToEntity(List<UserDTO> dtos);

	User prepareForUpdate(User entity, UserDTO orgDTO);

	User prepareForCreate(UserDTO dto);

	Set<UserDTO> entityToDTO(Set<User> visitors);

}
