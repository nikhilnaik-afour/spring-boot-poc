/**
 * 
 */
package com.poc.dellnxppoc.emgmt.mapper;

import com.poc.dellnxppoc.emgmt.entity.Event;
import com.poc.dellnxppoc.emgmt.model.EventDTO;

import java.util.List;
import java.util.Set;

/**
 * 
 */
public interface EventMapper {

	EventDTO entityToDTO(Event entity);

	Event DTOToEntity(EventDTO dto);

	List<EventDTO> entityToDTO(List<Event> entities);

	List<Event> DTOToEntity(List<EventDTO> dtos);

	Event prepareForUpdate(Event entity, EventDTO dto);

	Set<EventDTO> entityToDTO(Set<Event> entities);

	Set<Event> DTOToEntity(Set<EventDTO> dtos);

	Event prepareForCreate(EventDTO dto);

}
