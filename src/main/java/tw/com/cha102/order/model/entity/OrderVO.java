package tw.com.cha102.order.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Timestamp;
@Entity
public class OrderVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Integer orderId;
    @Column(name = "MEMBER_ID")
    private Integer memberId;
    @Column(name = "ORDER_DATE", insertable = false)
    private Timestamp orderDate;
    @Column(name = "RECIPIENT_NAME")
    private String recipientName;
    @Column(name = "TOTAL_PRICE")
    private Integer totalPrice;
    @Column(name = "POINT_APPLIED")
    private Integer pointApplied;
    @Column(name = "ACTUAL_AMOUNT")
    private Integer actualAmount;
    @Column(name = "BACK_POINTS")
    private Integer backPoints;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "TELPHONE_NUMBER")
    private String telphoneNumber;
    @Column(name = "SHIPPinG_ADDRESS")
    private String shippingAddress;
    @Column(name = "ORDER_STATUS" ,insertable = false)
    private Integer orderStatus;
    private String note;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getPointApplied() {
        return pointApplied;
    }

    public void setPointApplied(Integer pointApplied) {
        this.pointApplied = pointApplied;
    }

    public Integer getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Integer actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getBackPoints() {
        return backPoints;
    }

    public void setBackPoints(Integer backPoints) {
        this.backPoints = backPoints;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTelphoneNumber() {
        return telphoneNumber;
    }

    public void setTelphoneNumber(String telphoneNumber) {
        this.telphoneNumber = telphoneNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OrderVO(Integer memberId, String recipientName, Integer totalPrice, Integer pointApplied, Integer actualAmount, Integer backPoints, String phoneNumber, String telphoneNumber, String shippingAddress, String note) {
        this.memberId = memberId;
        this.recipientName = recipientName;
        this.totalPrice = totalPrice;
        this.pointApplied = pointApplied;
        this.actualAmount = actualAmount;
        this.backPoints = backPoints;
        this.phoneNumber = phoneNumber;
        this.telphoneNumber = telphoneNumber;
        this.shippingAddress = shippingAddress;
        this.note = note;


    }
}
