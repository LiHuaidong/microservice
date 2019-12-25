package hdli;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 16:27 2019/12/25
 */
public class RedisZSetLimit {

	public static void main(String[] args) {
		allow(Thread.currentThread().getId()+"1");
		allow(Thread.currentThread().getId()+"2");
		allow(Thread.currentThread().getId()+"3");
		allow(Thread.currentThread().getId()+"4");
		allow(Thread.currentThread().getId()+"5");
		allow(Thread.currentThread().getId()+"6");

//		for (int i = 0; i < 50; i++) {
//			new Thread(new Runnable() {
//				public void run() {
//					allow(Thread.currentThread().getId()+"");
//				}
//			}, "Thread" + i).start();
//		}
	}

	public static boolean allow(String ip) {
		Jedis jedis = null;
		try {
			jedis = RedisUtils.getRedis();
			jedis.zadd("v1", System.currentTimeMillis(), ip);

			Long current = System.currentTimeMillis();
			Long last = current - 1000L;

			Set<String> keys = jedis.zrange("v1", last, current);
			if(keys.size() > 4) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(jedis != null) {
				jedis.close();
			}
		}
		return false;
	}
}
