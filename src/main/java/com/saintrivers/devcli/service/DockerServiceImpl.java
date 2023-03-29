package com.saintrivers.devcli.service;

import com.saintrivers.devcli.resource.FileResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class DockerServiceImpl implements DockerService {

    private final FileResource.DockerCompose composeResource;

    private void copyFile(String target){
        Path targetFile = Paths.get(target);

        try {
            Files.write(targetFile, composeResource.postgres().getBytes());
        } catch (IOException e) {
            System.out.println("unable to copy file data");
        }
    }

    @Override
    public void generateDockerfile(String application) {
        this.copyFile("Dockerfile");
    }

    @Override
    public void generateDockerCompose(String application) {
        this.copyFile("docker-compose.yml");
    }
}
