package com.saintrivers.devcli.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DockerServiceImpl implements DockerService {

    private void copyFile(String source, String target){
        Path sourceFile = Path.of(source);
        Path targetFile = Paths.get(target);

        try {
            Files.copy(sourceFile, targetFile);
        } catch (IOException e) {
            System.out.println("unable to copy file data");
        }
    }

    @Override
    public void generateDockerfile(String application) {
        this.copyFile("src/main/resources/Dockerfile", "Dockerfile");
    }

    @Override
    public void generateDockerCompose(String application) {
        this.copyFile("src/main/resources/docker-compose.yml", "docker-compose.yml");
    }
}
