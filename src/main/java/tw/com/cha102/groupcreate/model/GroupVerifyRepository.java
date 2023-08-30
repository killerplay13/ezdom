package tw.com.cha102.groupcreate.model;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tw.com.cha102.member.model.entity.Member;

import java.util.List;

public interface GroupVerifyRepository extends JpaRepository<GroupVerifyVO,Integer> {
    @Query(
            value = "select* From cha102g4_test.group_member where GROUP_ID IN (:groupIds)",
            nativeQuery = true
    )
    List<GroupVerifyVO> findAllGroupVerify(@Param("groupIds") List<String> groupIds);

}
