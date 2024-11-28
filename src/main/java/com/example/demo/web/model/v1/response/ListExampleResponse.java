package com.example.demo.web.model.v1.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response object for listing multiple examples.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListExampleResponse {
    private List<ExampleResponse> examples;
}