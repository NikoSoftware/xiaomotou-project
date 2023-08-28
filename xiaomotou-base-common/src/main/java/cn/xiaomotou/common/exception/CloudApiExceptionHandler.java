package cn.xiaomotou.common.exception;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import cn.xiaomotou.common.result.R;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;

/**
 * 异常处理器
 *
 * @Author scott
 * @Date 2019
 */
@RestControllerAdvice
public class CloudApiExceptionHandler {

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(CloudApiException.class)
    public R handleRRException(CloudApiException e) {
        return R.error();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public R handlerNoFoundException(Exception e) {
        return R.error(404, "路径不存在，请检查路径是否正确");
    }


    /**
     * 全局异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        JSONObject map = new JSONObject();
//        map.put("类名:", stackTraceElement.getClassName());
        map.put("class:", stackTraceElement.getFileName());
        map.put("error:", e.toString());
        map.put("methodName:", stackTraceElement.getMethodName());
        map.put("errorLine:", stackTraceElement.getLineNumber());

        Map<String, String> m = System.getenv();
        String userName = m.get("USERNAME");// 获取用户名
        System.out.println(userName);
        if (userName == null) {
             userName = "Linux";
        }

        //发送钉钉/邮箱
        return R.error(JSONUtils.toJSONString(map));

    }


    /**
     * 请求格式不对
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        StringBuffer sb = new StringBuffer();
        sb.append("不支持");
        sb.append(e.getMethod());
        sb.append("请求方法，");
        sb.append("支持以下");
        String[] methods = e.getSupportedMethods();
        if (methods != null) {
            for (String str : methods) {
                sb.append(str);
                sb.append("!");
            }
        }
        return R.error(405, sb.toString());
    }


    /**
     *  spring默认上传大小100MB 超出大小捕获异常MaxUploadSizeExceededException
     * @param e
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public R handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        return R.error("文件大小超出10MB限制, 请压缩或降低文件质量! ");
    }

}
