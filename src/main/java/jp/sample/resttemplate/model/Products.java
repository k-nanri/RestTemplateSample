package jp.sample.resttemplate.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class Products {
    
    /**
     * 商品コード
     */
    @JsonProperty("products")
    private List<Product> products = new ArrayList<>();

}
