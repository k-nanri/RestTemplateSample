package jp.sample.resttemplate.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    
    /**
     * 商品コード
     */
    @JsonProperty("productCode")
    private int productCode = 0;
    
    /**
     * 仕入れ先コード
     */
    @JsonProperty("supplierCode")
    private int supplierCode = 0;
    
    /**
     * 商品名
     */
    @JsonProperty("productName")
    private String name = null;
    
    /**
     * 価格
     */
    @JsonProperty("productPrice")
    private int price = 0;
    
    /**
     * メーカー
     */
    @JsonProperty("maker")
    private String maker = null;

}
