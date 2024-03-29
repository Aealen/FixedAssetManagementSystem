package tech.aowu.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tech.aowu.aspects.UserOpreateLogAnnotation;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtil {

    //有效期为
    public static final Long JWT_TTL = 3 * 60 * 60 *1000L;// 3 * 60 * 60 *1000L  3个小时
    //设置秘钥明文
    public static final String JWT_KEY = "Aealen";

    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }

    @UserOpreateLogAnnotation
    public static String testAnno(String str){
        System.out.println("its sout in func: "+ str);
        return "its return string";
    }

    @UserOpreateLogAnnotation
    public static void main(String[] args) throws Exception {
//        String jwt = createJWT("2123");
//        String jwt = createJWT("1234");
//        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxZTU5ZGJiMzljNDI0ZmY2OGRiNzdiMzY0NzFhODY4NyIsInN1YiI6IntcInVpZFwiOlwiMTNcIixcInJpZFwiOlwiM1wiLFwidXNlcm5hbWVcIjpcIkVsb2VcIn0iLCJpc3MiOiJBZWFsZW4iLCJpYXQiOjE2NzYzNTA2ODAsImV4cCI6MTY3NjM1NDI4MH0.E_ZKMJUMv7UvJCZ79miDezpc8tYjZyo7RP0EQ14C88k";
//        System.out.println(jwt);
//
//        Claims claims = parseJWT(jwt);
//        String subject = claims.getSubject();
//        System.out.println(subject);
        System.out.println(testAnno("testString"));

//        System.out.println(claims);
    }


    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("Aealen")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }



    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }


}
