package tw.com.cha102.order.model.entity;

import lombok.Getter;
import tw.com.cha102.core.vo.Core;
import tw.com.cha102.product.model.entity.CategoryVO;
import tw.com.cha102.product.model.entity.ProductVO;

import javax.persistence.*;

@Entity
@Table(name = "order_detail",catalog ="cha102g4_test")
public class OrderDetailVO extends Core {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_DETAIL_ID")
    private Integer orderDetailId;

    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "PRODUCT_PRICE")
    private Integer productPrice;

    private Integer quantity;

    @Getter
    @Column(name = "PRODUCT_TOTAL_PRICE")
    private Integer productTotalPrice;

    @ManyToOne // 指定多对一关系
    @JoinColumn(name = "PRODUCT_ID",insertable=false ,updatable = false) // 指定外键列
    private ProductVO productVO;

    @ManyToOne
    @JoinColumn(name="ORDER_ID",insertable = false,updatable = false)
    private OrderVO orderVO;

    public Integer getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductVO(ProductVO productVO) {
        this.productVO = productVO;
    }

    public ProductVO getProductVO() {
        return productVO;
    }

    public OrderDetailVO(){};

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalAmount() {
        return productTotalPrice;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.productTotalPrice = totalAmount;
    }

    public OrderDetailVO(Integer orderId, Integer productId, Integer productPrice, Integer quantity, Integer productTotalPrice,ProductVO productVO,OrderVO orderVO) {
        this.orderId = orderId;
        this.productId = productId;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.productTotalPrice = productTotalPrice;
        this.productVO=productVO;
        this.orderVO=orderVO;

    }
}
