package com.poc.dellnxppoc.emgmt.controller;

import com.poc.dellnxppoc.emgmt.common.AppResponse;
import com.poc.dellnxppoc.emgmt.model.EventDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface EventControllerInterface {
    /* Get all the existing events without any filter */
    @Operation(summary = "Fetch all the events without any filter!")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found all the events!"),
            @ApiResponse(responseCode = "204", description = "No data found!")})
    ResponseEntity<AppResponse> fetchAllEvents();


    /* Get all the existing events by filtering them on there status */
    @Operation(description = "Fetch all the OPEN events!")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found all the OPEN/CLOSED events!"),
            @ApiResponse(responseCode = "204", description = "No data found!")})
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AppResponse> fetchEventsByStatus(
            @RequestParam(value = "open", defaultValue = "true") Boolean status);

    /* Get one existing event using its id */
    @Operation(summary = "Fetch an Event by ID!")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the Event!"),
            @ApiResponse(responseCode = "204", description = "No data found!")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AppResponse> findEventByID(@PathVariable(value = "id") Integer id);

    /* Create a new event */
    @Operation(summary = "Create a new Event.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Created!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AppResponse> addEvent(@RequestBody EventDTO eventDTO);

    /* update one existing event */
    @Operation(summary = "Update an Event.")
    @ApiResponses(value = {@ApiResponse(responseCode = "202", description = "Accepted and Updated!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AppResponse> updateEvent(@RequestBody EventDTO eventDTO);

    /* delete one existing event */
    @Operation(summary = "Delete the Event.")
    @ApiResponses(value = {@ApiResponse(responseCode = "202", description = "Deleted the requested event!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AppResponse> deleteEventByID(@PathVariable(value = "id") Integer id);
}
