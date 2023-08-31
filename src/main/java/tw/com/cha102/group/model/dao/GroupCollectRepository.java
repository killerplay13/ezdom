package tw.com.cha102.group.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.cha102.group.model.GroupCollect;

public interface GroupCollectRepository extends JpaRepository<GroupCollect,Integer> {

    GroupCollect findByGroupIdAndMemberId(Integer groupId, Integer memberId);


}
