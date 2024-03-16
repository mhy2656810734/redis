import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @author 26568
 * @date 2024-03-16 13:00
 */
public class RedisDemoSet {
    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("tcp://127.0.0.1:8888");
        try(Jedis jedis = jedisPool.getResource()) {
//            test1(jedis);
//            test2(jedis);
//            test3(jedis);
//            tes4(jedis);
//            test5(jedis);
//            test6(jedis);
//            test7(jedis);
//            test8(jedis);
            test9(jedis);
        }
    }

    private static void test9(Jedis jedis) {
        System.out.println("sdiff");
        jedis.flushAll();
        jedis.sadd("key1","111","222","333");
        jedis.sadd("key2","333");
        Set<String> result = jedis.sdiff("key1","key2");
        System.out.println("result = "+result);
    }

    private static void test8(Jedis jedis) {
        System.out.println("sunionstore");
        jedis.flushAll();
        jedis.sadd("key1","111","222");
        jedis.sadd("key2","333","444");
        jedis.sunionstore("key3","key1","key2");
        Set<String> ret = jedis.smembers("key3");
        System.out.println(ret);
    }

    private static void test7(Jedis jedis) {
        System.out.println("sunion");
        jedis.flushAll();
        jedis.sadd("key1","111","222");
        jedis.sadd("key2","333","444");
        Set<String> set = jedis.sunion("key1","key2");
        System.out.println("set = "+set);
    }

    private static void test6(Jedis jedis) {
        System.out.println("sinterstore");
        jedis.flushAll();
        jedis.sadd("key1","111","333","555");
        jedis.sadd("key2","111","555","777");
        jedis.sinterstore("key3","key1","key2");
        long len = jedis.scard("key3");
        System.out.println("len = "+len);
        Set<String> result = jedis.smembers("key3");
        System.out.println("result = "+result);
    }

    private static void test5(Jedis jedis) {
        System.out.println("sinter");
        jedis.flushAll();
        jedis.sadd("key1","111","333","555");
        jedis.sadd("key2","111","555","777");
        Set<String> result = jedis.sinter("key1","key2");
        System.out.println(result);
    }

    private static void tes4(Jedis jedis) {
        System.out.println("spop");
        jedis.flushAll();
        jedis.sadd("key","111","222","333","777","8888");
        String reslut = jedis.spop("key");
        System.out.println("result = "+reslut);
    }

    private static void test3(Jedis jedis) {
        System.out.println("scard");
        jedis.flushAll();
        jedis.sadd("key","222","333","111");
        long result = jedis.scard("key");
        System.out.println("result ="+result);
    }

    private static void test2(Jedis jedis) {
        System.out.println("sismember");
        jedis.flushAll();
        jedis.sadd("key","111","222","433");
        boolean flag = jedis.sismember("key","1121");
        System.out.println("flag = "+flag);
    }

    private static void test1(Jedis jedis) {
        System.out.println("sadd 和 smermbers");
        jedis.flushAll();
        jedis.sadd("key","111","222","333");
        Set<String> set = jedis.smembers("key");
        for (String  item: set) {
            System.out.println(item);
        }
    }
}
