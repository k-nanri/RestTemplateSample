package jp.sample.resttemplate.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseCreator;
import org.springframework.web.client.RestTemplate;

@AutoConfigureMockRestServiceServer
@SpringBootTest
class RepositoryImplTest {
    
    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    Repository repo;

    @Test
    void testGet() {
        
        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(this.restTemplate).build();
        
        Resource responseBody = new ClassPathResource("test.json");
        //mockServer.expect(requestTo("http://localhost/external"))
        //            .andRespond(withSuccess("{\"id\":\"123\", \"title\":\"タイトル\", \"finished\":false}", MediaType.APPLICATION_JSON));
        mockServer.expect(requestTo("http://localhost/external"))
                    .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));
        
        repo.get();
    }
}
