package jp.sample.resttemplate;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import jp.sample.resttemplate.model.Product;
import jp.sample.resttemplate.model.ProductDto;

public class Main {
    
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        
        List<String> list = new ArrayList<>();
        list.add("1");
        Product product = new Product(1111,2222,"test",1000,"happy happy", list);
        ProductDto dto = new ProductDto();
        
        BeanUtils.copyProperties(product, dto);
        
        logger.info("=============================");
        logger.info("product = {}", product.toString());
        logger.info("dto = {}", dto.toString());
        
        product.add("bb");
        
        logger.info("=============================");
        logger.info("product = {}", product.toString());
        logger.info("dto = {}", dto.toString());
          

    }

}
