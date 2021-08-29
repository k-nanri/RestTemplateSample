package jp.sample.resttemplate.repository;

import static org.junit.jupiter.api.Assertions.*;
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

import jp.sample.resttemplate.model.Product;
import jp.sample.resttemplate.model.Products;

@AutoConfigureMockRestServiceServer
@SpringBootTest
class RepositoryImplTest {
    
    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    Repository repo;

    @Test
    @DisplayName("文字列からレスポンスのモックを生成")
    void testGetProducts1() {
        
        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(this.restTemplate).build();
        
        mockServer.expect(requestTo("http://localhost/products"))
                    .andRespond(withSuccess("{\"products\":[ { \"productCode\": 1234, \"supplierCode\":4566, \"productName\":\"test\", \"productPrice\": 2000, \"maker\":\"happy happy\"}]}", MediaType.APPLICATION_JSON));
        
        Products actual = repo.getProducts();
        
        assertAll(
                () -> {
                    assertEquals(1, actual.getLen());
                },
                () -> {
                    Product product = actual.getProduct(0);
                    assertAll(
                            () -> assertEquals(1234, product.getProductCode()),
                            () -> assertEquals(4566, product.getSupplierCode()),
                            () -> assertEquals("test", product.getName()),
                            () -> assertEquals(2000, product.getPrice()),
                            () -> assertEquals("happy happy", product.getMaker())
                     );
                }
        );
    }
    
    @Test
    @DisplayName("JSONファイルからレスポンスのモックを生成")
    void testGetProducts2() {
        
        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(this.restTemplate).build();
        
        // src/java/resources配下のJSONファイル
        Resource responseBody = new ClassPathResource("ResponsePattern1.json");
        mockServer.expect(requestTo("http://localhost/products"))
                    .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));
        
        Products actual = repo.getProducts();
        
        assertAll(
                () -> {
                    assertEquals(1, actual.getLen());
                },
                () -> {
                    Product product = actual.getProduct(0);
                    assertAll(
                            () -> assertEquals(111111, product.getProductCode()),
                            () -> assertEquals(22222, product.getSupplierCode()),
                            () -> assertEquals("pipe", product.getName()),
                            () -> assertEquals(1000, product.getPrice()),
                            () -> assertEquals("happy hello", product.getMaker())
                     );
                }
        );
    }
    
    @Test
    @DisplayName("テストソースと同一ディレクトリのJSONファイルからレスポンスのモックを生成")
    void testGetProducts3() {
        
        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(this.restTemplate).build();
        
        // テストソースと同一ディレクトリのJSONファイル
        Resource responseBody = new ClassPathResource("ResponsePattern2.json", this.getClass());
        mockServer.expect(requestTo("http://localhost/products"))
                    .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));
        
        Products actual = repo.getProducts();
        
        assertAll(
                () -> {
                    assertEquals(2, actual.getLen());
                },
                () -> {
                    Product product = actual.getProduct(0);
                    assertAll(
                            () -> assertEquals(111111, product.getProductCode()),
                            () -> assertEquals(22222, product.getSupplierCode()),
                            () -> assertEquals("pipe", product.getName()),
                            () -> assertEquals(1000, product.getPrice()),
                            () -> assertEquals("happy hello", product.getMaker())
                     );
                },
                () -> {
                    Product product = actual.getProduct(1);
                    assertAll(
                            () -> assertEquals(222, product.getProductCode()),
                            () -> assertEquals(333, product.getSupplierCode()),
                            () -> assertEquals("pipe3", product.getName()),
                            () -> assertEquals(1500, product.getPrice()),
                            () -> assertEquals("happy hello", product.getMaker())
                     );
                }
        );
    }
}