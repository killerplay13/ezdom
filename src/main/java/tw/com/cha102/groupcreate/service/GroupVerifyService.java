package tw.com.cha102.groupcreate.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import tw.com.cha102.groupcreate.model.GroupVerifyVO;
import tw.com.cha102.member.model.entity.Member;

import java.util.List;

@Service
@SpringBootApplication
public interface GroupVerifyService {
    List<GroupVerifyVO> findAllGroupVerifyByGroupId(List<String> groupIds);

    GroupVerifyVO updateGroupApplyStatus(Integer groupMemberId, Integer newGroupApplyStatus);

    List<GroupVerifyVO> findGroupJoined(Integer memberId);

    List<GroupVerifyVO> findGroupMember(Integer groupId);
}
