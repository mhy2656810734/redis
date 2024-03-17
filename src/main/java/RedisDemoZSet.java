import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.resps.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 26568
 * @date 2024-03-17 11:18
 */
public class RedisDemoZSet {
    private final  int s = 10;
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
        System.out.println("zrank");
        jedis.flushAll();
        jedis.zadd("key",10,"zhangsan");
        jedis.zadd("key",20,"lisi");
        jedis.zadd("key",30,"wangwu");
        Long index = jedis.zrank("key","lisi");
        System.out.println("index: "+index);
    }
    private static void test3(Jedis jedis) {
        System.out.println("zscore");
        jedis.flushAll();
        jedis.zadd("key",10,"zhangsan");
        jedis.zadd("key",20,"lisi");
        jedis.zadd("key",30,"wangwu");
        Double score = jedis.zscore("key","zhangsn");
        System.out.println("score: "+score);
    }

    private static void test2(Jedis jedis) {
        System.out.println("zcard");
        jedis.flushAll();
        jedis.zadd("key",10,"zhangsan");
        jedis.zadd("key",20,"lisi");
        jedis.zadd("key",30,"wangwu");
        long len = jedis.zcard("key");
        System.out.println("len："+len);
    }

    private static void test1(Jedis jedis) {

        System.out.println("zadd 和  zrange");
        jedis.flushAll();
        jedis.zadd("key",10,"zhangsan");
        Map<String,Double> members = new HashMap<>();
        members.put("lisi",20.0);
        members.put("wangwu",30.0);
        jedis.zadd("key",members);
        List<String> list = jedis.zrange("key",0,-1);
        System.out.println("list: "+list);
        List<Tuple> memser = jedis.zrangeWithScores("key",0,-1);
        System.out.println("memser: "+memser);
        String member = memser.get(0).getElement();
        Double score = memser.get(0).getScore();
        System.out.println("member: "+member+" score: "+score);
    }
}
