package cn.xiaomotou.common.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
/**
 * @author: ykq
 * @date: 2023/2/18 15:27
 * @Description: jwt工具类
 */
@Component
public class JwtHmacUtil {

    private static final String REDIS_KEY = "TOKEN:USER_ID:";

    @Autowired
    private RedisUtils redisUtils;

    //定义默认有效期为10分钟 单位：毫秒
    public Long JWT_Default_Expires = 60 * 60 * 1000L;

    // 设置HS512密钥
    public String JWT_KEY = "tNTaMC1B0Kzdl1q0LKJy7MooRbuwaebz+wSb498OoxoUKL8Yib1P2psiudfeefaefawefafaeaffefafaeafeafeafaafea";

    //签发者
    public String Issuer = "xiaomotou";

    // 签名算法
    public SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;


//    public JwtHmacUtil(String key,Integer timeout){
//        this.JWT_KEY = key;
//        this.JWT_Default_Expires = timeout*60 * 60 * 1000L;
//
//    }

    /**
     * 创建一个token
     */
    public String createHmacJWT(String UserId, String subject, Long exprieTime) {
        //创建我们将要使用的JWT签名算法对（token）令牌进行签名
        //获取当前时间并转换为date对象
        long nowMillis = System.currentTimeMillis();

        //签发时间
        Date now = new Date(nowMillis);

        //exprieTime判断用户是否需要设置token有效时间
        if (exprieTime == null) {
            //如果为空，将默认有效期赋值给exprieTime
            exprieTime = JWT_Default_Expires;
        }

        //结束时间 = 当前时间 + 设定的有效期时间
        Long expiresTime = nowMillis + exprieTime;
        //转换为date数据类型
        Date date = new Date(expiresTime);

        JwtBuilder builder = Jwts.builder()
                .setId(UserId)
                .setSubject(subject) //主题，可以是json数据
                .setIssuer(Issuer)   //签发者
                .setIssuedAt(now)   //签发时间
                .signWith(generalKey(), signatureAlgorithm) //使用ES256对称加密算法签名
                .setExpiration(date);   //设置过期时间

        //compact:规则构建JWT并将其序列化为紧凑的URL安全字符串
        String result = builder.compact();

        redisUtils.set(REDIS_KEY+UserId,result,JWT_Default_Expires);

        return result;
    }

    /**
     * 创建一个token
     */
//    public String createHmacJWT(Map<String, Object> claimsMap, Long exprieTime) {
//        //创建我们将要使用的JWT签名算法对（token）令牌进行签名
//        //获取当前时间并转换为date对象
//        long nowMillis = System.currentTimeMillis();
//
//        //签发时间
//        Date now = new Date(nowMillis);
//
//        //exprieTime判断用户是否需要设置token有效时间
//        if (exprieTime == null) {
//            //如果为空，将默认有效期赋值给exprieTime
//            exprieTime = JWT_Default_Expires;
//        }
//
//        //结束时间 = 当前时间 + 设定的有效期时间
//        Long expiresTime = nowMillis + exprieTime;
//        //转换为date数据类型
//        Date date = new Date(expiresTime);
//
//        JwtBuilder builder = Jwts.builder()
////                .setHeaderParam(JWTHeader.TYPE, "JWT")    //一下两行就是Header
////                .setHeaderParam(JWTHeader.ALGORITHM, signatureAlgorithm.getValue())
//                .setClaims(claimsMap)
//                .setIssuer(Issuer)   //签发者
//                .setIssuedAt(now)   //签发时间
//                .signWith(generalKey(), signatureAlgorithm) //使用ES256对称加密算法签名
//                .setExpiration(date);   //设置过期时间
//
//        //compact:规则构建JWT并将其序列化为紧凑的URL安全字符串
//        return builder.compact();
//    }

    /**
     * 加密明文密钥
     *
     * @return
     */
    public SecretKey generalKey() {
        //调用base64中的getDecoder方法获取解码器，调用解码器中的decode方法将明文密钥进行编码
        byte[] decodeKey = Base64.getDecoder().decode(JWT_KEY);
        SecretKey secretKey = Keys.hmacShaKeyFor(decodeKey);
        //返回加密后的密钥
        return secretKey;
    }

    /**
     * 解析jwt
     *
     * @param jwt
     * @return
     */
    //此处当解析不出的时候会抛出异常
    public Claims parseHmacJWT(String jwt) {
        return Jwts.parserBuilder()//获取jwts的解析器
                .setSigningKey(generalKey())    //设置加密后的密钥进行比对
                .build()
                .parseClaimsJws(jwt)// (jwt)    //解析传入的jwt字符串
                .getBody();     // 拿到claims对象返回
    }

    public static void main(String[] args) {

        // HS256、HS384、HS512生成秘钥的方式
/*        SecretKey secretKey = io.jsonwebtoken.security.Keys.secretKeyFor(signatureAlgorithm);
        String secretKeyStr = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println(secretKeyStr);
        Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKeyStr));*/
//        String key = "tNTaMC1B0Kzdl1q0LKJy7MooRbuwaebz+wSb498OoxoUKL8Yib1P2psiudfeefaefawefafaeaffefafaeafeafeafaafea";
//        JwtHmacUtil jwtHmacUtil = new JwtHmacUtil( null,key,24L);
//        String jwtStr = jwtHmacUtil.createHmacJWT("123456", "123456", null);
//        Claims claims = jwtHmacUtil.parseHmacJWT(jwtStr);
//        String subject = claims.getSubject();
//        System.out.println(jwtStr + " : " + subject);
//
//        Map<String, Object> claimMap = new HashMap<>();
//        claimMap.put("memId", "123456");
//        claimMap.put(Claims.SUBJECT, "sub123");
//        String jwtStr2 =jwtHmacUtil. createHmacJWT(claimMap, null);
//        Claims claims2 = jwtHmacUtil.parseHmacJWT(jwtStr2);
//        String subject2 = claims2.getSubject();
//        System.out.println(jwtStr2 + " : " + subject2);
    }


    public String getAccessToken(String userId) {
        return (String) redisUtils.get(REDIS_KEY+userId);
    }

    public void addAccessToken(String userId,String accessToken) {
        redisUtils.del(REDIS_KEY+userId);
        redisUtils.set(REDIS_KEY+userId,accessToken,JWT_Default_Expires);
    }

    public void removeAccessToken(String userId) {
        redisUtils.del(REDIS_KEY+userId);
    }



}
