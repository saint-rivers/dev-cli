package com.saintrivers.devcli.resource;

import org.springframework.stereotype.Service;

public class FileResource {

    @Service
    public static class Dockerfile {

    }

    @Service
    public static class DockerCompose {

        private String cleanIndents(String input) {
            return input.replace("\n", "\n\s\s");
        }

        public String postgres() {
            var service = """
                    local-database:
                      image: postgres:14.4-alpine
                      restart: unless-stopped
                      container_name: local-database
                      environment:
                        - POSTGRES_DB=default
                        - POSTGRES_USER=postgres
                        - POSTGRES_PASSWORD=password
                      ports:
                        - '5432:5432'
                      volumes:
                        - local_data:/var/lib/postgresql/data
                    """;
            var volume = """
                    local_data:
                      driver: local
                     """;

            return this.composeFile(service, volume);
        }

        public String composeFile(String service, String volume) {
            var file = """
                    version: '3.8'
                                    
                    services:
                      **service**
                      
                    volumes:
                      **volume**
                    """;

            file = file.replace("**service**", cleanIndents(service));
            file = file.replace("**volume**", cleanIndents(volume));
            return file;
        }
    }

}
