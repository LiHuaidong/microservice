package hdli;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 14:41 2019/1/30
 */
public class JwtUtil {

	public String createJWT(String id, String subject, long ttlMillis) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		Map<String, Object> cliams = new HashMap<>();
		cliams.put("uid", "uniqueId");
		cliams.put("user_name", "admin");
		cliams.put("nick_name", "who");
		SecretKey key = generalKey();

		JwtBuilder builder = Jwts.builder().setClaims(cliams).setId(id).setIssuedAt(now).setSubject(subject)
				.signWith(signatureAlgorithm, key);

		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		return builder.compact();
	}

	public Claims parseJWT(String jwt) {
		SecretKey secretKey = generalKey();
		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
		return claims;
	}

	public SecretKey generalKey(){
		String stringKey = "aiqinghai";
		byte[] encodedKey = org.apache.commons.codec.binary.Base64.decodeBase64(stringKey);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}

	public static void main(String[] args) {
		JwtUtil util = new JwtUtil();
		String ab = null;
		ab = util.createJWT("jwt", "{id:100,name:aiqinhai}", 10000);

		System.out.println("签名之后的JWT:" + ab);
		String jwt = ab;
		Claims c = null;

		try {
			c = util.parseJWT(jwt);
			//注意：如果jwt已经过期了，这里会抛出jwt过期异常。
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(c.getId());
		System.out.println(c.getIssuedAt());
		System.out.println(c.getSubject());
		System.out.println("获取私有声明中的nick_name:"+c.get("nick_name"));
		System.out.println("获取私有声明中的user_name:"+c.get("user_name"));
		System.out.println(c.get("uid", String.class));
	}
}
