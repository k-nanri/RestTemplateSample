package jp.sample.resttemplate.repository;

import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RepositoryImpl implements Repository {

    private RestTemplate restTemplate;
    
    public RepositoryImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    
    public void get() {
        Map response = this.restTemplate.getForObject("http://localhost/external", Map.class);
    }
}
