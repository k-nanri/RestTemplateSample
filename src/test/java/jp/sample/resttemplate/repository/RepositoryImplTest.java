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
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
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
        
        Map<String, Object> response = new HashMap<>();
        response.put("id", 111);
        response.put("name",  "hoge");
        
        // 
        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(this.restTemplate).build();
        mockServer.expect(requestTo("http://localhost/external"))
                    .andRespond(withSuccess("{\"id\":\"123\", \"title\":\"タイトル\", \"finished\":false}", MediaType.APPLICATION_JSON));
        
        repo.get();
    }

}
