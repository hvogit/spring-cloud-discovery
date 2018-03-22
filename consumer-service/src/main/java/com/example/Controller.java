package com.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
    private final Log log = LogFactory.getLog(getClass());
    private final RestTemplate restTemplate;

    public Controller(@LoadBalanced RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/test")
    public String test() {
        String p1 = getName("PPL");
        String p2 = getName("PPL");
        String v = getName("VERB");
        return String.format("%s %s %s", p1, v, p2);
    }

    private String getName(String serviceName) {
        String uri = "//" + serviceName + "/name";
        log.info("uri: " + uri);
        return restTemplate.getForObject(uri, String.class);
    }
}
