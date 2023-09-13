package tw.com.cha102.groupcreate.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import tw.com.cha102.groupcreate.model.GroupCreateVO;

import java.util.List;
import java.util.Optional;

@Service
@SpringBootApplication
public interface GroupCreateService {
 GroupCreateVO create(Integer memberId,GroupCreateVO groupCreateVO);

 List<GroupCreateVO> findAllGroupCreateByMemberId(Integer createMemberId);
 GroupCreateVO update(Integer memberId,GroupCreateVO groupCreateVO);

 GroupCreateVO updateRegisterNumber(Integer groupId);

 Optional<GroupCreateVO> groupInfo(Integer groupId);

 List<GroupCreateVO> showLatestGroupCreate();

 List<GroupCreateVO> showUpcomingGroupCreate();

 List findGroupId(Integer memberId);

// List<GroupCreateVO> findOneGroupCreateByMemberIdAndStatus();
}


