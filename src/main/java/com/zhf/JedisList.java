package com.zhf;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author ZengHongFa
 * @create 2020/5/1 0001 17:46
 */
public class JedisList {
    public static void main(String[] args) {
        //连接数据库
        Jedis jedis = new Jedis("localhost", 6380);
        jedis.auth("123456");
        jedis.flushAll();
        jedis.lpush("list-1","aa","bb","cc");   //cc bb aa
        jedis.rpush("list-1","dd","ee","ff");   //cc bb aa dd ee ff
        jedis.lpop("list-1");   //bb aa dd ee ff

        System.out.println(jedis.llen("list-1"));   // 查询一个列表类型的数据有几个元素

        System.out.println(jedis.lindex("list-1",2));   //查询列表某个位置上的元素

        List<String> list = jedis.lrange("list-1", 2, 4);
        for(String s :list){
            System.out.println(s);
        }

    }
}
