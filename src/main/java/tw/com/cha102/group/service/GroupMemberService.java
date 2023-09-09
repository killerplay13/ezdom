package tw.com.cha102.group.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.cha102.group.model.GroupMember;
import tw.com.cha102.group.model.dao.GroupMemberRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class GroupMemberService {

    private final GroupMemberRepository groupMemberRepository;



    @Autowired
    public GroupMemberService(GroupMemberRepository groupMemberRepository) {
        this.groupMemberRepository = groupMemberRepository;
    }

    public GroupMember save(GroupMember groupMember) {
        return groupMemberRepository.save(groupMember);
    }

    public GroupMember addGroupMember(GroupMember groupMember) {
        return groupMemberRepository.save(groupMember);
    }

    public List<GroupMember> findGroupMember(Integer groupId, Integer memberId) {
        return groupMemberRepository.findByGroupIdAndMemberId(groupId, memberId);
    }

    public List<Integer> findGroupIdsByMemberIdAndStatus(Integer memberId,Byte groupApplyStatus) {
        return groupMemberRepository.findGroupIdsByMemberIdAndStatus(memberId, groupApplyStatus);
    }

    public GroupMember findByGroupIdAndMemberIdAndGroupApplyStatus(Integer groupId,Integer memberId,byte groupApplyStatus) {
        return groupMemberRepository.findByGroupIdAndMemberIdAndGroupApplyStatus(groupId, memberId, groupApplyStatus);
    }

    public void deleteByGroupId(Integer groupId) {
        groupMemberRepository.deleteByGroupId(groupId);
    }

    GroupMember findByGroupIdAndMemberIdAndGroupApplyStatusIsNotIn(Integer groupId, Integer memberId,List<Byte> groupApplyStatus) {
        return groupMemberRepository.findByGroupIdAndMemberIdAndGroupApplyStatusIsNotIn(groupId, memberId, groupApplyStatus);
    }



    public void cancel(Integer groupId,Integer memberId) {
        GroupMember groupMember = groupMemberRepository.findByGroupIdAndMemberIdAndGroupApplyStatusIsNotIn(groupId, memberId, Arrays.asList((byte)3,(byte)2));

        if (Objects.nonNull(groupMember)) {
            groupMember.setGroupApplyStatus((byte) 3);
            groupMemberRepository.save(groupMember);
        }

    }

    public List<GroupMember> findByGroupId(Integer groupId) {
        return groupMemberRepository.findByGroupId(groupId);
    }
}
