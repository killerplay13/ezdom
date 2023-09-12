package tw.com.cha102.groupcreate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.cha102.groupcreate.model.GroupCreateVO;
import tw.com.cha102.groupcreate.service.GroupCreateService;

import java.util.List;

@RestController
@RequestMapping("/GroupIndex")
public class GroupIndexController {
    @Autowired
    GroupCreateService groupCreateService;
    @GetMapping("/showLatestGroupCreate")
    public List<GroupCreateVO> showLatestGroupCreate(){
        List<GroupCreateVO> LatestGroupCreateList = groupCreateService.showLatestGroupCreate();
        return LatestGroupCreateList;
    }

    @GetMapping("/showUpcomingGroupCreate")
    public List<GroupCreateVO> showUpcomingGroupCreate(){
        List<GroupCreateVO> UpcomingGroupCreateList = groupCreateService.showUpcomingGroupCreate();
        return UpcomingGroupCreateList;
    }
}
