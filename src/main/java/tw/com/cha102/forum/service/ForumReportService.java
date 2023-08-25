package tw.com.cha102.forum.service;

import tw.com.cha102.forum.model.entity.ForumReportVO;

import java.util.List;

public interface ForumReportService {

    ForumReportVO createReport(ForumReportVO forumReportVO);

    List<ForumReportVO> getAllReports();

    ForumReportVO getReportById(Integer reportId);

    boolean deleteReport(Integer reportId);

//    boolean updateReportStatus(Integer reportId, Integer newStatus);



}
