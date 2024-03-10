import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * 这是测试list类型的类
 * @author 26568
 * @date 2024-03-10 19:36
 */
public class RedisDemoList {
    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("tcp://127.0.0.1:8888");
        try(Jedis jedis = jedisPool.getResource()) {
//            test1(jedis);
//            test2(jedis);
//            test3(jedis);
//            test4(jedis);
//            test5(jedis);
            test6(jedis);
        }
    }

    private static void test6(Jedis jedis) {
        System.out.println("llen");
        jedis.flushAll();
        jedis.rpush("key","111","222");
        long len = jedis.llen("key");
        System.out.println("len = " +len);
    }

    private static void test5(Jedis jedis) {
        System.out.println("brpop");
        jedis.flushAll();
        List<String> result = jedis.brpop(100,"key");
        System.out.println("result[0] "+result.get(0));
        System.out.println("result[1] "+result.get(1));
    }

    private static void test4(Jedis jedis) {
        System.out.println("rpop");
        jedis.flushAll();
        jedis.rpush("key","111","222");
        String result = jedis.rpop("key");
        System.out.println("result = "+result);
        result = jedis.rpop("key");
        System.out.println("result = "+result);
        result = jedis.rpop("key");
        System.out.println("result = "+result);
    }

    private static void test3(Jedis jedis) {
        System.out.println("lpop");
        jedis.flushAll();
        jedis.rpush("key","111","222");
        String result = jedis.lpop("key");
        System.out.println("result = "+result);
        result = jedis.lpop("key");
        System.out.println("result = "+result);
        result = jedis.lpop("key");
        System.out.println("result = "+result);
    }

    private static void test2(Jedis jedis) {
        System.out.println("rpush");
        jedis.flushAll();
        jedis.rpush("key","111","222","333");
        List<String> list = jedis.lrange("key",0,-1);
        System.out.println(list);
    }

    private static void test1(Jedis jedis) {
        System.out.println("lpush 和 lrange");
        jedis.flushAll();
        jedis.lpush("key","111","222","333");
        List<String> list = jedis.lrange("key",0,-1);
        System.out.println(list);
    }

}
