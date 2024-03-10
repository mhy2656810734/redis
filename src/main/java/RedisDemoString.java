import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * 测试String类型的命令
 * @author 26568
 * @date 2024-03-10 11:17
 */
public class RedisDemoString {
    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("tcp://127.0.0.1:8888");
        try(Jedis jedis = jedisPool.getResource()) {
//            test1(jedis);
//            test2(jedis);
//            test3(jedis);
            test4(jedis);
        }
    }

    private static void test4(Jedis jedis) {
        System.out.println("incr 和  decr");
        jedis.flushAll();
        jedis.set("key","100");
        long result = jedis.incr("key");
        System.out.println("result = " +result);
        result = jedis.decr("key");
        System.out.println("result = "+result);
    }

    private static void test3(Jedis jedis) {
        System.out.println("append 的使用");
        jedis.flushAll();
        jedis.set("key","abc");
        jedis.append("key","hhhhh");
        String value = jedis.get("key");
        System.out.println("value = "+value);
    }

    /**
     * 测试getrange 和 setrange
     * @param jedis
     */
    private static void test2(Jedis jedis) {
        System.out.println("getrange 和 setrange的使用");
        jedis.flushAll();
        jedis.mset("key","1111111","key1","222");

        String result = jedis.getrange("key",0,-2);
        System.out.println("result = "+result);
        jedis.setrange("key",2,"hhh");
        result = jedis.getrange("key",0,-1);
        System.out.println("result:" +result);
    }

    /**
     * 测试 mset 和 mget命令
     * @param jedis
     */
    private static void test1(Jedis jedis) {
        System.out.println("mset 和 mget");
        jedis.flushAll();
        jedis.mset("key1","111","key2","222");
        List<String> list= jedis.mget("key1","key2","keys");
        System.out.println(list);
    }

}
