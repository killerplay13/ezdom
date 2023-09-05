package tw.com.cha102.forumreport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.forum.model.dao.ForumPostDao;
import tw.com.cha102.forumcollect.model.entity.ForumCollectVO;
import tw.com.cha102.forumreport.model.entity.ForumReportVO;
import tw.com.cha102.forumreport.model.dao.ForumReportDao;
import tw.com.cha102.forumreport.service.ForumReportService;

import java.util.List;

@Service
public class ForumReportServiceImpl implements ForumReportService {

    private final ForumReportDao forumReportDao;

    @Autowired
    public ForumReportServiceImpl(ForumReportDao forumReportDao) {

        this.forumReportDao = forumReportDao;
    }

    @Override
    public boolean createReport(ForumReportVO forumReportVO) {
        Integer forumPostId = forumReportVO.getForumPostId();
        Integer memberId = forumReportVO.getMemberId();
        // 檢查是否已經檢舉過該文章
        if (hasReportedSamePost(forumPostId, memberId)) {
            return false; // 已經檢舉過該文章，返回false
        }
        ForumReportVO savedReport = forumReportDao.save(forumReportVO);
        // 如果保存成功，返回true
        return savedReport != null;
    }
    @Override
    public List<ForumReportVO> getAllReports() {

        return forumReportDao.findAll();
    }

    @Override
    public ForumReportVO getReportById(Integer forumReportId) {

        return forumReportDao.findById(forumReportId).orElse(null);
    }

    @Override
    public boolean deleteReport(Integer forumReportId) {
        if (forumReportDao.existsById(forumReportId)) {
            forumReportDao.deleteById(forumReportId);
            return true;
        }
        return false;
    }
    @Override
    // 檢查是否已經檢舉過相同的文章
    public boolean hasReportedSamePost(Integer forumPostId, Integer memberId) {
        return forumReportDao.existsByForumPostIdAndMemberId(forumPostId, memberId);
    }

    @Override
    public boolean updateReportStatus(Integer reportId) {
        ForumReportVO report = forumReportDao.findById(reportId).orElse(null);

        if (report != null && report.getForumReportStatus() == 0) {
            // 更新檢舉狀態為 1
            report.setForumReportStatus(1);
            forumReportDao.save(report);
            return true;
        }

        return false;
    }


}