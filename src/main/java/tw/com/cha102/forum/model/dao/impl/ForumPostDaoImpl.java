package tw.com.cha102.forum.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.forum.model.dao.ForumPostDao;
import tw.com.cha102.forum.model.entity.ForumPostVO;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ForumPostDaoImpl implements ForumPostDao {

    @PersistenceContext
    private Session session;

    @Override
    public int insert(ForumPostVO forumPostVO) {
        session.persist(forumPostVO);
        return 1;
    }

    @Override
    public int deleteById(Integer forumPostId) {
        ForumPostVO forumPost = session.load(ForumPostVO.class, forumPostId);
        session.remove(forumPost);
        return 1;
    }

    @Override
    public int updateForumPost(ForumPostVO forumPostVO) {
        Query query = session.createQuery("UPDATE ForumPostVO SET forumPostContent=:forumPostContent, forumPostTitle=:forumPostTitle, memberId=:memberId, forumCategoryId=:forumCategoryId where forumPostId=:forumPostId")
                .setParameter("forumPostContent", forumPostVO.getForumPostContent())
                .setParameter("forumPostTitle", forumPostVO.getForumPostTitle())
                .setParameter("memberId", forumPostVO.getMemberId())
                .setParameter("forumCategoryId", forumPostVO.getForumCategoryId())
                .setParameter("forumPostId", forumPostVO.getForumPostId());
        int i = query.executeUpdate();
        return i;
    }

    @Override
    public ForumPostVO selectById(Integer forumPostId) {
        return session.get(ForumPostVO.class, forumPostId);
    }

    @Override
    public List<ForumPostVO> selectAll() {
        final String hql = "FROM ForumPostVO ORDER BY id";
        return session
                .createQuery(hql, ForumPostVO.class)
                .getResultList();
    }
}
