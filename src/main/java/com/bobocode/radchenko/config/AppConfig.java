package com.bobocode.radchenko.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class AppConfig {

    @Value("${spring.profiles.active}")
    private String profile;
}
