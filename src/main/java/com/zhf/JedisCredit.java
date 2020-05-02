package com.zhf;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author ZengHongFa
 * @create 2020/5/1 0001 20:49
 */
public class JedisCredit {
    public static void main(String[] args) {
        //连接数据库
        Jedis jedis = new Jedis("localhost",6380);
        jedis.auth("123456");
        jedis.flushAll();

        jedis.set("credit","4000"); //信用余额
        jedis.set("debt","1000");   //债务余额

        int out = 1000; //将要刷出的金额
        int credit = Integer.parseInt(jedis.get("credit"));
        if(credit<out){
            System.out.println("信用余额不足，刷卡失败");
            return;
        }else{
            jedis.watch("credit","debt");   //监视数据
            Transaction transaction = jedis.multi();    //获取事务对象
            transaction.decrBy("credit",out);
            transaction.incrBy("debt",1000);
            List<Object> results = transaction.exec();
            jedis.unwatch();
            if(!results.isEmpty()){
                System.out.println("刷卡成功!");
            }else {
                System.out.println("刷卡失败!");
            }
            System.out.println("信用余额"+jedis.get("credit")+",债务余额："+jedis.get("debt"));

        }



    }
}
