/**
 * 
 */
package com.poc.dellnxppoc.emgmt.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.poc.dellnxppoc.emgmt.entity.Event;
import com.poc.dellnxppoc.emgmt.model.EventDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 */
@Component
public class EventMapperImpl implements EventMapper {

	private final ModelMapper modelMapper;

	@Autowired
	public EventMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public EventDTO entityToDTO(Event entity) {
		return modelMapper.map(entity, EventDTO.class);
	}

	@Override
	public Event DTOToEntity(EventDTO dto) {
		return modelMapper.map(dto, Event.class);
	}

	@Override
	public List<EventDTO> entityToDTO(List<Event> entities) {
		return entities
				.stream()
				.map(this::entityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<Event> DTOToEntity(List<EventDTO> dtos) {
		return dtos
				.stream()
				.map(this::DTOToEntity)
				.collect(Collectors.toList());
	}

	@Override
	public Event prepareForUpdate(Event entity, EventDTO dto) {
		final String ACTOR = "test";

		if (null != dto.getEventName())
			entity.setEventName(dto.getEventName());
		if (null != dto.getIsClosed())
			entity.setClosed(dto.getIsClosed());
		if (null != dto.getStartAt())
			entity.setStartAt(dto.getStartAt());
		if (null != dto.getEndAt())
			entity.setEndAt(dto.getEndAt());
		if (null != dto.getLocation())
			entity.setLocation(dto.getLocation());
		
		entity.setUpdatedAt(LocalDateTime.now());
		entity.setUpdatedBy(ACTOR);
		return entity;
	}

	@Override
	public Set<EventDTO> entityToDTO(Set<Event> entities) {
		return entities
				.stream()
				.map(this::entityToDTO)
				.collect(Collectors.toSet());
	}

	@Override
	public Set<Event> DTOToEntity(Set<EventDTO> dtos) {
		return dtos
				.stream()
				.map(this::DTOToEntity)
				.collect(Collectors.toSet());
	}
	
	@Override
	public Event prepareForCreate(EventDTO dto) {
		final String ACTOR = "test";

		Event entity = this.DTOToEntity(dto);
		entity.setCreatedAt(LocalDateTime.now());
		entity.setCreatedBy(ACTOR);
		entity.setUpdatedAt(LocalDateTime.now());
		entity.setUpdatedBy(ACTOR);
		return entity;
	}

}
