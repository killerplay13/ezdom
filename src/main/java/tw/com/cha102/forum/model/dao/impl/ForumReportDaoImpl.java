package tw.com.cha102.forum.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.forum.model.dao.ForumReportDao;
import tw.com.cha102.forum.model.entity.ForumReportVO;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ForumReportDaoImpl implements ForumReportDao {

    @PersistenceContext
    private Session session;

    @Override
    public int insert(ForumReportVO forumReportVO) {
        session.persist(forumReportVO);
        return 1;
    }

    @Override
    public int deleteById(Integer forumReportId) {
        ForumReportVO forumReport = session.load(ForumReportVO.class, forumReportId);
        session.remove(forumReport);
        return 1;
    }

    @Override
    public int updateForumReport(ForumReportVO forumReportVO) {
        Query query = session.createQuery("UPDATE ForumReportVO SET forumPostId=:forumPostId, memberId=:memberId, forumReportWhy=:forumReportWhy where forumReportId=:forumReportId")
                .setParameter("forumPostId", forumReportVO.getForumPostId())
                .setParameter("memberId", forumReportVO.getMemberId())
                .setParameter("forumReportWhy", forumReportVO.getForumReportWhy())
                .setParameter("forumReportId", forumReportVO.getForumReportId());
        int i = query.executeUpdate();
        return i;
    }

    @Override
    public ForumReportVO selectById(Integer forumReportId) {
        return session.get(ForumReportVO.class, forumReportId);
    }

    @Override
    public List<ForumReportVO> selectAll() {
        final String hql = "FROM ForumReportVO ORDER BY id";
        return session
                .createQuery(hql, ForumReportVO.class)
                .getResultList();
    }
}
