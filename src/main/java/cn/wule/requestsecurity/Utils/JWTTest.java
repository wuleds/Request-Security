package cn.wule.requestsecurity.Utils;
//汉江师范学院 数计学院 吴乐创建于2023/11/17 11:45

import cn.wule.requestsecurity.vo.JwtUserInfo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wule
 */
@Slf4j
@Component
public class JWTTest
{
    @Value("${jwt.secretKey}")
    private String secretKey;

    public String createJWT(JwtUserInfo userInfo, Date issDate, Date expDate)
    {
        Map<String,Object> headerClaims = new HashMap<>();
        headerClaims.put("alg","HS256");
        headerClaims.put("typ","JWT");
        JWTCreator.Builder builder = JWT.create();
        builder.withHeader(headerClaims)
                //签名是由谁生成 例如 服务器
                .withIssuer("wule")
                //生成签名的时间
                .withIssuedAt(issDate)
                //签名过期的时间
                .withExpiresAt(expDate)
                //自定义
                .withClaim("userId",userInfo.getUseId())
                .withClaim("userName",userInfo.getUserName())
                .withClaim("authList",userInfo.getAuthList());

        for (Map.Entry<String, String> info:userInfo.getUserInfo().entrySet())
        {
            builder.withClaim(info.getKey(),info.getValue());
        }
        String jwt = builder.sign(Algorithm.HMAC256(secretKey));
        log.info("jwt:{}",jwt);
        return jwt;
    }

    public boolean verifyJWT(String jwt)
    {
        try{
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
            String userId = decodedJWT.getClaim("userId").asString();
            String userName = decodedJWT.getClaim("userName").asString();
            List<String> authList = decodedJWT.getClaim("authList").asList(String.class);
            return true;
        }catch (Exception e){
            log.error("jwt verify error:{}",e.getMessage());
        }
        return false;
    }
}