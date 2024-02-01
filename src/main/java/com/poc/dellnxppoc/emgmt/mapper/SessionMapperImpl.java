/**
 * 
 */
package com.poc.dellnxppoc.emgmt.mapper;

import com.poc.dellnxppoc.emgmt.entity.Esession;
import com.poc.dellnxppoc.emgmt.model.EsessionDTO;
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
public class SessionMapperImpl implements SessionMapper {

	private final ModelMapper modelMapper;

	public SessionMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public EsessionDTO entityToDTO(Esession entity) {
		return modelMapper.map(entity, EsessionDTO.class);
	}

	@Override
	public Esession DTOToEntity(EsessionDTO dto) {
		return modelMapper.map(dto, Esession.class);
	}

	@Override
	public List<EsessionDTO> entityToDTO(List<Esession> entities) {
		return entities
				.stream()
				.map(this::entityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<Esession> DTOToEntity(List<EsessionDTO> dtos) {
		return dtos
				.stream()
				.map(this::DTOToEntity)
				.collect(Collectors.toList());
	}

	@Override
	public Esession prepareForUpdate(Esession entity, EsessionDTO dto) {
		final String ACTOR = "test";

		if (null != dto.getEsessionTitle())
			entity.setEsessionTitle(dto.getEsessionTitle());
		if (null != dto.getStartAt())
			entity.setStartAt(dto.getStartAt());
		if (null != dto.getEndAt())
			entity.setEndAt(dto.getEndAt());
		
		entity.setUpdatedAt(LocalDateTime.now());
		entity.setUpdatedBy(ACTOR);
		return entity;
	}

	@Override
	public Set<EsessionDTO> entityToDTO(Set<Esession> entities) {
		return entities
				.stream()
				.map(this::entityToDTO)
				.collect(Collectors.toSet());
	}

	@Override
	public Set<Esession> DTOToEntity(Set<EsessionDTO> dtos) {
		return dtos
				.stream()
				.map(this::DTOToEntity)
				.collect(Collectors.toSet());
	}
	
	@Override
	public Esession prepareForCreate(EsessionDTO dto) {
		final String ACTOR = "test";

		Esession entity = this.DTOToEntity(dto);
		entity.setCreatedAt(LocalDateTime.now());
		entity.setCreatedBy(ACTOR);
		entity.setUpdatedAt(LocalDateTime.now());
		entity.setUpdatedBy(ACTOR);
		return entity;
	}

}
