package cn.xiaomotou.common.http;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RestTemplateUtil
 * @Author 小坏
 * @Date 2022/12/15、17:03
 * @Version 1.0
 */
public class HttpUtil {

    //此RestTemplate 想在静态方法中使用不可 不可注入 @Autowired
    static RestTemplate restTemplate = new RestTemplate();

    /**
     * get请求
     *
     * @param url     请求地址
     * @param headers 头部信息
     * @param resp    响应结果类型
     * @return
     */
    public static Object HttpGet(String url, Map<String, String> headers, Class<?> resp) {
        HttpEntity httpEntity = getHttpEntity(headers);
        ResponseEntity<?> result = restTemplate.exchange(url, HttpMethod.GET, httpEntity, resp);
        return result.getBody();
    }


    /**
     * get请求
     *
     * @param url  请求地址
     * @param resp 响应结果类型
     * @return
     */
    public static Object HttpGet(String url, Class<?> resp) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity httpEntity = getHttpEntity(headers);
        ResponseEntity<?> result = restTemplate.exchange(url, HttpMethod.GET, httpEntity, resp);
        return result.getBody();
    }




    /**
     * get请求
     *
     * @param url     请求地址
     * @param headers 头部信息
     *                默认不传入响应类型、默认为String
     * @return
     */

    public static Object HttpGet(String url, Map<String, String> headers) {
        HttpEntity httpEntity = getHttpEntity(headers);
        ResponseEntity<?> result = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        return result.getBody();
    }


    /**
     * post 请求
     *
     * @param url     请求地址
     * @param headers 头信息
     * @param data    请求参数 JSON
     * @param resp    设置响应参数
     * @return JSONObject
     */
    public static Object HttpPost(String url, Map<String, String> headers, Object data, Class<?> resp) {
        HttpEntity httpEntity = postHttpEntity(headers, data);
        return restTemplate.postForObject(url, httpEntity, resp);
    }



    /**
     * post 请求
     * 减少设置 headers 请求头格式
     * @param url     请求地址
     * @param data    请求参数 JSON
     * @param resp    设置响应参数
     * @return JSONObject
     */
    public static Object HttpPost(String url,  Object data, Class<?> resp) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity httpEntity = postHttpEntity(headers, data);
        return restTemplate.postForObject(url, httpEntity, resp);
    }


    /**
     * post 请求
     *
     * @param url     请求地址
     * @param headers 头信息
     * @param data    请求参数 JSON
     *                默认响应参数为 JSONObject
     * @return JSONObject
     */
    public static JSONObject HttpPost(String url, Map<String, String> headers, Object data) {
        HttpEntity httpEntity = postHttpEntity(headers, data);
        JSONObject result = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        return result;
    }


    /**
     * get设置head请求封装
     *
     * @param headers
     * @Author 小坏
     * @Version 1.0
     */
    private static HttpEntity getHttpEntity(Map<String, String> headers) {
        HttpHeaders h = new HttpHeaders();
        for (Map.Entry<String, String> stringStringEntry : headers.entrySet()) {
            h.add(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
        return new HttpEntity(h);
    }


    /**
     * post设置head请求封装
     *
     * @param headers
     * @Author 小坏
     * @Version 1.0
     */
    private static HttpEntity postHttpEntity(Map<String, String> headers, Object data) {
        HttpHeaders h = new HttpHeaders();
        for (Map.Entry<String, String> stringStringEntry : headers.entrySet()) {
            h.add(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
        return new HttpEntity(data, h);
    }

}
