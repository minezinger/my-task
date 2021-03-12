package com.example.demo.api;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("health/ping")
public class HealthResource {

	@GetMapping
    @ResponseBody
    public String ping() {
        return ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).toString();
    }

}