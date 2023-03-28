package com.saintrivers.devcli.shell;

import com.saintrivers.devcli.service.DockerService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;

@ShellComponent
@RequiredArgsConstructor
public class DockerCommand {

    private final DockerService dockerService;

    @ShellMethod(key = "gen", value = "generates docker files")
    public void gen(@ShellOption(value = {"--docker", "-d"}) String docker) throws IOException {
        switch (docker){
            case "file" -> dockerService.generateDockerfile("jre");
            case "compose" -> dockerService.generateDockerCompose("postgres");
            default -> System.out.println("no valid arguments provided");
        }
    }
}
