package jp.sample.resttemplate.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
    void testGetProducts1() {
        
        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(this.restTemplate).build();
        
        Resource responseBody = new ClassPathResource("test.json");
        //mockServer.expect(requestTo("http://localhost/external"))
        //            .andRespond(withSuccess("{\"id\":\"123\", \"title\":\"タイトル\", \"finished\":false}", MediaType.APPLICATION_JSON));
        mockServer.expect(requestTo("http://localhost/products"))
                    .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));
        
        repo.getProducts();
        
    }
    
    @Test
    @DisplayName("JSONファイルからレスポンスのモックを生成")
    void testGetProducts12() {
        
        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(this.restTemplate).build();
        
        Resource responseBody = new ClassPathResource("ResponsePattern1.json");
        //mockServer.expect(requestTo("http://localhost/external"))
        //            .andRespond(withSuccess("{\"id\":\"123\", \"title\":\"タイトル\", \"finished\":false}", MediaType.APPLICATION_JSON));
        mockServer.expect(requestTo("http://localhost/products"))
                    .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));
        
        repo.getProducts();
        
    }
}
