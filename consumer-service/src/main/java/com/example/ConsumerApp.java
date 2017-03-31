package com.example;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ConsumerApp {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
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
        Application application = eurekaClient.getApplication(serviceName);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String uri = instanceInfo.getHomePageUrl() + "name";
        return restTemplate.getForObject(uri, String.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }

}
