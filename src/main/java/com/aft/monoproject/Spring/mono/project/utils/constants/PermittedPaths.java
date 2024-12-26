package com.aft.monoproject.Spring.mono.project.utils.constants;

import java.util.Set;

public class PermittedPaths {
    public static final Set<String> PERMITTED_PATHS = Set.of(

            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    );

}