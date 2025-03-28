package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        logger.info("\n----------------------------------------------------------\n\t" +
                "本地访问地址 : \thttp://localhost:8080" + "/\n\t" +
                "在线文档地址 : \thttp://localhost:8080" + "/doc.html\n" +
                "----------------------------------------------------------");
    }
}
