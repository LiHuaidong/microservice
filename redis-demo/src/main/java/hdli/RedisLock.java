package hdli;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:51 2019/11/17
 */
public class RedisLock {

	public boolean getLock() {
		Jedis jedis = null;
		try {
			jedis = RedisUtils.getRedis();
			jedis.watch("adb");
			Transaction ts = jedis.multi();

			ts.set("lock", "" + Thread.currentThread().getId(), "NX", "EX", 2000);
			System.out.println("122112");

			ts.exec();



			if(jedis.get("lock").equals("" + Thread.currentThread().getId())) {
				jedis.del("lock");
			}
		} catch (Exception e) {

		} finally {

		}
		return false;
	}

}
