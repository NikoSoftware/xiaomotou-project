package cn.xiaomotou.common.log;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


public class CloudApiLog implements Serializable {

    @JsonFormat(pattern = "yyyy.MM.dd",timezone = "GMT+8")
    private Date createTime;
    private String userName;
    private String springApplicationName;
    private String operationModular;
    private String operationMethod;
    private String operationDes;
    private String requestURL;
    private String requestIp;
    private String requestMethod;
    private String requestSignature;
    private String requestArgs;
    private String result;
    private Long requestTime;

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSpringApplicationName(String springApplicationName) {
        this.springApplicationName = springApplicationName;
    }

    public void setOperationModular(String operationModular) {
        this.operationModular = operationModular;
    }

    public void setOperationMethod(String operationMethod) {
        this.operationMethod = operationMethod;
    }

    public void setOperationDes(String operationDes) {
        this.operationDes = operationDes;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public void setRequestSignature(String requestSignature) {
        this.requestSignature = requestSignature;
    }

    public void setRequestArgs(String requestArgs) {
        this.requestArgs = requestArgs;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getUserName() {
        return userName;
    }

    public String getSpringApplicationName() {
        return springApplicationName;
    }

    public String getOperationModular() {
        return operationModular;
    }

    public String getOperationMethod() {
        return operationMethod;
    }

    public String getOperationDes() {
        return operationDes;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestSignature() {
        return requestSignature;
    }

    public String getRequestArgs() {
        return requestArgs;
    }

    public String getResult() {
        return result;
    }

    public Long getRequestTime() {
        return requestTime;
    }
}
