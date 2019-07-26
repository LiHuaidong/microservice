package hdli;

import redis.clients.jedis.Jedis;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 15:24 2019/7/26
 */
public class RedisUtils {

	private static final String HOST = "192.168.122.1";
	private static final int PORT = 6379;

	public static Jedis getRedis() {
		return new Jedis(HOST,PORT);
	}

}
