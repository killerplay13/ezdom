package tw.com.cha102.groupcreate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.groupcreate.DTO.GroupIdsDTO;
import tw.com.cha102.groupcreate.model.GroupVerifyVO;
import tw.com.cha102.groupcreate.service.GroupVerifyService;
import tw.com.cha102.member.model.entity.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/frontend/groupVerify")
public class GroupVerifyController {

    @Autowired
    private GroupVerifyService groupVerifyService;

    @GetMapping("/listAllGroupVerify")
    public List<GroupVerifyVO> listAllGroupVerify(@ModelAttribute GroupIdsDTO dto){
        List<GroupVerifyVO> groupVerifyList = groupVerifyService.findAllGroupVerifyByGroupId(dto.getGroupIds());

        return groupVerifyList;

    }

    @PutMapping("/updateGroup/{groupMemberId}")
    public ResponseEntity<String> updateGroupApplyStatus(
            @PathVariable Integer groupMemberId,
            @RequestParam Integer groupApplyStatus) {
GroupVerifyVO result = groupVerifyService.updateGroupApplyStatus(groupMemberId, groupApplyStatus);

        return ResponseEntity.ok("修改成功");
    }

    @GetMapping("/findGroupJoined/")
    public List<GroupVerifyVO> findGroupJoined(HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer memberId = (Integer)session.getAttribute("memberId");
        List<GroupVerifyVO> groupJoinedList = groupVerifyService.findGroupJoined(memberId);
        return groupJoinedList;
    }

    @GetMapping("/findGroupMember/{groupId}")
    public List<GroupVerifyVO> findGroupMember(@PathVariable Integer groupId){
        List<GroupVerifyVO> groupMemberList = groupVerifyService.findGroupMember(groupId);
        return groupMemberList;
    }

}
