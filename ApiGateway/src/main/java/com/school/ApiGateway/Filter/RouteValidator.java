package com.school.ApiGateway.Filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

   public static final List<String> openApiEndpoints = List.of(
            "/login/getotp",
            "/login/all",
            "admin/superadmin/register",
            "/eureka/**",
            "/admin/**",
            "/fee/**",
            "/events/**",
            "/sch/addSchool",
            "/sch/getAllSchool",
            "/sch/getById",
            "/sch/editById",
            "/sch/deleteById",
            "/sch/addPlanInSchool",
            "/sch/addSpSerBySchId",
            "/sch/getSpFeaBySchId",
            "/plan/addPlans",
            "/plan/getAllPlan",
            "/plan/getById",
            "/plan/editById",
            "/plan/deleteById",
            "/fea/addFeaByPlanId",
            "/fea/getAllFeatures",
            "/fea/getAllFeaByPlanId",
            "/fea/getByFeaId",
            "/fea/editByfeaId",
            "/fea/deleteById",
            "per/addPermission",
            "per/getAllPermission",
            "per/getAllPerByFeaId",
            "per/getById",
            "per/editById",
            "per/deleteById",
            "/subs/getAllSubs",
            "/subs/editBySubsId",
           "/swagger-ui/index.html"


    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
