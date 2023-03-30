package com.saintrivers.devcli.resource;

import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Docker {

    public String containerToComposeService(Container container) {
        if (container.image().equals("postgres")) {
            var service = """
                    **service-name**:
                      image: **image**:**version**
                      restart: unless-stopped
                      container_name: local-database
                      environment:**variables**
                      ports:
                        - '5432:5432'
                      volumes:
                        - local_data:/var/lib/postgresql/data
                    """;

            service = FileResource.Utils.replace(service, "**service-name**", container.serviceName());
            service = FileResource.Utils.replace(service, "**image**", container.image());
            service = FileResource.Utils.replace(service, "**version**", container.version());
            service = FileResource.Utils.replace(service, "**variables**", renderVariables(container.environmentVariables()));

            return service;
        }

        throw new MissingRequiredPropertiesException();
    }

    String renderVariables(Variables variables) {
        StringBuilder result = new StringBuilder();
        var entrySet = variables.getVariables().entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            result.append("\n\s\s")
                    .append("- ").append(entry.getKey()).append("=").append(entry.getValue());
        }
        return result.toString();
    }

    public String compose(List<Container> services) {
        var file = """
                version: '3.8'
                                
                services:
                  **service**
                volumes:
                  **volume**
                """;

        file = FileResource.Utils.replace(file, "**service**", combineServices(services));
        return file;
    }

    String combineServices(List<Container> services) {
        StringBuilder result = new StringBuilder();
        services.forEach(service -> {
            var data = containerToComposeService(service);
            result.append(data).append("\n");
        });
        return result.toString();
    }
}
