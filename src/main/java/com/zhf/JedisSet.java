package com.zhf;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author ZengHongFa
 * @create 2020/5/1 0001 17:57
 */
public class JedisSet {
    public static void main(String[] args) {
        //连接数据库
        Jedis jedis = new Jedis("localhost",6380);
        jedis.auth("123456");
        jedis.flushAll();
        jedis.sadd("set1","tom","jim","peter");
        jedis.srem("set1","jim");   //移除元素，set1
        jedis.sadd("set2","smith","rose","tom");
        System.out.println(jedis.scard("set1"));    //查询集合中元素个数
        Set<String> smembers = jedis.smembers("set1");  //获取集合数据的成员
        for(String m: smembers){
            System.out.println(m);
        }

//        Set<String> sin = jedis.sinter("set1", "set2");   //求交集
        jedis.sinterstore("set3","set1","set2");  // 求交集并存入redis



    }
}
