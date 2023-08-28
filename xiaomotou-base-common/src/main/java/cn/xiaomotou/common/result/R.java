package cn.xiaomotou.common.result;

import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import java.io.Serializable;
import java.util.Optional;

/**
 * 返回数据
 *
 * @author niko
 */
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * * 状态码
     */
    private int code;

    /**
     * 是否成功*
     */
    private boolean success;
    /**
     * 承载数据*
     */
    private T data;
    /**
     * 返回消息*
     */
    private String msg;

    private R(IResultCode resultCode) {
        this(resultCode, null, resultCode.getMessage());
    }

    private R(IResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private R(ResultCode resultCode, T data) {
        this(resultCode.getCode(), data, resultCode.getMessage());
    }

    private R(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private R(int code, String msg){
        this(code, null, msg);
    }

    private R(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = ResultCode.SUCCESS.code == code;
    }

    public static boolean isSuccess(@Nullable R<?> result) {
        return (Boolean) Optional.ofNullable(result).map((x) -> {
            return ObjectUtils.nullSafeEquals(ResultCode.SUCCESS.code, x.code);
        }).orElse(Boolean.FALSE);
    }

    public static boolean isNotSuccess(@Nullable R<?> result) {
        return !isSuccess(result);
    }

    public static <T> R<T> data(T data) {
        return data(data, "操作成功");
    }

    public static <T> R<T> data(T data, String msg) {
        return data(200, data, msg);
    }

    public static <T> R<T> data(int code, T data, String msg) {
        return new R(code, data, data == null ? "暂无承载数据" : msg);
    }

    public static <T> R<T> success(String msg) {
        return new R(ResultCode.SUCCESS, msg);
    }

    public static <T> R<T> success(IResultCode resultCode) {
        return new R(resultCode);
    }

    public static <T> R<T> success(IResultCode resultCode, String msg) {
        return new R(resultCode, msg);
    }


    public static <T> R<T> error() {
        return new R(ResultCode.INTERNAL_SERVER_ERROR, ResultCode.INTERNAL_SERVER_ERROR);
    }
    public static <T> R<T> error(String msg) {
        return new R(ResultCode.FAILURE, msg);
    }

    public static <T> R<T> error(int code, String msg) {
        return new R(code, (Object)null, msg);
    }

    public static <T> R<T> error(IResultCode resultCode) {
        return new R(resultCode);
    }

    public static <T> R<T> error(IResultCode resultCode, String msg) {
        return new R(resultCode, msg);
    }

    public static <T> R<T> status(boolean flag) {
        return flag ? success("操作成功") : error("操作失败");
    }

    public int getCode() {
        return this.code;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public String toString() {
        return "R(code=" + this.getCode() + ", success=" + this.isSuccess() + ", data=" + this.getData() + ", msg=" + this.getMsg() + ")";
    }

    public R() {
    }
}