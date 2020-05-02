package com.zhf;

import redis.clients.jedis.Jedis;

/**
 * @author ZengHongFa
 * @create 2020/5/1 0001 17:06
 */
public class JedisString {
    public static void main(String[] args) {
        //连接数据库
        Jedis jedis = new Jedis("localhost",6380);
        jedis.auth("123456");
        jedis.select(10);   //选择要操作的库
        jedis.flushDB();
        jedis.set("country","china");

        System.out.println(jedis.exists("country"));
        System.out.println(jedis.strlen("country"));    //获取value长度
        jedis.append("country"," is great!");         //在字符串值上增加一部分字符串
        System.out.println(jedis.get("country"));

        jedis.setrange("country",9,"amazing!");  //修改value的一部分
        System.out.println(jedis.get("country"));
        System.out.println(jedis.getrange("country",6,7));     //取得value的一部分

        jedis.set("age","100");
//        jedis.incr("age");      //增加1
//        jedis.decr("age");      //每次减少1
        System.out.println(jedis.get("age"));
        jedis.incrBy("age",10);
//        jedis.decrBy("age",10);
        System.out.println(jedis.get("age"));
        jedis.flushDB();
        jedis.mset("name","zhangsan","age","40","sex","male");  //一次增加多条记录
        System.out.println(jedis.dbSize());
        jedis.close();
    }
}
