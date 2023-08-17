package tw.com.cha102.message.service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.sql.Timestamp;
import java.util.List;

public class JedisHandleMessage {
    private static JedisPool pool = JedisPoolUtil.getJedisPool();
    public static List<String> getHistoryMsg(Integer MEMBER_ID_A, Integer MEMBER_ID_B) {
        String key = new StringBuilder(MEMBER_ID_A).append(":").append(MEMBER_ID_B).toString();
        Jedis jedis = null;
        jedis = pool.getResource();
        List<String> historyData = jedis.lrange(key, 0,-1);
        jedis.close();
        return historyData;
    }

    public static void saveChatMessage(Integer memberIdA, Integer memberIdB, String messageContent, boolean messageStatus, Timestamp messageTime){
        String read = String.valueOf(messageContent);
        String time = String.valueOf(messageTime);
        String senderKey = new StringBuilder(memberIdA).append(":").append(memberIdB).toString();
        String receiverKey = new StringBuilder(memberIdB).append(":").append(memberIdA).toString();
        Jedis jedis = pool.getResource();
        jedis.rpush(senderKey,messageContent, read, time);
        jedis.rpush(receiverKey, messageContent,read, time);

        jedis.close();
    }

}
