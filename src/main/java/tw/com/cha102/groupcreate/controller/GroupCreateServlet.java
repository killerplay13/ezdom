package tw.com.cha102.groupcreate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.cha102.groupcreate.model.GroupCreateVO;
import tw.com.cha102.groupcreate.service.GroupCreateService;

@RestController
@RequestMapping("groupcreate/groupcreate")
public class GroupCreateServlet {

    @Autowired
    private GroupCreateService service;

    @PostMapping
    public GroupCreateVO groupcreate(@RequestBody GroupCreateVO groupCreateVO){
        if(groupCreateVO == null){
            groupCreateVO = new GroupCreateVO();
            groupCreateVO.setMessage("無揪團資訊");
            groupCreateVO.setSuccessful(false);
            return groupCreateVO;
        }
        return service.create(groupCreateVO);
    }
}
