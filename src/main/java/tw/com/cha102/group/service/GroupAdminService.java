package tw.com.cha102.group.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.group.model.AdminGroup;
import tw.com.cha102.group.model.Group;
import tw.com.cha102.group.model.GroupMember;
import tw.com.cha102.group.model.dao.GroupMemberRepository;
import tw.com.cha102.group.model.dao.GroupRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GroupAdminService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupAdminService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getApprovedGroups() {
        return groupRepository.findByGroupStatus(1); // 1表示審核成功狀態
    }

    public List<Group> getHotApprovedGroups() {
        return groupRepository.findByGroupStatusAndRegisteredNumberIsGreaterThanEqual(1,10); // 1表示審核成功狀態
    }

    public List<Group> conditionApprovedGroups(String groupName) {
        return groupRepository.findByGroupStatusAndGroupNameStartingWith(1,groupName); // 1表示審核成功狀態
    }


    public List<Group> getAllGroups() {
        return (List<Group>) groupRepository.findAll();
    } //瀏覽揪團審核列表

    public Optional<Group> getGroupById(Integer groupId) {
        return groupRepository.findById(groupId);
    }

    public List<Group> getGroupsInIds(List<Integer> groupIds) {
        return groupRepository.findByGroupIdIn(groupIds);
    }

    public Group updateGroup(Integer groupId, AdminGroup updateGroup) {
        Optional<Group> existingGroupOptional = groupRepository.findById(groupId);
        if (existingGroupOptional.isPresent()) {
            Group existingGroup = existingGroupOptional.get();
            existingGroup.setGroupStatus(updateGroup.getGroupStatus());
            existingGroup.setCreateFailReason(updateGroup.getCreateFailReason());

            return groupRepository.save(existingGroup);
        } else {
            return null; // 返回 null 表示找不到要修改的揪團
        }
    }

    @Autowired  //當揪團審核成功 會將團主資料插入groupmember
    private GroupMemberRepository groupMemberRepository; // 自行定義GroupMemberRepository

    public void updateMembersOnSuccessfulApproval() {
        // 查找GROUP_STATUS為1的揪團活動
        List<Group> approvedGroups = groupRepository.findByGroupStatus(1);

        for (Group group : approvedGroups) {
            // 獲取CREATE_MEMBER_ID
            int memberId = group.getCreateMemberId();

            // 創建新的揪團成員記錄
            GroupMember newMember = new GroupMember();
            newMember.setMemberId(memberId);
            newMember.setGroupId(group.getGroupId());
            newMember.setGroupApplyStatus((byte) 1);;
            newMember.setGroupApplyDate(LocalDateTime.now());

            // 將新的揪團成員記錄存入資料庫
            groupMemberRepository.save(newMember);
        }
    }
}

