package tw.com.cha102.cart.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import tw.com.cha102.product.model.entity.ProductVO;

import java.io.Serializable;

@Data
public class CartItem implements Serializable {
    private String productJson;
    private Integer quantity;


    public CartItem(ProductVO product, Integer quantity) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.productJson = objectMapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.quantity = quantity;
    }

    public ProductVO getProduct() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(productJson, ProductVO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
