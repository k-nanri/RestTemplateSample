package jp.sample.resttemplate.repository;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RepositoryImpl implements Repository {
    
    private Logger logger = LoggerFactory.getLogger(RepositoryImpl.class);

    private RestTemplate restTemplate;
    
    public RepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public void get() {
        Map response = this.restTemplate.getForObject("http://localhost/external", Map.class);
        logger.info(response.toString());
        logger.info("id = {}", response.get("id"));
        logger.info("title = {}", response.get("title"));
        logger.info("finished = {}", response.get("finished"));
        
    }
}
