import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 26568
 * @date 2024-03-16 20:37
 */
public class RedisDemoHash {
    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("tcp://127.0.0.1:8888");
        try(Jedis jedis = jedisPool.getResource()) {
//            test1(jedis);
//             test2(jedis);
//             test3(jedis);
//             test4(jedis);
             test5(jedis);
        }
    }

    private static void test5(Jedis jedis) {
        System.out.println("hmget 和  hmset");
        jedis.flushAll();
        Map<String,String> hash = new HashMap();
        hash.put("f1","111");
        hash.put("f2","222");
        hash.put("f3","333");
        jedis.hmset("key",hash);
        List<String> list = jedis.hmget("key","f1","f2");
        System.out.println("list: "+list);
    }

    private static void test4(Jedis jedis) {
        System.out.println("hkeys 和  hvals");
        jedis.flushAll();
        jedis.hset("key","f1","111");
        jedis.hset("key","f2","222");
        jedis.hset("key","f3","333");
        Set<String> set = jedis.hkeys("key");
        List<String> list = jedis.hvals("key");
        System.out.println("set: "+set);
        System.out.println("list: "+list);
    }

    private static void test3(Jedis jedis) {
        System.out.println("hdel");
        jedis.flushAll();
        jedis.hset("key","f1","111");
        jedis.hset("key","f2","222");
        jedis.hset("key","f3","333");
        long ret = jedis.hdel("key","f1","f2");
        System.out.println("ret: "+ret);
    }

    private static void test2(Jedis jedis) {
        System.out.println("hexists");
        jedis.flushAll();
        jedis.hset("key","f1","111");
        boolean exists = jedis.hexists("key","f1");
        System.out.println("exists: "+exists);
    }

    private static void test1(Jedis jedis) {
        System.out.println("hset 和 hget");
        jedis.flushAll();
        jedis.hset("key","f1","111");
        Map<String,String> hash = new HashMap<>();
        hash.put("f1","222");
        hash.put("f2","333");
        jedis.hset("key",hash);
        String result = jedis.hget("key","f1");
        System.out.println("result: " +result);
        result = jedis.hget("key","f5");
        System.out.println("result: "+result);
    }
}
