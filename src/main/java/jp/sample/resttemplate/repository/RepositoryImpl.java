package jp.sample.resttemplate.repository;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import jp.sample.resttemplate.model.Products;

@Component
public class RepositoryImpl implements Repository {
    
    private Logger logger = LoggerFactory.getLogger(RepositoryImpl.class);

    private RestTemplate restTemplate;
    
    public RepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Products getProducts() {
        Products products = this.restTemplate.getForObject("http://localhost/products", Products.class);
        logger.info(products.toString());
        return products;
    }
}
