package tw.com.cha102.forum.model.dao.impl;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.forum.model.dao.ForumCollectDao;
import tw.com.cha102.forum.model.entity.ForumCollectVO;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ForumCollectDaoImpl implements ForumCollectDao {

    @PersistenceContext
    private Session session;

    @Override
    public int insert(ForumCollectVO forumCollectVO) {
        session.persist(forumCollectVO);
        return 1;
    }

    @Override
    public int deleteById(Integer forumCollectId) {
        ForumCollectVO forumCollect = session.load(ForumCollectVO.class, forumCollectId);
        session.remove(forumCollect);
        return 1;
    }

    @Override
    public int updateForumCollect(ForumCollectVO forumCollectVO) {
        Query query = session.createQuery("UPDATE ForumCollectVO SET forumPostId=:forumPostId, memberId=:memberId where forumCollectId=:forumCollectId")
                .setParameter("forumPostId", forumCollectVO.getForumPostId())
                .setParameter("memberId", forumCollectVO.getMemberId())
                .setParameter("forumCollectId", forumCollectVO.getForumCollectId());
        int i = query.executeUpdate();
        return i;
    }

    @Override
    public ForumCollectVO selectById(Integer forumCollectId) {
        return session.get(ForumCollectVO.class, forumCollectId);
    }

    @Override
    public List<ForumCollectVO> selectAll() {
        final String hql = "FROM ForumCollectVO ORDER BY id";
        return session
                .createQuery(hql, ForumCollectVO.class)
                .getResultList();
    }
}
