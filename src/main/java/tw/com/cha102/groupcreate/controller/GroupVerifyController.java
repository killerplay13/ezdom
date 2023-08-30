package tw.com.cha102.groupcreate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.groupcreate.DTO.GroupIdsDTO;
import tw.com.cha102.groupcreate.model.GroupVerifyVO;
import tw.com.cha102.groupcreate.service.GroupVerifyService;
import tw.com.cha102.member.model.entity.Member;

import java.util.List;

@RestController
@RequestMapping("/groupVerify")
public class GroupVerifyController {

    @Autowired
    private GroupVerifyService groupVerifyService;

    @GetMapping("/listAllGroupVerify")
    public List<GroupVerifyVO> listAllGroupVerify(@ModelAttribute GroupIdsDTO dto){
        List<GroupVerifyVO> groupVerifyList = groupVerifyService.findAllGroupVerifyByGroupId(dto.getGroupIds());

        return groupVerifyList;

    }

}
