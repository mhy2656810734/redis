import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author 26568
 * @date 2024-03-09 21:27
 */
public class RedisDemo {
    public static void main(String[] args) {
        // 连接到 Redis 服务器上
        JedisPool jedisPool = new JedisPool("tcp://127.0.0.1:8888");
        try(Jedis jedis = jedisPool.getResource()) {
            // redis 的 各种命令，就都对应到 jedis 对象的各种方法
            String pong = jedis.ping();
            System.out.println(pong);
        }
    }
}
