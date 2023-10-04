package com.tizo.mspgateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {



    public static final List<String> openApiEndpoints = List.of(

            "/msp-auth/token",
            "/eureka",
            "/h2-console/**",
            "/msp-auth/register/user"

    );

    public static final List<String> adminApiEndpoints = List.of(

            "/msp-auth/register/admin"

    );

    public static final List<String> userApiEndpoints = List.of(
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

    public Predicate<ServerHttpRequest> requiresAdminAccess =
            request -> adminApiEndpoints
                    .stream()
                    .anyMatch(uri -> request.getURI().getPath().contains(uri));
    public Predicate<ServerHttpRequest> requiresUserAccess =
            request -> adminApiEndpoints
                    .stream()
                    .anyMatch(uri -> request.getURI().getPath().contains(uri));

}



