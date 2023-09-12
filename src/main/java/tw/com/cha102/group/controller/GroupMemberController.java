package tw.com.cha102.group.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.group.model.GroupMember;
import tw.com.cha102.group.service.GroupMemberService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/frontend/group/member")
public class GroupMemberController {

    private final GroupMemberService groupMemberService;



    @Autowired
    public GroupMemberController(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }


    @PostMapping
    public ResponseEntity<GroupMember> apply(GroupMember groupMember,HttpSession session) {

        // TODO: 用來獲取已登入的member訊息
        Integer memberId = (Integer) session.getAttribute("memberId");


        //目前預設為1
        //Integer memberId = 1;

        List<GroupMember> list = groupMemberService.findGroupMember(groupMember.getGroupId(), memberId);



        if (list.size() > 0 && list.stream().filter(groupMember1 ->  groupMember1.getGroupApplyStatus() != (byte) 3 && groupMember1.getGroupApplyStatus() != (byte) 2).collect(Collectors.toList()).size() > 0){
            throw new RuntimeException("失敗！已報名該揪團！");
        }
        groupMember.setMemberId(memberId);
        GroupMember groupMember1 = groupMemberService.addGroupMember(groupMember);
        return new ResponseEntity<>(groupMember1, HttpStatus.OK);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity cance(@PathVariable Integer groupId, HttpSession session) {

        // TODO: 用來獲取已登入的member訊息
        Integer memberId = (Integer) session.getAttribute("memberId");


        //目前預設為1
        //Integer memberId = 1;

        groupMemberService.cancel(groupId,memberId);
        return new ResponseEntity(HttpStatus.OK);
    }



}
