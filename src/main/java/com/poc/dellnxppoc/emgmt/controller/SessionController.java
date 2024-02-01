/**
 * 
 */
package com.poc.dellnxppoc.emgmt.controller;

import java.util.List;

import com.poc.dellnxppoc.emgmt.common.AppResponse;
import com.poc.dellnxppoc.emgmt.common.AppResponseBuilder;
import com.poc.dellnxppoc.emgmt.exception.EmptyRequestException;
import com.poc.dellnxppoc.emgmt.model.EsessionDTO;
import com.poc.dellnxppoc.emgmt.service.SessionService;
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
 * points to manage any Session.
 * 
 * @author Sandeep Jariya
 */
@RestController
@RequestMapping("/session")
public class SessionController implements SessionControllerInterface {

	private final SessionService service;

	private final AppResponseBuilder responseBuilder;

	public SessionController(SessionService service, AppResponseBuilder responseBuilder) {
		this.service = service;
		this.responseBuilder = responseBuilder;
	}

	/* Get all sessions of an event */
	@Override
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> findSessionEventByID(@RequestParam(value = "eventId") final Integer eventId) {
		if (null == eventId)
			throw new EmptyRequestException();

		List<EsessionDTO> result = service.findSessionEventByID(eventId);

		return new ResponseEntity<>(responseBuilder.getSuccessDataFoundResponse(result, result.size()), HttpStatus.OK);
	}

	/* Get an existing session using its id */
	@Override
	@GetMapping(value = "/{ID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> findSessionByID(@PathVariable(value = "ID") final Integer id) {
		if (null == id)
			throw new EmptyRequestException();

		EsessionDTO result = service.findSessionByID(id);

		return new ResponseEntity<>(responseBuilder.getSuccessDataFoundResponse(result, 1), HttpStatus.OK);
	}

	/* Create a new session under any event */
	@Override
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> addSession(@RequestBody EsessionDTO dto) {
		if (null == dto)
			throw new EmptyRequestException();

		EsessionDTO result = service.addSession(dto);

		AppResponse response = responseBuilder.getRequestSuccessResponse("session.create.success", result, HttpStatus.CREATED);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/* Update an existing session */
	@Override
	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> updateSession(@RequestBody EsessionDTO dto) {
		if (null == dto)
			throw new EmptyRequestException();

		EsessionDTO result = service.updateSession(dto);
		
		AppResponse response = responseBuilder.getRequestSuccessResponse("session.update.success", result, HttpStatus.CREATED);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/* Delete one existing session */
	@Override
	@DeleteMapping(value = "/{ID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> deleteSession(@PathVariable(value = "ID") final Integer id) {
		if (null == id)
			throw new EmptyRequestException();

		Boolean result = service.deleteSessionByID(id);

		AppResponse response = responseBuilder.getRequestSuccessResponse("session.delete.success", result, HttpStatus.ACCEPTED);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
