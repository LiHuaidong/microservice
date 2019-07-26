package hdli;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 14:42 2019/7/26
 */
public class RedisRatetimeLimit {

	private static final String RATE_LIMIT_PREFIX = "hdli:rate:limit";

	public static boolean allow() {
		boolean allow = false;
		Jedis jedis = null;
		try {
			jedis = RedisUtils.getRedis();
			StringBuilder keyBuilder = new StringBuilder(RATE_LIMIT_PREFIX);
			String key = keyBuilder.append(":").append(Thread.currentThread()).toString();

			Long pttl = jedis.pttl(key);
			Long rateTimes = null;
			if (pttl > 0) {
				rateTimes = jedis.incr(key);
				if (rateTimes <= 10) {
					allow = true;
				} else {
					System.out.println("超出请求次数");
				}
			} else if (pttl == -1 || pttl == -2 || pttl == 0) {
				Transaction tx = jedis.multi();
				Response<Long> rsp = tx.incr(key);
				tx.expire(key, 60);
				tx.exec();
				rateTimes = rsp.get();
				if (rateTimes <= 10) {
					allow = true;
				} else {
					System.out.println("超出请求次数");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return allow;
	}

}
