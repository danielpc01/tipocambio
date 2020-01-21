package com.challenge.tipocambio.expose;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.tipocambio.commons.constants.EndpointConstant;

@RestController
@RequestMapping(EndpointConstant.API_BASE)
@PropertySource("file:gradle.properties")
public class HealthCheckRestController {

    @Value("${currentVersion}")
    private String currentVersion;

    @GetMapping("health-check")
    @ResponseBody
    public Map<String,String> healthCheck() {
        Map<String,String> versionMap = new HashMap<>();
        versionMap.put("version",currentVersion);
        return versionMap;
    }
}
