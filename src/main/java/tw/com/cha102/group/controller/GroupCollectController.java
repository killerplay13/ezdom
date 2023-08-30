package tw.com.cha102.group.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.group.model.Group;
import tw.com.cha102.group.model.GroupCollect;
import tw.com.cha102.group.service.GroupCollectService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/group/collect")
public class GroupCollectController {

    private final GroupCollectService groupCollectService;



    @Autowired
    public GroupCollectController(GroupCollectService groupCollectService) {
        this.groupCollectService = groupCollectService;
    }

    /**
     * 查詢是否收藏
     * @param groupId
     * @param session
     * @return
     */
    @GetMapping("/{groupId}")
    public ResponseEntity<GroupCollect> getCollect(@PathVariable Integer groupId, HttpSession session) {
        // TODO: 用來獲取已登入的member訊息
        // Member member = (Member) session.getAttribute("member");
        // Integer memberId = member.getMemberId();

        //目前預設為1
        Integer memberId = 1;
        GroupCollect groupCollect = groupCollectService.findByGroupIdAndMemberId(groupId, memberId);
        if (Objects.isNull(groupCollect)) {
            groupCollect = new GroupCollect();
            groupCollect.setGroupCollectId(0);
        }
        return new ResponseEntity<>(groupCollect,HttpStatus.OK);
    }

    /**
     * 添加收藏
     * @param groupId
     * @param session
     * @return
     */
    @PostMapping("/{groupId}")
    public ResponseEntity<List<Group>> addCollect(@PathVariable Integer groupId, HttpSession session) {
        // TODO: 用來獲取已登入的member訊息
        // Member member = (Member) session.getAttribute("member");
        // Integer memberId = member.getMemberId();

        //目前預設為1
        Integer memberId = 1;
        groupCollectService.addCollect(groupId,memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 取消收藏
     * @param groupId
     * @param session
     * @return
     */
    @DeleteMapping("/{groupId}")
    public ResponseEntity<List<Group>> deleteCollect(@PathVariable(name = "groupId")Integer groupId, HttpSession session) {
        // TODO: 用來獲取已登入的member訊息
        // Member member = (Member) session.getAttribute("member");
        // Integer memberId = member.getMemberId();

        //目前預設為1
        Integer memberId = 1;
        groupCollectService.deleteCollect(groupId,memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
