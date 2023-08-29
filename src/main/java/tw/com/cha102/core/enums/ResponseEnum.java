package tw.com.cha102.core.enums;

public enum ResponseEnum {

    ERROR(-1, "服務端錯誤"),

    SUCCESS(0, "成功"),

    PASSWORD_ERROR(1, "密碼錯誤"),

    USERNAME_EXIST(2, "用戶名已存在"),

    PARAM_ERROR(3, "參數錯誤"),

    EMAIL_EXIST(4, "電子郵件已存在"),

    NEED_LOGIN(10, "用戶未登錄, 請先登錄"),

    USERNAME_OR_PASSWORD_ERROR(11, "用戶名或密碼錯誤"),

    PRODUCT_OFF_SALE_OR_DELETE(12, "該商品已下架或刪除"),

    PRODUCT_NOT_EXIST(13, "商品不存在"),

    PRODUCT_STOCK_ERROR(14, "庫存不正確"),

    CART_PRODUCT_NOT_EXIST(15, "購物車裡無此商品"),

    DELETE_SHIPPING_FAIL(16, "刪除收貨地址失敗"),

    SHIPPING_NOT_EXIST(17, "收貨地址不存在"),

    CART_SELECTED_IS_EMPTY(18, "請選擇商品後下單"),

    ORDER_NOT_EXIST(19, "訂單不存在"),

    ORDER_STATUS_ERROR(20, "訂單狀態有誤"),
    ;

    Integer code;

    String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
