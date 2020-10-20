package com.yffd.jecap.admin.sys;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SysApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SysApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}

