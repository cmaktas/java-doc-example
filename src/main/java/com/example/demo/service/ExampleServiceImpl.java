package com.example.demo.service;

import com.example.demo.utils.ExampleDataGenerator;
import com.example.demo.web.model.v1.request.ExampleRequest;
import com.example.demo.web.model.v1.response.ExampleResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link ExampleService}.
 * <p>
 * This class provides a mock implementation for managing example resources, including creating, updating,
 * deleting, and retrieving examples. Data is stored in memory for demonstration purposes.
 * </p>
 */
@Service
public class ExampleServiceImpl implements ExampleService {

    private final AtomicLong idGenerator = new AtomicLong(1);
    private final List<ExampleResponse> examples = new ArrayList<>();

    /**
     * Initializes the service with mock data.
     * <p>
     * This method generates a predefined number of mock example resources to populate the in-memory storage.
     * </p>
     */
    @PostConstruct
    private void initializeExamples() {
        // Generate 5 mock ExampleResponse objects and add them to the examples list
        examples.addAll(ExampleDataGenerator.generateExampleRequests(10));
        idGenerator.set(examples.size() + 1); // Ensure the ID generator starts after the preloaded IDs
    }

    /**
     * Creates a new example resource.
     *
     * @param exampleRequest the request object containing the details of the example to create
     * @return the created example resource
     */
    @Override
    public ExampleResponse createExample(ExampleRequest exampleRequest) {
        ExampleResponse response = new ExampleResponse(
                idGenerator.getAndIncrement(),
                exampleRequest.getName(),
                exampleRequest.getDescription(),
                exampleRequest.getAge(),
                exampleRequest.getPrice(),
                exampleRequest.getIsActive(),
                exampleRequest.getCreationDate(),
                exampleRequest.getTags(),
                exampleRequest.getExampleType()
        );
        examples.add(response);
        return response;
    }

    /**
     * Deletes an example resource by ID.
     *
     * @param id the ID of the example to delete
     */
    @Override
    public void deleteExample(Long id) {
        examples.removeIf(example -> example.getId().equals(id));
    }

    /**
     * Updates an existing example resource.
     *
     * @param id             the ID of the example to update
     * @param exampleRequest the request object containing updated details of the example
     * @return the updated example resource
     */
    @Override
    public ExampleResponse updateExample(Long id, ExampleRequest exampleRequest) {
        for (ExampleResponse example : examples) {
            if (example.getId().equals(id)) {
                example.setName(exampleRequest.getName());
                example.setDescription(exampleRequest.getDescription());
                return example;
            }
        }
        throw new IllegalArgumentException("Example with ID " + id + " not found.");
    }

    /**
     * Retrieves a list of example resources, optionally filtered by type.
     *
     * @param type the type of examples to filter by (can be null to retrieve all examples)
     * @return a list of matching example resources
     */
    @Override
    public List<ExampleResponse> listExamples(ExampleRequest.ExampleType type) {
        if (type == null) {
            return new ArrayList<>(examples);
        }
        return examples.stream()
                .filter(example -> example.getExampleType() == type)
                .collect(Collectors.toList());
    }
}
