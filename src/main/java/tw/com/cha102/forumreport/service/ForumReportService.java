package tw.com.cha102.forumreport.service;

import tw.com.cha102.forumreport.model.entity.ForumReportVO;

import java.util.List;

public interface ForumReportService {

    boolean createReport(ForumReportVO forumReportVO);

    List<ForumReportVO> getAllReports();

    boolean deleteReport(Integer forumReportId);

    boolean hasReportedSamePost(Integer forumPostId, Integer memberId);

    boolean updateReportStatus(Integer reportId);

}
