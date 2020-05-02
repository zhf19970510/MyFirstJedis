package com.zhf;

import javafx.geometry.HPos;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ZengHongFa
 * @create 2020/5/1 0001 17:23
 */
public class JedisHash {
    public static void main(String[] args) {
        //连接数据库
        Jedis jedis = new Jedis("localhost",6380);
        jedis.auth("123456");

        HashMap<String ,String > hap = new HashMap<>();
        hap.put("name","HP-3321");
        hap.put("price","5000");
        jedis.hmset("hpcomputer", hap); //向redis中存入一个hash类型的数据
        System.out.println(jedis.hlen("hpcomputer")); //得到hash类型元素的个数
        jedis.hset("hpcomputer","madein","China");  //向hash类型的数据中增加元素
        System.out.println(jedis.hlen("hpcomputer")); //得到hash类型元素的个数

        Map<String, String> all = jedis.hgetAll("hpcomputer");  //得到一个hash类型数据的全部元素
        for (String k:all.keySet()){
            System.out.println(k+":"+all.get(k));
        }

        System.out.println(jedis.hget("hpcomputer","price"));

        Set<String> hkeys = jedis.hkeys("hpcomputer");      //得到hash类型数据的的全部的key
        for (String key :hkeys){
            System.out.println(key);
        }

        List<String> hvals = jedis.hvals("hpcomputer");
        for (String v:hvals){
            System.out.println(v);
        }
    }
}
