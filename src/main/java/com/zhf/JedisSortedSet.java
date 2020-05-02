package com.zhf;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author ZengHongFa
 * @create 2020/5/1 0001 18:30
 */
public class JedisSortedSet {
    public static void main(String[] args) {
        //连接数据库
        Jedis jedis = new Jedis("localhost",6380);
        jedis.auth("123456");
        jedis.flushAll();
        jedis.zadd("sset",100,"zion");
        jedis.zadd("sset",80,"Peter");
        jedis.zadd("sset",60,"Rose");
        jedis.zadd("sset",10,"Jim");

        System.out.println(jedis.zscore("sset","Jim")); //得到数据中某个元素的score
        jedis.zincrby("sset",5,"Jim");  //给某个元素增加score
        Set<String> mset = jedis.zrangeByScore("sset", 60, 100);
        for (String s:mset){
            System.out.println(s);
        }


        jedis.close();
    }
}
