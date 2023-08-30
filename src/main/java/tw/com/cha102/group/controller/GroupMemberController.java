package tw.com.cha102.group.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tw.com.cha102.group.model.GroupMember;
import tw.com.cha102.group.service.GroupMemberService;

import java.util.Objects;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/group/member")
public class GroupMemberController {

    private final GroupMemberService groupMemberService;



    @Autowired
    public GroupMemberController(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }


    @PostMapping
    public ResponseEntity<GroupMember> apply(GroupMember groupMember) {

        // TODO: 用來獲取已登入的member訊息
        // Member member = (Member) session.getAttribute("member");
        // Integer memberId = member.getMemberId();

        //目前預設為1
        Integer memberId = 1;

        GroupMember entity = groupMemberService.findGroupMember(groupMember.getGroupId(), memberId);

        if (Objects.nonNull(entity)){
            throw new RuntimeException("失敗！已報名該揪團！");
        }

        groupMember.setMemberId(memberId);
        GroupMember groupMember1 = groupMemberService.addGroupMember(groupMember);
        return new ResponseEntity<>(groupMember1, HttpStatus.OK);
    }
}
