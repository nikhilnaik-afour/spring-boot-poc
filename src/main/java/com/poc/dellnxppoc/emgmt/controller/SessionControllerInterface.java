package com.poc.dellnxppoc.emgmt.controller;

import com.poc.dellnxppoc.emgmt.common.AppResponse;
import com.poc.dellnxppoc.emgmt.model.EsessionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface SessionControllerInterface {
    /* Get all sessions of an event */
    @Operation(description = "Fetch all Session by its Event Id.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the sessions!"),
            @ApiResponse(responseCode = "204", description = "No data found!")})
    ResponseEntity<AppResponse> findSessionEventByID(@RequestParam(value = "eventId") Integer eventId);

    /* Get an existing session using its id */
    @Operation(description = "Fetch Session by Session Id.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the sessions!"),
            @ApiResponse(responseCode = "204", description = "No data found!")})
    @GetMapping(value = "/{ID}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AppResponse> findSessionByID(@PathVariable(value = "ID") Integer id);

    /* Create a new session under any event */
    @Operation(description = "Create a new Session.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Created!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AppResponse> addSession(@RequestBody EsessionDTO dto);

    /* Update an existing session */
    @Operation(description = "Update the Session.")
    @ApiResponses(value = {@ApiResponse(responseCode = "202", description = "Accepted and Updated!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AppResponse> updateSession(@RequestBody EsessionDTO dto);

    /* Delete one existing session */
    @Operation(description = "Delete the Session.")
    @ApiResponses(value = {@ApiResponse(responseCode = "202", description = "Deleted the requested session!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    @DeleteMapping(value = "/{ID}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AppResponse> deleteSession(@PathVariable(value = "ID") Integer id);
}
