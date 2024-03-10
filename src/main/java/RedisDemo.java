import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Set;

/**
 * @author 26568
 * @date 2024-03-09 21:27
 */
public class RedisDemo {
    /**
     * get和set命令的使用
     * @param jedis  传入的jedis对象
     */
    public static void test1(Jedis jedis){
        System.out.println("get 和 set 的使用");
        // 先清空数据库 避免上一组数据测试的残留数据影响到下一组测试结果
        jedis.flushAll();
        jedis.set("key","111");
        jedis.set("key1","222");
        // 设置超时时间
        SetParams setParams = new SetParams();
        setParams.ex(10);
        // 存在才设置
        setParams.xx();
        // 不存在才设置
//        setParams.nx();
        jedis.set("key","333",setParams);
        String value = jedis.get("key");
        System.out.println("value = "+value);
    }
    public static void main(String[] args) {
        // 连接到 Redis 服务器上
        JedisPool jedisPool = new JedisPool("tcp://127.0.0.1:8888");
        try(Jedis jedis = jedisPool.getResource()) {
            // redis 的 各种命令，就都对应到 jedis 对象的各种方法
//            String pong = jedis.ping();
//            System.out.println(pong);
//            test1(jedis);
//            test2(jedis);
//            test3(jedis);
//            test4(jedis);
            test5(jedis);
        }
    }

    /**
     * 测试type
     * @param jedis 传入的Jedis对象
     */
    private static void test5(Jedis jedis) {
        System.out.println("type 的使用");
        jedis.flushAll();
        jedis.set("key","111");
        String type = jedis.type("key");
        System.out.println("type = "+type);

        jedis.lpush("key1","111","222");
        type = jedis.type("key1");
        System.out.println("type = "+type);

        jedis.hset("key2","f1","111");
        type = jedis.type("key2");
        System.out.println("type = " +type);

        jedis.sadd("key3","111","2222");
        type = jedis.type("key3");
        System.out.println("type = "+type);

        jedis.zadd("key4",100,"zhangsan");
        type = jedis.type("key4");
        System.out.println("type =" + type);
    }

    /**
     * 测试exists 和 del
     * @param jedis
     */
    private static void test2(Jedis jedis) {
        System.out.println("exists 和 del");
        jedis.flushAll();
        jedis.set("key","4444");
        jedis.set("key1","555");
        boolean result = jedis.exists("key");
        System.out.println("result = "+result);
        long result2 = jedis.del("key");
        System.out.println("result2 = "+result2);
        result = jedis.exists("key");
        System.out.println("result = "+result);
    }

    /**
     * 测试keys
     * @param jedis
     */
    public static void test3(Jedis jedis) {
        System.out.println("keys 的使用");
        jedis.flushAll();
        jedis.set("key","4444");
        jedis.set("key1","555");
        Set<String> set = jedis.keys("key*");
        for (String key:set) {
            System.out.println(key);
        }
    }

    /**
     * 测试expire 和 ttl
     * @param jedis 传入的Jedis对象
     */
    public static void test4(Jedis jedis){
        System.out.println("ttl 和 expire的使用");
        jedis.flushAll();
        jedis.set("key","1111");
        jedis.expire("key",10);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long time = jedis.ttl("key");
        System.out.println("time = "+time);
    }
}
