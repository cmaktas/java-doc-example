package com.example.demo.web.controller.v1;

import com.example.demo.service.ExampleService;
import com.example.demo.web.model.v1.request.ExampleRequest;
import com.example.demo.web.model.v1.response.ExampleResponse;
import com.example.demo.web.model.v1.response.ListExampleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing example resources.
 * <p>
 * Provides endpoints for creating, updating, deleting, and listing example resources.
 * </p>
 */
@Tag(name = "Example API", description = "APIs for creating, updating, deleting, and listing examples.")
@AllArgsConstructor
@RestController
@RequestMapping("/api/example")
public class ExampleController {

    private final ExampleService exampleService;

    /**
     * Creates a new example resource.
     *
     * @param exampleRequest the request object containing details of the example to create
     * @return the created example resource
     */
    @Operation(
            summary = "Create Example",
            description = "Creates a new example resource."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully created the example."),
            @ApiResponse(responseCode = "400", description = "Invalid input."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PostMapping
    public ResponseEntity<ExampleResponse> createExample(
            @RequestBody @Parameter(description = "Details of the example to create.") ExampleRequest exampleRequest) {
        ExampleResponse response = exampleService.createExample(exampleRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes an existing example resource by ID.
     *
     * @param id the ID of the example to delete
     * @return a response with no content
     */
    @Operation(
            summary = "Delete Example",
            description = "Deletes an existing example resource by ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Successfully deleted the example."),
            @ApiResponse(responseCode = "404", description = "Example not found.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExample(
            @PathVariable @Parameter(description = "ID of the example to delete.") Long id) {
        exampleService.deleteExample(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing example resource.
     *
     * @param id             the ID of the example to update
     * @param exampleRequest the request object containing updated details of the example
     * @return the updated example resource
     */
    @Operation(
            summary = "Update Example",
            description = "Updates an existing example resource."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated the example."),
            @ApiResponse(responseCode = "404", description = "Example not found."),
            @ApiResponse(responseCode = "400", description = "Invalid input.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ExampleResponse> updateExample(
            @PathVariable @Parameter(description = "ID of the example to update.") Long id,
            @RequestBody @Parameter(description = "Details of the example to update.") ExampleRequest exampleRequest) {
        ExampleResponse response = exampleService.updateExample(id, exampleRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * Lists all example resources, optionally filtered by type.
     *
     * @param type the optional filter for examples by type
     * @return a list of example resources
     */
    @Operation(
            summary = "List Examples",
            description = "Retrieves a list of all examples, optionally filtered by type."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the examples."),
            @ApiResponse(responseCode = "400", description = "Invalid type filter.")
    })
    @GetMapping
    public ResponseEntity<ListExampleResponse> listExamples(
            @RequestParam(value = "type", required = false)
            @Parameter(description = "Optional filter for example type.", schema = @Schema(allowableValues = {"BASIC", "ADVANCED", "PRO"}))
            ExampleRequest.ExampleType type) {
        List<ExampleResponse> examples = exampleService.listExamples(type);
        return ResponseEntity.ok(new ListExampleResponse(examples));
    }

    /**
     * Demonstrates returning different HTTP status codes based on a header parameter.
     *
     * @param status the HTTP status code to return
     * @return a response with the specified status code
     */
    @Operation(
            summary = "Dynamic Status Response",
            description = "Demonstrates returning different HTTP status codes based on a header parameter."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "404", description = "Not Found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")
    })
    @GetMapping("/dynamic-status")
    public ResponseEntity<Void> dynamicStatusResponse(
            @RequestHeader(value = "X-Response-Status", required = true)
            @Parameter(description = "The HTTP status code to return.", example = "200") int status) {
        HttpStatus httpStatus = HttpStatus.resolve(status);
        if (httpStatus == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(httpStatus).build();
    }
}