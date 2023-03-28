package com.saintrivers.devcli.service;

import java.io.IOException;

public interface DockerService {
     void generateDockerfile(String application) throws IOException;
     void generateDockerCompose(String application) throws IOException;
}
