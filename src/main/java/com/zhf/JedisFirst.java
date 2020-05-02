package com.zhf;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author ZengHongFa
 * @create 2020/5/1 0001 15:58
 */
public class JedisFirst {
    public static void main(String[] args) {
    	// 连接redis数据库
        Jedis jedis = new Jedis("localhost",6380);
        jedis.auth("123456");
        System.out.println(jedis.getDB());      //得到当前的db

        //切换数据库
        jedis.select(10);
        System.out.println(jedis.getDB());

        // jedis.flushDB();        //清空当前数据库
        // jedis.flushAll();       //清空所有数据

        jedis.set("a","Jim");       //插入或者修改一条数据
        jedis.set("b","Tom");
        jedis.set("c","Rose");
        jedis.setex("e",7,"white");
        // 遍历该库中的所有数据
        Set<String> keys = jedis.keys("*"); //得到所有的key
        for(String k:keys){
            System.out.println(k + ":" +jedis.get(k));  //输出k和value
        }
        System.out.println(jedis.dbSize());     //输出当前库的记录条数
        jedis.move("c",11);
        System.out.println(jedis.dbSize());
        jedis.expire("c",5);        //设置key的生存时间
        jedis.close();  //关闭连接


    }
}
