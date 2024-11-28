package com.example.demo.utils;

import com.example.demo.web.model.v1.request.ExampleRequest;
import com.example.demo.web.model.v1.request.ExampleRequest.ExampleType;
import com.example.demo.web.model.v1.response.ExampleResponse;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for generating mock data for ExampleRequest objects.
 * <p>
 * This utility provides static methods to generate mock data for testing and demonstration purposes. The generated
 * data includes a variety of fields, demonstrating different data types, validation constraints, and default values.
 * </p>
 */
@UtilityClass
public class ExampleDataGenerator {

    /**
     * Generates a list of mock ExampleRequest objects.
     *
     * @param count the number of mock objects to generate
     * @return a list of ExampleRequest objects
     */
    public static List<ExampleResponse> generateExampleRequests(int count) {
        List<ExampleResponse> responses = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            responses.add(new ExampleResponse(
                    (long) i,
                    "Example Name " + i,
                    "Description for Example " + i,
                    20 + i, // Age
                    100.0 + i * 10, // Price
                    i % 2 == 0, // isActive: true for even IDs
                    LocalDate.now().minusDays(i), // creationDate: current date minus i days
                    new String[]{"tag" + i, "tag" + (i + 1)}, // Tags
                    ExampleType.values()[i % ExampleType.values().length] // Rotating ExampleType
            ));
        }
        return responses;
    }
}
