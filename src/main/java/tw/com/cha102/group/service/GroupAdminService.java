package tw.com.cha102.group.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.group.model.AdminGroup;
import tw.com.cha102.group.model.Group;
import tw.com.cha102.group.model.dao.GroupRepository;

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
}

