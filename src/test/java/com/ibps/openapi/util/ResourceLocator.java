package com.ibps.openapi.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class ResourceLocator {
    @Value("${server.contextPath}")
    private String contextRoot;

    private String port;

//    public URI getEncryptionLocation(String... pathParams) {
//        return getLocation("/v1/encryptionkey", pathParams);
//    }

    public URI getUserApiLocation(String... pathParams) {
        return getLocation("/v1/users", pathParams);
    }
//    public URI getProjectApiLocation(String... pathParams) {
//        return getLocation("/v1/project", pathParams);
//    }
//
//    public URI getMasterpassCreateUserApiLocation(String... pathParams) {
//        return getLocation("/v1/masterpass/createuser", pathParams);
//    }
//    public URI getMasterpassSingingKey(String... pathParams) {
//        return getLocation("/v1/masterpass/key", pathParams);
//    }

    private URI getLocation(String resourcePath, String... pathParams) {
        pathParams = Arrays.stream(pathParams)
                .map(p -> p.startsWith("/") || p.endsWith("/") ? p.substring(1) : p)
                .collect(Collectors.toList()).toArray(new String[]{});

        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(port)
                .path(contextRoot)
                .path(resourcePath)
                .pathSegment(pathParams)
                .build().toUri();
    }

    public void setPort(String port) {
        this.port=port;
    }
}
