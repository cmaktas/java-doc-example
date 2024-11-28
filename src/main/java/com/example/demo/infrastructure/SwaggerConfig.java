package com.example.demo.infrastructure;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up Swagger (OpenAPI) documentation.
 * <p>
 * This class demonstrates best practices for configuring Swagger in a Java Spring application,
 * including the addition of API metadata, external documentation links, and contact information.
 * </p>
 */
@Configuration
public class SwaggerConfig {

    /**
     * Creates and configures the OpenAPI specification for the application.
     * <p>
     * This method sets up the API metadata such as title, version, description, contact information,
     * license, and terms of service. It also includes links to external resources like the project's
     * GitHub repository, hosted Javadocs, and README.
     * </p>
     *
     * @return the configured {@link OpenAPI} object
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info()
                        .title("Java Documentation Practices API") // The title of the API
                        .version("1.0") // Version of the API
                        .summary("An example project showcasing Java Spring documentation best practices.") // A brief summary of the API
                        .description("This project demonstrates Java Spring documentation practices, including:\n\n" +
                                "**Resources**:\n" +
                                "- [Project Repository](https://github.com/cmaktas/java-doc-example)\n" +
                                "- [Project Hosted Javadocs](http://localhost:8080/javadocs/index.html)\n" +
                                "- [Project README](https://github.com/cmaktas/java-doc-example#readme)") // Detailed description with links
                        .contact(new Contact()
                                .name("Name Surname") // Contact name
                                .email("example@example.com") // Contact email
                                .url("https://www.example.com") // URL for more contact details (e.g., personal website)
                        )
                        .license(new License()
                                .name("MIT License") // Name of the license
                                .url("https://opensource.org/licenses/MIT") // URL of the license
                        )
                        .termsOfService("https://example.com/terms") // Terms of service URL
                )
                .externalDocs(new ExternalDocumentation()
                        .description("External Example") // External documentation description
                        .url("https://www.example.com") // URL for the README
                );
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}

/*
 * Notes on the limitations of `externalDocs` in OpenAPI:
 *
 * 1. The `externalDocs` field only supports a single object at a time, meaning you cannot add multiple
 *    external documentation links natively.
 * 2. This limitation can be worked around by combining multiple links into the `description` field of
 *    a single `ExternalDocumentation` object. Markdown-style links (e.g., `[link text](URL)`) can be used
 *    for clarity and accessibility.
 * 3. Another workaround is to use the `tags` feature in OpenAPI. Each tag can have its own `externalDocs`
 *    object, allowing you to associate specific links with particular areas of your API.
 *    Example:
 *    ```
 *    .tags(List.of(
 *        new Tag()
 *            .name("User Management")
 *            .description("APIs related to user management.")
 *            .externalDocs(new ExternalDocumentation()
 *                .description("User Management Docs")
 *                .url("https://example.com/user-docs")),
 *        new Tag()
 *            .name("Admin APIs")
 *            .description("APIs for administrative tasks.")
 *            .externalDocs(new ExternalDocumentation()
 *                .description("Admin Documentation")
 *                .url("https://example.com/admin-docs"))
 *    ))
 *    ```
 * 4. Hosting a single HTML page that consolidates all external links is another effective alternative.
 *    This page can then be referenced in the `externalDocs` URL.
 */
