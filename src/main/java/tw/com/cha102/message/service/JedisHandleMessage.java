package tw.com.cha102.message.service;

import redis.clients.jedis.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.Set;
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
       Jedis jedis = pool.getResource();
            jedis.rpush(senderKey, message);
            jedis.rpush(receiverKey, message);
            logger.info("Chat message saved successfully.");
            jedis.close();
    }
//      還不會動的已讀訊息
//    public static void updateMessageStatus(String memberIdA, String memberIdB) {
//        String senderKey = new StringBuilder(memberIdA).append(":").append(memberIdB).toString();
//        String receiverKey = new StringBuilder(memberIdB).append(":").append(memberIdA).toString();
//        try (Jedis jedis = pool.getResource()) {
//
//            Pipeline pipeline = jedis.pipelined();
//            Response<String> senderValue = pipeline.get(senderKey);
//            Response<String> receiverValue = pipeline.get(receiverKey);
//            pipeline.sync();
//
//            String senderJsonValue = senderValue.get();
//            String receiverJsonValue = receiverValue.get();
//
//            if (senderJsonValue != null) {
//                JsonObject senderJsonObject = new JsonObject();
//                senderJsonObject.addProperty("messageStatus", true);
//                jedis.set(senderKey, senderJsonObject.toString());
//            }
//
//            if (receiverJsonValue != null) {
//                JsonObject receiverJsonObject = new JsonObject();
//                receiverJsonObject.addProperty("messageStatus", true);
//                jedis.set(receiverKey, receiverJsonObject.toString());
//            }
//            System.out.println("Updated messageStatus for both keys.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public static void saveGroupMessage(String groupId, String memberId, String message) {
        String zsetName = "groupId:memberId";
        Jedis jedis = pool.getResource();
        double timestamp = System.currentTimeMillis();

        double memberScore = Double.valueOf(groupId) + (Double.valueOf(memberId) / 1000.0);

        jedis.zadd(zsetName, timestamp, message + "|" + memberScore);
        logger.info("Group message saved successfully.");
        jedis.close();
    }

    public static Set<Tuple> getgroupHistoryMsg(String Group_ID) {
        String zsetName = "group:" + Group_ID + ":messages";
        Jedis jedis = pool.getResource();
        Set<Tuple> historyData = jedis.zrangeWithScores("groupId", 0, -1);
        jedis.close();
        return historyData;
    }


}
