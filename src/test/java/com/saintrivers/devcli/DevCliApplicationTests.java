package com.saintrivers.devcli;

import com.saintrivers.devcli.resource.Docker;
import com.saintrivers.devcli.resource.Container;
import com.saintrivers.devcli.resource.Variables;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DevCliApplicationTests {

    @Autowired
    private Docker docker;

    @Test
    void shouldMapContainerObjectToComposeService() {
        var container = new Container(
                "postgres",
                "14.4-alpine",
                "local-database",
                "local-database", "",
                Variables.using("postgres", "local", "postgres", "defualt")
        );
        var result = docker.containerToComposeService(container);
        System.out.println(result);
    }

    @Test
    void shouldMapContainerObjectToComposeFile() {
        var container = new Container(
                "postgres",
                "14.4-alpine",
                "local-database",
                "local-database", "",
                Variables.using("postgres", "local", "postgres", "defualt")
        );
        var container2 = new Container(
                "postgres",
                "14.4-alpine",
                "article-database",
                "article-database", "",
                Variables.using("postgres", "local", "postgres", "defualt")
        );
        var result = docker.compose(List.of(container, container2));
        System.out.println(result);
    }
}
