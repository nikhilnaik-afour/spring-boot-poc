/**
 * 
 */
package com.poc.dellnxppoc.emgmt.controller;

import java.util.List;

import com.poc.dellnxppoc.emgmt.common.AppResponse;
import com.poc.dellnxppoc.emgmt.common.AppResponseBuilder;
import com.poc.dellnxppoc.emgmt.exception.EmptyRequestException;
import com.poc.dellnxppoc.emgmt.model.EventDTO;
import com.poc.dellnxppoc.emgmt.service.EventService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * This is a Controller class having all the REST (GET POST PUT DELETE) end
 * points to manage any Event.
 *
 * @author Test
 */
@RestController
@RequestMapping("/event")
public class EventController implements EventControllerInterface {

	private final EventService service;

	private final AppResponseBuilder responseBuilder;

	public EventController(EventService service, AppResponseBuilder responseBuilder) {
		this.service = service;
		this.responseBuilder = responseBuilder;
	}

	/* Get all the existing events without any filter */
	@Override
	@GetMapping(value = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> fetchAllEvents() {
		List<EventDTO> result = service.fetchAllEvents();

		return new ResponseEntity<>(responseBuilder.getSuccessDataFoundResponse(result, result.size()), HttpStatus.OK);
	}

	/* Get all the existing events by filtering them on there status */
	@Override
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> fetchEventsByStatus(
			@RequestParam(value = "open", defaultValue = "true") final Boolean status) {
		if (null == status)
			throw new EmptyRequestException();

		List<EventDTO> result = service.fetchEventsByStatus(status);
		return new ResponseEntity<>(responseBuilder.getSuccessDataFoundResponse(result, result.size()), HttpStatus.OK);
	}

	/* Get one existing event using its id */
	@Override
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> findEventByID(@PathVariable(value = "id") final Integer id) {
		if (null == id)
			throw new EmptyRequestException();

		EventDTO result = service.findEventByID(id);

		return new ResponseEntity<>(responseBuilder.getSuccessDataFoundResponse(result, 1), HttpStatus.OK);
	}

	/* Create a new event */
	@Override
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> addEvent(@RequestBody EventDTO eventDTO) {
		if (null == eventDTO)
			throw new EmptyRequestException();

		EventDTO result = service.addEvent(eventDTO);

		AppResponse response = responseBuilder.getRequestSuccessResponse("event.create.success", result, HttpStatus.CREATED);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/* update one existing event */
	@Override
	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> updateEvent(@RequestBody EventDTO eventDTO) {
		if (null == eventDTO)
			throw new EmptyRequestException();

		EventDTO result = service.updateEvent(eventDTO);

		AppResponse response = responseBuilder.getRequestSuccessResponse("event.update.success", result, HttpStatus.ACCEPTED);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/* delete one existing event */
	@Override
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> deleteEventByID(@PathVariable(value = "id") final Integer id) {
		if (null == id)
			throw new EmptyRequestException();

		boolean result = service.deleteEventByID(id);

		AppResponse response = responseBuilder.getRequestSuccessResponse("event.delete.success", result, HttpStatus.ACCEPTED);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
