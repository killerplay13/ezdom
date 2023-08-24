package tw.com.cha102.forum.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.cha102.forum.model.entity.ForumReportVO;

import java.util.List;

public interface ForumReportDao extends JpaRepository<ForumReportVO, Integer> {
    boolean existsByForumPostIdAndMemberId(Integer forumPostId, Integer memberId);
    //這個方法的目的是檢查是否存在一個特定的檢舉記錄,以判斷會員是否已經對特定文章進行過檢舉

}
