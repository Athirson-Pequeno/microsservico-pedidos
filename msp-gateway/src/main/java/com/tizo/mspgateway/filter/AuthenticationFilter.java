package com.tizo.mspgateway.filter;

import com.tizo.mspgateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private RestTemplate template;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (((exchange, chain) -> {
            if (       validator.isSecured.test(exchange.getRequest())
                    || validator.requiresAdminAccess.test(exchange.getRequest())
                    || validator.requiresUserAccess.test(exchange.getRequest())){

                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){

                   throw new RuntimeException("Missing authorization header token");

                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                if (authHeader !=null && authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7);
                }

                try {
                    List<String> listRoles = jwtUtil.getRoles(authHeader);

                    if(!listRoles.contains("ROLE_ADMIN") && validator.requiresAdminAccess.test(exchange.getRequest())){

                        throw new RuntimeException("Requires administrator access");

                    }

                }catch (Exception e){


                    throw new RuntimeException("Un authorized access to application");

                }

            }
            return chain.filter(exchange);
        }));
    }

    public static class Config{

    }
}
