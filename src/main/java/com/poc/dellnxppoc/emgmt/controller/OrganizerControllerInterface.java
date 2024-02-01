package com.poc.dellnxppoc.emgmt.controller;

import com.poc.dellnxppoc.emgmt.common.AppResponse;
import com.poc.dellnxppoc.emgmt.model.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface OrganizerControllerInterface {
    /* Get all the existing organizers without any filter */
    @Operation(description = "Fetch all the organizers without any filter!")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found all the organizers!"),
            @ApiResponse(responseCode = "204", description = "No data found!")})
    @GetMapping(value = "/organizers", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AppResponse> fetchAllOrganizers();

    /* Get one existing organizer using its id */
    @Operation(description = "Fetch one organizer by ID!")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the organizer!"),
            @ApiResponse(responseCode = "204", description = "No data found!")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AppResponse> findOrganizerByID(@PathVariable(value = "id") Integer id);

    /* Get one existing organizer using its username */
    @Operation(description = "Fetch one organizers by USERNAME!")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the organizer!"),
            @ApiResponse(responseCode = "204", description = "No data found!")})
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AppResponse> findOrganizerByUserName(@RequestParam(value = "userName") String userName);

    /* Create a new organizer */
    @Operation(description = "Create a new Organizer.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Created!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AppResponse> addOrganizer(@RequestBody UserDTO dto);

    /* update an existing organizer */
    @Operation(description = "Update an Organizer.")
    @ApiResponses(value = {@ApiResponse(responseCode = "202", description = "Accepted and Updated!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AppResponse> updateOrganizer(@RequestBody UserDTO dto);

    /* Delete one organizer using its id */
    @Operation(description = "Delete an Organizer.")
    @ApiResponses(value = {@ApiResponse(responseCode = "202", description = "Deleted the requested organizer!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AppResponse> deleteOrganizer(@PathVariable(value = "id") Integer id);
}
