package tw.com.cha102.forumreport.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.cha102.forumreport.model.entity.ForumReportVO;

public interface ForumReportDao extends JpaRepository<ForumReportVO, Integer> {
    boolean existsByForumPostIdAndMemberId(Integer forumPostId, Integer memberId);//判斷會員是否已經對特定文章進行過檢舉


}
