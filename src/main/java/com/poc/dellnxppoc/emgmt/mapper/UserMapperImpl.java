/**
 * 
 */
package com.poc.dellnxppoc.emgmt.mapper;


import com.poc.dellnxppoc.emgmt.entity.User;
import com.poc.dellnxppoc.emgmt.model.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 */
@Component
public class UserMapperImpl implements UserMapper {

	private final ModelMapper modelMapper;

	public UserMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDTO entityToDTO(User entity) {
		return modelMapper.map(entity, UserDTO.class);
	}

	@Override
	public User DTOToEntity(UserDTO dto) {
		return modelMapper.map(dto, User.class);
	}

	@Override
	public List<UserDTO> entityToDTO(List<User> entities) {
		return entities
				.stream()
				.map(this::entityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<User> DTOToEntity(List<UserDTO> dtos) {
		return dtos
				.stream()
				.map(this::DTOToEntity)
				.collect(Collectors.toList());
	}

	@Override
	public User prepareForUpdate(User entity, UserDTO dto) {
		if (null != dto.getFirstName())
			entity.setFirstName(dto.getFirstName());
		if (null != dto.getLastName())
			entity.setLastName(dto.getLastName());
		if (null != dto.getIsActive())
			entity.setActive(dto.getIsActive());
		if(null != dto.getPassword())
			entity.setPassword(dto.getPassword());
		
		entity.setUpdatedAt(LocalDateTime.now());
		entity.setUpdatedBy("test");
		
		return entity;
	}

	@Override
	public User prepareForCreate(UserDTO dto) {
		final String ACTOR = "test";

		User entity = this.DTOToEntity(dto);
		entity.setCreatedAt(LocalDateTime.now());
		entity.setCreatedBy(ACTOR);
		entity.setUpdatedAt(LocalDateTime.now());
		entity.setUpdatedBy(ACTOR);
		entity.setPassword(dto.getPassword());
		return entity;
	}

	@Override
	public Set<UserDTO> entityToDTO(Set<User> entities) {
		return entities
				.stream()
				.map(this::entityToDTO)
				.collect(Collectors.toSet());
	}

}
