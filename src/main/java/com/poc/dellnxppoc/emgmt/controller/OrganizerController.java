/**
 * 
 */
package com.poc.dellnxppoc.emgmt.controller;

import java.util.List;

import com.poc.dellnxppoc.emgmt.common.AppResponse;
import com.poc.dellnxppoc.emgmt.common.AppResponseBuilder;
import com.poc.dellnxppoc.emgmt.exception.EmptyRequestException;
import com.poc.dellnxppoc.emgmt.model.UserDTO;
import com.poc.dellnxppoc.emgmt.service.OrganizerService;
import io.swagger.v3.oas.annotations.Operation;
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
 * points to manage any Organizer.
 * 
 * @author Test
 */
@RestController
@RequestMapping("/organizer")
public class OrganizerController implements OrganizerControllerInterface {

	private final OrganizerService service;

	private final AppResponseBuilder responseBuilder;

	public OrganizerController(OrganizerService service, AppResponseBuilder responseBuilder) {
		this.service = service;
		this.responseBuilder = responseBuilder;
	}

	/* Get all the existing organizers without any filter */
	@Override
	@Operation(description = "Fetch all the organizers without any filter!")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found all the organizers!"),
			@ApiResponse(responseCode = "204", description = "No data found!") })
	@GetMapping(value = "/organizers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> fetchAllOrganizers() {
		List<UserDTO> result = service.fetchAllOrganizers();

		return new ResponseEntity<>(responseBuilder.getSuccessDataFoundResponse(result, result.size()), HttpStatus.OK);
	}

	/* Get one existing organizer using its id */
	@Override
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> findOrganizerByID(@PathVariable(value = "id") final Integer id) {
		if (null == id)
			throw new EmptyRequestException();

		UserDTO result = service.findOrganizerByID(id);

		return new ResponseEntity<>(responseBuilder.getSuccessDataFoundResponse(result, 1), HttpStatus.OK);
	}

	/* Get one existing organizer using its username */
	@Override
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> findOrganizerByUserName(@RequestParam(value = "userName") final String userName) {
		if (null == userName)
			throw new EmptyRequestException();

		UserDTO result = service.findOrganizerByUserName(userName);

		return new ResponseEntity<>(responseBuilder.getSuccessDataFoundResponse(result, 1), HttpStatus.OK);
	}

	/* Create a new organizer */
	@Override
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> addOrganizer(@RequestBody UserDTO dto) {
		if (null == dto)
			throw new EmptyRequestException();

		UserDTO result = service.addOrganizer(dto);

		AppResponse response = responseBuilder.getRequestSuccessResponse("organizer.create.success", result, HttpStatus.CREATED);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/* update an existing organizer */
	@Override
	@PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> updateOrganizer(@RequestBody UserDTO dto) {
		if (null == dto)
			throw new EmptyRequestException();

		UserDTO result = service.updateOrganizer(dto);

		AppResponse response = responseBuilder.getRequestSuccessResponse("organizer.update.success", result, HttpStatus.CREATED);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/* Delete one organizer using its id */
	@Override
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppResponse> deleteOrganizer(@PathVariable(value = "id") final Integer id) {
		if (null == id)
			throw new EmptyRequestException();

		boolean result = service.deleteOrganizerByID(id);

		AppResponse response = responseBuilder.getRequestSuccessResponse("organizer.delete.success", result, HttpStatus.ACCEPTED);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
