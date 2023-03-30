package com.saintrivers.devcli.resource;

import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;

public record Container(
        @NotBlank String image,
        @Value("latest") String version,
        @NotBlank String serviceName,
        String containerName,
        String port,
        Variables environmentVariables
) {
}
