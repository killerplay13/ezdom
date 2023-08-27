package tw.com.cha102.group.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.group.model.AdminGroup;
import tw.com.cha102.group.model.Group;
import tw.com.cha102.group.service.GroupAdminService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/group")
public class GroupAdminController {

    private final GroupAdminService groupAdminService;

    @Autowired
    public GroupAdminController(GroupAdminService groupAdminService) {
        this.groupAdminService = groupAdminService;
    }

    @GetMapping("/admin/list") // 瀏覽揪團審核列表
    public ResponseEntity<List<Group>> listGroups() {
        List<Group> groupList = groupAdminService.getAllGroups();
        return new ResponseEntity<>(groupList, HttpStatus.OK);
    }

    @GetMapping("/list") // 前台揪團列表，只有審核成功才會列在這邊
    public ResponseEntity<List<Group>> listApprovedGroups() {
        List<Group> approvedGroupList = groupAdminService.getApprovedGroups();
        return new ResponseEntity<>(approvedGroupList, HttpStatus.OK);
    }
    @GetMapping("/admin/{groupId}")//單一查詢揪團審核
    public ResponseEntity<Group> getGroupById(@PathVariable Integer groupId) {
        Optional<Group> group = groupAdminService.getGroupById(groupId);

        if (group.isPresent()) {
            return new ResponseEntity<>(group.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/admin/{groupId}") // 修改揪團狀態
    public ResponseEntity<Group> updateGroupStatus(
            @PathVariable Integer groupId,
            @RequestBody AdminGroup updateGroup) {
        Group updatedGroup = groupAdminService.updateGroup(groupId, updateGroup);

        if (updatedGroup != null) {
            return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
