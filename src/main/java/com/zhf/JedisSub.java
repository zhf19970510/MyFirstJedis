package com.zhf;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @author ZengHongFa
 * @create 2020/5/1 0001 21:31
 */
public class JedisSub {
    public static void main(String[] args) {
        //连接数据库
        Jedis jedis = new Jedis("localhost",6380);
        jedis.auth("123456");
        jedis.select(10);   //选择要操作的库
        jedis.flushDB();
        // 订阅频道
        jedis.subscribe(new MyListener(),"cctv");
    }

    /**
     * 内部类，消息处理器
     */
    private static class MyListener extends JedisPubSub{
        @Override
        public void onMessage(String channel, String message) {
            System.out.println(channel+":"+message);
        }
    }
}
