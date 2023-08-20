package tw.com.cha102.forum.model.dao.impl;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.forum.model.dao.ForumCategoryDao;
import tw.com.cha102.forum.model.entity.ForumCategoryVO;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ForumCategoryDaoImpl implements ForumCategoryDao {

    @PersistenceContext
    private Session session;

    @Override
    public int insert(ForumCategoryVO forumCategoryVO) {
        session.persist(forumCategoryVO);
        return 1;
    }

    @Override
    public int deleteById(Integer forumCategoryId) {
        ForumCategoryVO forumCategory = session.load(ForumCategoryVO.class, forumCategoryId);
        session.remove(forumCategory);
        return 1;
    }

    @Override
    public int updateForumCategory(ForumCategoryVO forumCategoryVO) {
        Query query = session.createQuery("UPDATE ForumCategoryVO SET forumCategoryName=:forumCategoryName where forumCategoryId=:forumCategoryId")
                .setParameter("forumCategoryName", forumCategoryVO.getForumCategoryName())
                .setParameter("forumCategoryId", forumCategoryVO.getForumCategoryId());
        int i = query.executeUpdate();
        return i;
    }

    @Override
    public ForumCategoryVO selectById(Integer forumCategoryId) {
        return session.get(ForumCategoryVO.class, forumCategoryId);
    }

    @Override
    public List<ForumCategoryVO> selectAll() {
        final String hql = "FROM ForumCategoryVO ORDER BY id";
        return session
                .createQuery(hql, ForumCategoryVO.class)
                .getResultList();
    }

}

