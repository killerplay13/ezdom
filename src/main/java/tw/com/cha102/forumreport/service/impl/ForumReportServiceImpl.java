package tw.com.cha102.forumreport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.forum.model.dao.ForumPostDao;
import tw.com.cha102.forumcollect.model.entity.ForumCollectVO;
import tw.com.cha102.forumreport.model.entity.ForumReportVO;
import tw.com.cha102.forumreport.model.dao.ForumReportDao;
import tw.com.cha102.forumreport.service.ForumReportService;

import java.util.List;
import java.util.Optional;

@Service
public class ForumReportServiceImpl implements ForumReportService {
    @Autowired
    private ForumReportDao forumReportDao;


    @Override
    public boolean createReport(ForumReportVO forumReportVO) {
        Integer forumPostId = forumReportVO.getForumPostId();
        Integer memberId = forumReportVO.getMemberId();
        if (hasReportedSamePost(forumPostId, memberId)) {//檢查是否已經檢舉過該文章
            return false;
        }
        ForumReportVO savedReport = forumReportDao.save(forumReportVO);
        return savedReport != null;
    }
    @Override
    public List<ForumReportVO> getAllReports() {

        return forumReportDao.findAll();
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
    public boolean updateReportStatus(Integer reportId) {
        Optional<ForumReportVO> optionalReport = forumReportDao.findById(reportId);

        if (optionalReport.isPresent()) {
            ForumReportVO report = optionalReport.get();

            if (report.getForumReportStatus() == 0) {
                report.setForumReportStatus(1);//更新檢舉狀態為 1
                forumReportDao.save(report);
                return true;
            }
        }
        return false;
    }


    public boolean hasReportedSamePost(Integer forumPostId, Integer memberId) {
        return forumReportDao.existsByForumPostIdAndMemberId(forumPostId, memberId);
    }


}