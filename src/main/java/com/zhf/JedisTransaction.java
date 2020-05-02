package com.zhf;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author ZengHongFa
 * @create 2020/5/1 0001 20:22
 */
public class JedisTransaction {
    public static void main(String[] args) {
        //连接数据库
        Jedis jedis = new Jedis("localhost",6380);
        jedis.auth("123456");
        jedis.select(10);   //选择要操作的库
        jedis.flushDB();

//        try {
//            Transaction transaction = jedis.multi();    //获取事务对象，开启事务处理
//            transaction.set("a", "peter");
//            int cc = 100 / 0;   //模拟两个操作之间会出错
//            transaction.set("b", "tom");
//            transaction.exec();
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        jedis.set("money","100");
        jedis.watch("money");
        jedis.set("money","80");    //开始监视后到事务提交前，如果被监视的数据被本事务以外的对象修改，事务将不被提交，返回空列表
        Transaction t = jedis.multi();  //获取事务对象
        t.set("money","120");
        List<Object> r = t.exec();  //提交事务
        jedis.unwatch();
        System.out.println(r);
        System.out.println("money"+jedis.get("money"));


    }
}
