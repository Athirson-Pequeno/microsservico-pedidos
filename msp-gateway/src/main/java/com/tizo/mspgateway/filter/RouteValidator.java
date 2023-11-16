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
            "/msp-auth/register/user",
            "/msp-products",
            "/msp-auth/validate"

    );

    public static final List<String> adminApiEndpoints = List.of(

            "/msp-auth/register/admin",
            "/msp-products/new",
            "/msp-orders/all"

    );

    public static final List<String> userApiEndpoints = List.of(

            "/msp-orders/new",
            "/msp-orders/orders/update/",
            "/msp-orders/orders/clientorders"

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
            request -> userApiEndpoints
                    .stream()
                    .anyMatch(uri -> request.getURI().getPath().contains(uri));

}



