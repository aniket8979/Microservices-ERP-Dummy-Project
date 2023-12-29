package com.school.ApiGateway.Filter;


import com.google.common.net.HttpHeaders;
import com.school.ApiGateway.Exception.GlobalException;
import com.school.ApiGateway.Utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpStatus;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;


    @Autowired
    private JwtUtils jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {

            ServerHttpRequest franchiseId = null;

            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//                    throw new RuntimeException("missing authorization header");
                    return this.onError(exchange,"token not present", HttpStatus.UNAUTHORIZED);
                }

                String token = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (token != null && token.startsWith("Bearer ")) {
                    token = token.substring(7);
                }
                try {
                    if (jwtUtil.isTokenExpired(token)){
                        return this.onError(exchange, "tokenExpired", HttpStatus.NOT_ACCEPTABLE);

                    }
                }catch (Exception e) {
                    return this.onError(exchange, "tokenExpired", HttpStatus.UNAUTHORIZED);
                }
                try {
                    jwtUtil.validateToken(token);
                    this.getFromTokenSetInHeaders(exchange, token);

                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("un authorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }


    private Mono<Void> onError(
            ServerWebExchange exchange,
            String err,
            HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);


        return response.setComplete();
    }




    private void getFromTokenSetInHeaders(ServerWebExchange exchange, String token){
        Claims claim = jwtUtil.extractAllClaims(token);
        exchange.getRequest()
                .mutate()
                .header("franchiseId", String.valueOf(claim.getSubject()))
                .header("email", String.valueOf(claim.get("email")))
                .header("roleType", String.valueOf(claim.get("roleType")))
                .build();
    }



}