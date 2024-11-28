package com.example.demo.web.model.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Request object for creating or updating an example with various field types.
 * <p>
 * This class demonstrates a variety of data types and Swagger documentation annotations to generate a rich API
 * specification. It serves as a representation of the payload required for creating or updating an example resource.
 * </p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleRequest {

    @Schema(description = "Unique identifier for the example", example = "12345", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Name of the example", example = "Example Name", required = true)
    private String name;

    @Schema(description = "Description of the example", example = "This is an example description", required = true)
    private String description;

    @Schema(description = "Age associated with the example", example = "25")
    private Integer age;

    @Schema(description = "Price of the example", example = "199.99")
    private Double price;

    @Schema(description = "Flag to indicate if the example is active", example = "true")
    private Boolean isActive;

    @Schema(description = "Date of creation", example = "2024-01-01")
    private LocalDate creationDate;

    @Schema(description = "Array of tags associated with the example", example = "[\"tag1\", \"tag2\"]")
    private String[] tags;

    @Schema(description = "Type of example, represented as an enumeration", example = "BASIC")
    private ExampleType exampleType;

    /**
     * Enum representing example types.
     */
    public enum ExampleType {
        BASIC, ADVANCED, PRO
    }
}
