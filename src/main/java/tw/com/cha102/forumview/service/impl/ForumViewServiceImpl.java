package tw.com.cha102.forumview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tw.com.cha102.forumview.model.entity.ForumViewVO;
import tw.com.cha102.forumview.service.ForumViewService;
import com.google.gson.Gson;
import java.util.HashSet;
import java.util.Set;

//@Service
public class ForumViewServiceImpl implements ForumViewService {
//    private static String REDIS_KEY_PREFIX = "forum_views:";
//
//    private RedisTemplate<String, String> redisTemplate;
//
//    @Autowired
//    public ForumViewServiceImpl(RedisTemplate<String, String> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }
//    private Gson gson = new Gson();
//
//    @Override
//    public void addView(ForumViewVO view) {
//        String key = REDIS_KEY_PREFIX + view.getMemberId();
//        Double score = redisTemplate.opsForZSet().score(key, gson.toJson(view));
//
//        if (score == null) {
//            redisTemplate.opsForZSet().add(key, gson.toJson(view), System.currentTimeMillis());
//        }
//    }
//
//    @Override
//    public Set<ForumViewVO> getRecentViewedForumPosts(Integer memberId, long fromTimestamp, long toTimestamp) {
//        String key = REDIS_KEY_PREFIX + memberId;
//        Set<String> jsonViews = redisTemplate.opsForZSet().rangeByScore(key, fromTimestamp, toTimestamp);
//        Set<ForumViewVO> views = new HashSet<>();
//
//        for (String jsonView : jsonViews) {
//            views.add(gson.fromJson(jsonView, ForumViewVO.class));
//        }
//
//        return views;
//    }
//
//    @Override
//    public void deleteView(Integer memberId, Integer forumPostId) {
//        String key = REDIS_KEY_PREFIX + memberId;
//        ForumViewVO view = new ForumViewVO();
//        view.setMemberId(memberId);
//        view.setForumPostId(forumPostId);
//        redisTemplate.opsForZSet().remove(key, gson.toJson(view));
//    }
}

