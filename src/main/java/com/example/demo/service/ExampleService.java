package com.example.demo.service;

import com.example.demo.web.model.v1.request.ExampleRequest;
import com.example.demo.web.model.v1.response.ExampleResponse;

import java.util.List;

/**
 * Service interface for handling example-related business logic.
 */
public interface ExampleService {

    ExampleResponse createExample(ExampleRequest exampleRequest);

    void deleteExample(Long id);

    ExampleResponse updateExample(Long id, ExampleRequest exampleRequest);

    List<ExampleResponse> listExamples(ExampleRequest.ExampleType type);
}
