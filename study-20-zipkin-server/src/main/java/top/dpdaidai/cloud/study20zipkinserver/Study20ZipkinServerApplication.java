package top.dpdaidai.cloud.study20zipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
//@EnableZipkinServer
@EnableZipkinStreamServer
public class Study20ZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Study20ZipkinServerApplication.class, args);
    }

}
