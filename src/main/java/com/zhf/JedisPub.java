package com.zhf;

import redis.clients.jedis.Jedis;

/**
 * @author ZengHongFa
 * @create 2020/5/1 0001 21:27
 */
public class JedisPub {
    public static void main(String[] args) {
        //连接数据库
        Jedis jedis = new Jedis("localhost",6380);
        jedis.auth("123456");
        jedis.flushAll();
        //每隔1秒发布一条消息
        for (int i = 0;i<10;i++){
            jedis.publish("cctv","Help"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
