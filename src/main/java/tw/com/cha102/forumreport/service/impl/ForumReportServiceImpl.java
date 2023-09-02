package tw.com.cha102.forumreport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public ForumReportVO createReport(ForumReportVO forumReportVO) {

        return forumReportDao.save(forumReportVO);
    }

    @Override
    public List<ForumReportVO> getAllReports() {

        return forumReportDao.findAll();
    }

    @Override
    public ForumReportVO getReportById(Integer reportId) {

        return forumReportDao.findById(reportId).orElse(null);
    }

    @Override
    public boolean deleteReport(Integer reportId) {
        if (forumReportDao.existsById(reportId)) {
            forumReportDao.deleteById(reportId);
            return true;
        }
        return false;
    }
    @Override
    // 檢查是否已經檢舉過相同的文章
    public boolean hasReportedSamePost(Integer forumPostId, Integer memberId) {
        return forumReportDao.existsByForumPostIdAndMemberId(forumPostId, memberId);
    }


}
