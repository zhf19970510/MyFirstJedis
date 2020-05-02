package com.zhf;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @author ZengHongFa
 * @create 2020/5/1 0001 19:58
 */
public class JedisPipleLine {
    public static void main(String[] args) {
        //连接数据库
        Jedis jedis = new Jedis("localhost",6380);
        jedis.auth("123456");
        jedis.flushAll();
        int count = 10000;  //记录条数

        long start = System.currentTimeMillis();    //开始时间
        Pipeline pl = jedis.pipelined();        //取得管道对象
        for (int i = 0;i<count;i++){
//            jedis.set("key"+i,"tps");
            pl.set("key"+i,"tps");  //pipleline插入数据
        }
        pl.sync();  //管道同步
        long end = System.currentTimeMillis();
        System.out.println(count+"条数据，耗时"+(end-start)+"毫秒");


    }
}
