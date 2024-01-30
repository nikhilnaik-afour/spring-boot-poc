package com.poc.dellnxppoc.db.controller;

import com.poc.dellnxppoc.db.model.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

public interface ClientControllerInterface {

    @Operation(summary = "List of Client data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Clients",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Client.class))) })
    })
    List<Client> getClients();
}
