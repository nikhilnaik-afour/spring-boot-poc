/**
 * 
 */
package com.poc.dellnxppoc.emgmt.controller;


import com.afour.emgmt.model.UserRegistrationDTO;
import com.poc.dellnxppoc.emgmt.common.AppResponse;
import com.poc.dellnxppoc.emgmt.common.AppResponseBuilder;
import com.poc.dellnxppoc.emgmt.exception.EmptyRequestException;
import com.poc.dellnxppoc.emgmt.model.UserDTO;
import com.poc.dellnxppoc.emgmt.service.VisitorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is a Controller class having all the REST (GET POST PUT DELETE) end
 * points to manage any Visitor.
 * 
 * @author Sandeep Jariya
 */
@RestController
@RequestMapping("/visitor")
public class VisitorController implements VisitorControllerInterface {

	private final VisitorService service;

	private final AppResponseBuilder responseBuilder;

	public VisitorController(VisitorService service, AppResponseBuilder responseBuilder) {
		this.service = service;
		this.responseBuilder = responseBuilder;
	}

	/* Get all the visitors without any filter */
	@Override

	@GetMapping(value = "/visitors", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> fetchAllVisitors() {
		List<UserDTO> result = service.fetchAllVisitors();

		return new ResponseEntity<>(responseBuilder.getSuccessDataFoundResponse(result, result.size()), HttpStatus.OK);
	}

	/* Get a visitor using its id */
	@Override
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> findVisitorByID(@PathVariable(value = "id") final Integer id) {
		if (null == id)
			throw new EmptyRequestException();

		UserDTO result = service.findVisitorByID(id);

		return new ResponseEntity<>(responseBuilder.getSuccessDataFoundResponse(result, 1), HttpStatus.OK);
	}

	/* Get a visitor using its username */
	@Override
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> findVisitorByUserName(@RequestParam(value = "userName") final String userName) {
		if (null == userName)
			throw new EmptyRequestException();

		UserDTO result = service.findVisitorByUserName(userName);

		return new ResponseEntity<>(responseBuilder.getSuccessDataFoundResponse(result, 1), HttpStatus.OK);
	}

	/* Create a new visitor */
	@Override
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> addVisitor(@RequestBody UserDTO dto) {
		if (null == dto)
			throw new EmptyRequestException();

		UserDTO result = service.addVisitor(dto);

		AppResponse response = responseBuilder.getRequestSuccessResponse("visitor.create.success", result, HttpStatus.CREATED);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/* Update an existing visitor */
	@Override
	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> updateVisitor(@RequestBody UserDTO dto) {
		if (null == dto)
			throw new EmptyRequestException();

		UserDTO result = service.updateVisitor(dto);

		AppResponse response = responseBuilder.getRequestSuccessResponse("visitor.update.success", result, HttpStatus.CREATED);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/* Delete an existing visitor using its id */
	@Override
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> deleteVisitorByID(@PathVariable(value = "id") final Integer id) {
		if (null == id)
			throw new EmptyRequestException();

		boolean result = service.deleteVisitorByID(id);

		AppResponse response = responseBuilder.getRequestSuccessResponse("visitor.delete.success", result, HttpStatus.ACCEPTED);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/* Register one existing visitor for one or more events */
	@Override
	@PostMapping(value = "/registerForEvent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> registerVisitorForEvent(@RequestBody UserRegistrationDTO dto) {
		if (null == dto)
			throw new EmptyRequestException();

		UserDTO result = service.registerVisitorForEvent(dto);
		AppResponse response = responseBuilder.getRequestSuccessResponse("visitor.register.success", result, HttpStatus.CREATED);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
