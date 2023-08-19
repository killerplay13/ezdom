package tw.com.cha102.forum.model.dao;

import tw.com.cha102.forum.model.entity.ForumReportVO;

import java.util.List;

public interface ForumReportDao {

    int insert(ForumReportVO forumReportVO);

    int deleteById(Integer forumReportId);

    int updateForumReport(ForumReportVO forumReportVO);

    ForumReportVO selectById(Integer forumReportId);

    List<ForumReportVO> selectAll();
}
