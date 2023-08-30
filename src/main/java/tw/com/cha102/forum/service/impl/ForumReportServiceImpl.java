package tw.com.cha102.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.forum.model.entity.ForumReportVO;
import tw.com.cha102.forum.model.dao.ForumReportDao;
import tw.com.cha102.forum.service.ForumReportService;

import java.util.List;
import java.util.Optional;

@Service
public class ForumReportServiceImpl implements ForumReportService {

    private final ForumReportDao forumReportDao;

    @Autowired
    public ForumReportServiceImpl(ForumReportDao forumReportDao) {
        this.forumReportDao = forumReportDao;
    }

    @Override
    public ForumReportVO createReport(ForumReportVO forumReportVO) {
        //檢查是否已經檢舉過相同的文章
        boolean hasReported = hasReportedSamePost(forumReportVO.getForumPostId(), forumReportVO.getMemberId());
        //傳遞了兩個參數：要檢舉的文章的ID,要執行檢舉的會員的ID
        if (hasReported) {
            forumReportVO.setSuccessful(false);
            forumReportVO.setMessage("您已檢舉過此文章");
            return forumReportVO;
        }

        //執行實際的檢舉新增
        ForumReportVO createdReport = forumReportDao.save(forumReportVO);
        if (createdReport != null) {
            createdReport.setSuccessful(true);
            createdReport.setMessage("檢舉成功");
        } else {
            createdReport.setSuccessful(false);
            createdReport.setMessage("檢舉失敗");
        }
        return createdReport;
    }

    @Override
    public List<ForumReportVO> getAllReports() {

        return forumReportDao.findAll();
    }

    @Override
    public ForumReportVO getReportById(Integer reportId) {

        return forumReportDao.findById(reportId).orElse(null);
    }
//    @Override
//    public boolean updateReportStatus(Integer reportId, Integer newStatus) {
//        Optional<ForumReportVO> optionalReport = forumReportDao.findById(reportId);
//        if (optionalReport.isPresent()) {
//            ForumReportVO report = optionalReport.get();
//            report.setForumReportStatus(newStatus);
//            forumReportDao.save(report);
//            return true;
//        }
//        return false;
//    }


    @Override
    public boolean deleteReport(Integer reportId) {
        if (forumReportDao.existsById(reportId)) {
            forumReportDao.deleteById(reportId);
            return true;
        }
        return false;
    }

    // 檢查是否已經檢舉過相同的文章
    private boolean hasReportedSamePost(Integer forumPostId, Integer memberId) {
        return forumReportDao.existsByForumPostIdAndMemberId(forumPostId, memberId);
    }


}
