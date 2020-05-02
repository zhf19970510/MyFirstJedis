package com.zhf;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZengHongFa
 * @create 2020/5/2 0002 10:41
 */
public class JedisSentinel {
    public static void main(String[] args) {
        //创建哨兵集合
        Set<String> ips = new HashSet<>();
        ips.add("127.0.0.1:26386");
        ips.add("127.0.0.1:26387");
        ips.add("127.0.0.1:26388");
        //创建哨兵池
        JedisSentinelPool spool = new JedisSentinelPool("redis001", ips);

        //从哨兵池中得到数据库
        Jedis jedis = spool.getResource();
        jedis.auth("123456");
        jedis.flushAll();
        for (int i = 0; i < 100; i++) {
            jedis.set("key"+i,"Redis is so cool!");
        }
        jedis.close();
        spool.destroy();    //释放资源

    }
}
