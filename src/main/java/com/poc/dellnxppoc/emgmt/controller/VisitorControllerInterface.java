package com.poc.dellnxppoc.emgmt.controller;

import com.poc.dellnxppoc.emgmt.common.AppResponse;
import com.poc.dellnxppoc.emgmt.model.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface VisitorControllerInterface {
    /* Get all the visitors without any filter */
    @Operation(description = "Fetch all the visitors without any filter!")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found all the visitors!"),
            @ApiResponse(responseCode = "204", description = "No data found!")})
    ResponseEntity<AppResponse> fetchAllVisitors();

    /* Get a visitor using its id */
    @Operation(description = "Fetch one Visitor by ID!")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the Visitor!"),
            @ApiResponse(responseCode = "204", description = "No data found!")})
    ResponseEntity<AppResponse> findVisitorByID(@PathVariable(value = "id") Integer id);

    /* Get a visitor using its username */
    @Operation(description = "Fetch one Visitor by USERNAME!")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the Visitor!"),
            @ApiResponse(responseCode = "204", description = "No data found!")})
    ResponseEntity<AppResponse> findVisitorByUserName(@RequestParam(value = "userName") String userName);

    /* Create a new visitor */
    @Operation(description = "Create a new Visitor.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Created!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    ResponseEntity<AppResponse> addVisitor(@RequestBody UserDTO dto);

    /* Update an existing visitor */
    @Operation(description = "Update a Visitor.")
    @ApiResponses(value = {@ApiResponse(responseCode = "202", description = "Accepted and Updated!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    ResponseEntity<AppResponse> updateVisitor(@RequestBody UserDTO dto);

    /* Delete an existing visitor using its id */
    @Operation(description = "Delete the visitor.")
    @ApiResponses(value = {@ApiResponse(responseCode = "202", description = "Deleted the requested visitor!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    ResponseEntity<AppResponse> deleteVisitorByID(@PathVariable(value = "id") Integer id);

    /* Register one existing visitor for one or more events */
    @Operation(description = "Register a Visitor for Events.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Registered!"),
            @ApiResponse(responseCode = "400", description = "Bad Request!")})
    ResponseEntity<AppResponse> registerVisitorForEvent(@RequestBody com.afour.emgmt.model.UserRegistrationDTO dto);
}
