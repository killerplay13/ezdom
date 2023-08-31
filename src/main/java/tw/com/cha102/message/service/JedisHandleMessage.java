package tw.com.cha102.message.service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;

public class JedisHandleMessage {
    private static final Logger logger = Logger.getLogger(JedisHandleMessage.class.getName());
    private static JedisPool pool = JedisPoolUtil.getJedisPool();
    public static List<String> getHistoryMsg(String MEMBER_ID_A, String MEMBER_ID_B) {
        String key = new StringBuilder(MEMBER_ID_A).append(":").append(MEMBER_ID_B).toString();
        Jedis jedis = null;
        jedis = pool.getResource();
        List<String> historyData = jedis.lrange(key, 0,-1);
        jedis.close();
        return historyData;
    }

    public static void saveChatMessage(String memberIdA, String memberIdB, String message){

        String senderKey = new StringBuilder(memberIdA).append(":").append(memberIdB).toString();
        String receiverKey = new StringBuilder(memberIdB).append(":").append(memberIdA).toString();
//        Jedis jedis = pool.getResource();
       Jedis jedis = pool.getResource();
            jedis.rpush(senderKey, message);
            jedis.rpush(receiverKey, message);
            logger.info("Chat message saved successfully.");
            jedis.close();
    }

}
