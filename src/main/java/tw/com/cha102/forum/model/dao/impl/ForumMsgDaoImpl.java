package tw.com.cha102.forum.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.forum.model.dao.ForumMsgDao;
import tw.com.cha102.forum.model.entity.ForumMsgVO;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ForumMsgDaoImpl implements ForumMsgDao {

    @PersistenceContext
    private Session session;

    @Override
    public int insert(ForumMsgVO forumMsgVO) {
        session.persist(forumMsgVO);
        return 1;
    }

    @Override
    public int deleteById(Integer forumMsgId) {
        ForumMsgVO forumMsg = session.load(ForumMsgVO.class, forumMsgId);
        session.remove(forumMsg);
        return 1;
    }

    @Override
    public int updateForumMsg(ForumMsgVO forumMsgVO) {
        Query query = session.createQuery("UPDATE ForumMsgVO SET forumPostId=:forumPostId, memberId=:memberId, forumMsgContent=:forumMsgContent where forumMsgId=:forumMsgId")
                .setParameter("forumPostId", forumMsgVO.getForumPostId())
                .setParameter("memberId", forumMsgVO.getMemberId())
                .setParameter("forumMsgContent", forumMsgVO.getForumMsgContent())
                .setParameter("forumMsgId", forumMsgVO.getForumMsgId());
        int i = query.executeUpdate();
        return i;
    }

    @Override
    public ForumMsgVO selectById(Integer forumMsgId) {
        return session.get(ForumMsgVO.class, forumMsgId);
    }

    @Override
    public List<ForumMsgVO> selectAll() {
        final String hql = "FROM ForumMsgVO ORDER BY id";
        return session
                .createQuery(hql, ForumMsgVO.class)
                .getResultList();
    }
}

