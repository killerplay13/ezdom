package tw.com.cha102.group.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.cha102.group.model.GroupMember;
import tw.com.cha102.group.model.dao.GroupMemberRepository;

import java.util.List;

@Service
@Transactional
public class GroupMemberService {

    private final GroupMemberRepository groupMemberRepository;


    @Autowired
    public GroupMemberService(GroupMemberRepository groupMemberRepository) {
        this.groupMemberRepository = groupMemberRepository;
    }

    public GroupMember addGroupMember(GroupMember groupMember) {
        return groupMemberRepository.save(groupMember);
    }

    public GroupMember findGroupMember(Integer groupId, Integer memberId) {
        return groupMemberRepository.findByGroupIdAndMemberId(groupId, memberId);
    }

    public List<Integer> findGroupIdsByMemberIdAndStatus(Integer memberId,Byte groupApplyStatus) {
        return groupMemberRepository.findGroupIdsByMemberIdAndStatus(memberId, groupApplyStatus);
    }
}
