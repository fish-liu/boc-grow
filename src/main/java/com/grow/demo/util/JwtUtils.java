package com.grow.demo.util;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Jwt 工具类，用于验证登录，替代session
 * @author liuxw
 * @date 2019/10/30
 * @since 1.0
 */
public class JwtUtils {

    public static final String JWT_ID = UUID.randomUUID().toString();

    /**
     * 加密密文
     */
    public static final String JWT_SECRET = "woyebuzhidaoxiediansha";

    /**
     *  millisecond  一小时5分钟
     */
    private static long JWT_TTL = 65*60*1000;

    private static long JWT_TT = 60*60*1000;

    private static long JWT_TL = 5*60*1000;

    public static String issuer = "looker";

    /**
     * 创建token(jwt)
     *
     * // 下面就是在为payload添加各种标准声明和私有声明了
     *  JwtBuilder builder = Jwts.builder() // 这里其实就是new一个JwtBuilder，设置jwt的body
     *   .setClaims(claims)          // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
     *   .setId(id)                  // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
     *   .setIssuedAt(now)           // iat: jwt的签发时间
     *   .setIssuer(issuer)          // issuer：jwt签发人
     *   .setSubject(subject)        // subSubject：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
     *   .signWith(signatureAlgorithm, key); // 设置签名使用的签名算法和签名使用的秘钥
     *
     * @param subject
     * @return
     * @throws Exception
     */
    private static String createToken(String id,long nowMillis, String subject){

        // 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成JWT的时间
        //long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 签名秘钥
        SecretKey key = generalKey();

        JwtBuilder builder = Jwts.builder()
                //.setClaims(claims)
                .setId(id + "_" + nowMillis)
                .setIssuedAt(now)
                .setIssuer(issuer)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);

        // 设置过期时间
        long expMillis = nowMillis + JWT_TTL;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);

        return builder.compact();
    }


    /**
     * 创建token(jwt)
     *
     * @param subject
     * @return
     * @throws Exception
     */
    public static String createToken(String id, String subject){
        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        return createToken(id,nowMillis,subject);
    }

    /**
     * 根据原token产生一个新的token
     * 根据第一次生产token的时间，加上 60分钟，当做新的token的开始时间
     *
     * token的有效时间为65分钟，在60到65之间有请求的话，产生新的token，并返回前端，前端更新token
     *
     * token的作用时间 ：  9点登录
     * 第一次：  9:00 -- 10:05     （在10:00 - 10:05 之前有请求的时候，更新token）
     * 第二次：  10:00 -- 11:05    （在11:00 - 11:05 之前有请求的时候，更新token）
     * 第三次：  11:00 -- 12:05
     * @param token
     * @return
     */
    public static String refreshToken(String token){
        Claims claims = getClaimFromToken(token);

        String subject = claims.getSubject();

        long d = claims.getIssuedAt().getTime();
        long nowMillis = d + JWT_TT ;

        String id = claims.getId().split("_")[0];

        return createToken(id,nowMillis,subject);
    }

    /**
     * 检查当前token是否还能继续使用
     * true：可以  false：不建议
     * @param token
     * @return
     */
    public static CheckResult checkToken(String token){
        Claims claims = getClaimFromToken(token);
        long expireTime = claims.getExpiration().getTime();

        long now = System.currentTimeMillis();
        // 过期
        if(now > expireTime){
            return CheckResult.Expired;
        }

        long x = expireTime - now;
        // 需要生成一个新的token
        if( x <= JWT_TL ){
            return CheckResult.Refresh;
        }

        return CheckResult.Normal;
    }


    /**
     * <pre>
     *  验证token是否失效
     *  true:过期   false:没过期
     * </pre>
     */
    public static Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }

    /**
     * 获取用户从token中
     */
    public static String getSubjectFromToken(String token) {
        return getClaimFromToken(token).getSubject();
    }


    public static String getValueFromTokenBykey(String token,String key){
        String subject = getClaimFromToken(token).getSubject();
        Map<String,Object> m = JSON.parseObject(subject, Map.class);

        return m.getOrDefault(key,"").toString();
    }

    /**
     * 获取token失效时间
     */
    public static Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token).getExpiration();
    }

    /**
     * 解密token
     * 获取jwt的payload部分
     *
     * Jwts.parser()  //得到DefaultJwtParser
     * .setSigningKey(secretKey)  //设置签名的秘钥
     * .parseClaimsJws(token.replace("jwt_", "")) //设置需要解析的jwt
     * .getBody();
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Claims getClaimFromToken(String token){
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();

        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token).getBody();
        return claims;
    }


    /**
     * 签名私钥
     * 由字符串生成加密key
     *
     * @return
     */
    private static SecretKey generalKey() {

        // 本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(JWT_SECRET);

        // 根据给定的字节数组使用AES加密算法构造一个密钥,使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

        return key;
    }


    public static enum CheckResult{
        /**
         * 过期
         */
        Expired,
        /**
         * 没过期，但是需要刷新token
         */
        Refresh,
        /**
         * 没过期，也不需要刷新token
         */
        Normal;

    }


    public static void main(String[] args) {

        /*User user = null;//new User("tingfeng", "bulingbuling", "1056856191");
        String subject = JSON.toJSONString(user);

        try {

            String jwt = JwtUtils.createToken(JWT_ID, "Anson", subject, JWT_TTL);
            System.out.println("JWT：" + jwt);

            System.out.println("\n解密\n");

            Claims c = JwtUtils.getClaimFromToken(jwt);
            System.out.println(c.getId());
            System.out.println(c.getIssuedAt());
            System.out.println(c.getSubject());
            System.out.println(c.getIssuer());
            System.out.println(c.get("uid", String.class));

        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }



}
