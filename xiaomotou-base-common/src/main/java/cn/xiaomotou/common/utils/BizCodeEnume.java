package cn.xiaomotou.common.utils;

public enum BizCodeEnume {
    BD_BASE64_TYPE(200, "BASE64"),    //百度Base64 类型
    BD_URL_TYPE(200, "URL"),   //通用URL格式图片
    KS_IMG_HTTPS(200, "https"),//矿视 Base64图片类型
    KS_IMG_TYPE(200, "image_base64"),//矿视 Base64图片类型


    TO_MANY_REQUEST(10002, "请求流量过大"),
    UNKNOW_EXCEPTION(10000, "系统未知异常"),
    VAILD_EXCEPTION(10001, "参数格式校验失败"),
    SMS_EXCEPTION(10002, "验证码获取频率太高，稍后再试"),
    PRODUCT_UP_EXCEPTION(11000, "商品商家异常"),
    USER_EXIT_EXCEPTION(15001, "用户存在"),
    PHONE_EXIT_EXCEPTION(15002, "手机号存在"),
    NO_STOCK_EXCEPTION(21000, "商品库存不足"),
    BASE64_UTILS(001, "BASE64"),
    URL_UTILS(002, "URL"),
    MultiParTile_UTILS(003, "MultiParTile"),
    ALI_BUCKET(004, "alibaba-api");


    private int code;
    private String msg;

    BizCodeEnume(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
