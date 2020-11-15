package jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author running@vip.163.com
 *
 */
public class JWT {


    /**
     * 秘钥
     */
    private static final byte[] SECRET="3d990d2276917dfac04467df11fff26d".getBytes();

    /**
     * 初始化head部分的数据为
     * {
     * 		"alg":"HS256",
     * 		"type":"JWT"
     * }
     */
    private static final JWSHeader header=new JWSHeader(JWSAlgorithm.HS256, JOSEObjectType.JWT, null, null, null, null, null, null, null, null, null, null, null);

    /**
     * 生成token，该方法只在用户登录成功后调用
     *

     * @return token字符串,若失败则返回null
     */
    public static String createToken(Map<String, Object> payload) {
        String tokenString=null;
        // 创建一个 JWS object
        JWSObject jwsObject = new JWSObject(header, new Payload(new JSONObject(payload)));
        try {
            // 将jwsObject 进行HMAC签名
            jwsObject.sign(new MACSigner(SECRET));
            //将header,payload,secret链接成序列
            tokenString=jwsObject.serialize();
        } catch (JOSEException e) {
            System.err.println("签名失败:" + e.getMessage());
            e.printStackTrace();
        }
        //链接成功就返回Token,否则null
        return tokenString;
    }



    /**
     * 校验token是否合法，返回Map集合,集合中主要包含    state状态码   data鉴权成功后从token中提取的数据
     * 该方法在过滤器中调用，每次请求API时都校验
     * @param token
     * @return  Map<String, Object>
     */
    public static Map<String, Object> validToken(String token) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            //解析Token
            JWSObject jwsObject = JWSObject.parse(token);
            Payload payload = jwsObject.getPayload();

            JWSVerifier verifier = new MACVerifier(SECRET);

            if (jwsObject.verify(verifier)) {
                JSONObject jsonOBj = payload.toJSONObject();
                // token校验成功（此时没有校验是否过期）
                resultMap.put("state", TokenState.VALID.toString());
                // 若payload包含ext字段，则校验是否过期
                if (jsonOBj.containsKey("ext")) {
                    long extTime = Long.valueOf(jsonOBj.get("ext").toString());
                    long curTime = new Date().getTime();
                    // 过期了
                    if (curTime > extTime) {
                        resultMap.clear();
                        resultMap.put("state", TokenState.EXPIRED.toString());
                    }
                }
                resultMap.put("data", jsonOBj);

            } else {
                // 校验失败
                resultMap.put("state", TokenState.INVALID.toString());
            }

        } catch (Exception e) {
            //e.printStackTrace();
            // token格式不合法导致的异常
            resultMap.clear();
            resultMap.put("state", TokenState.INVALID.toString());
        }
        return resultMap;
    }

    /**
     * 用户退出登录时，使jwt失效，并删除cookie中的jwt
     * @param token
     * @return token
     */
    public static void invalidateToken(String token){

    }

    /**
     * 根据token获取username
     * @param token
     * @return username
     */
    public static String getUsername(String token){
        String username=null;
        try {
            //解析Token
            JWSObject jwsObject = JWSObject.parse(token);
            Payload payload = jwsObject.getPayload();

            JWSVerifier verifier = new MACVerifier(SECRET);

            if (jwsObject.verify(verifier)) {
                JSONObject jsonOBj = payload.toJSONObject();
                // token校验成功（此时没有校验是否过期）
                // 若payload包含uid，则取出并返回
                if (jsonOBj.containsKey("uid")) {
                    username=jsonOBj.get("uid").toString();
                    System.out.println("username="+username);
                }

            } else {
                // 校验失败
                return null;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            // token格式不合法导致的异常
            return null;
        }
        return username;
    }
}
