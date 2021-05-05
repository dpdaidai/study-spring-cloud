package top.dpdaidai.cloud.study20zipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class Study20ZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Study20ZipkinServerApplication.class, args);
    }

}
