package tw.com.cha102.groupcreate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.groupcreate.model.GroupCreateRepository;
import tw.com.cha102.groupcreate.model.GroupCreateVO;
import tw.com.cha102.groupcreate.service.GroupCreateService;
import tw.com.cha102.groupcreate.service.GroupCreateServiceImpl;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/groupcreate")
public class GroupCreateServlet {

    @Autowired
    private GroupCreateService groupCreateService;

    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@RequestBody GroupCreateVO groupCreateVO) {

            if (groupCreateVO.getGroupPhoto() != null && groupCreateVO.getGroupPhoto().length > 0) {

                byte[] photoBytes = groupCreateVO.getGroupPhoto();

                groupCreateVO.setGroupPhoto(photoBytes);
            }

            GroupCreateVO result = groupCreateService.create(groupCreateVO);

            if (result.isSuccessful()) {
                return ResponseEntity.ok("新增揪團成功");
            } else {
                return ResponseEntity.badRequest().body(result.getMessage());
            }
    }

    @GetMapping("/listAllGroupCreate")
    public List<GroupCreateVO> listAllGroupCreate(@RequestParam Integer createMemberId) {
        List<GroupCreateVO> groupCreateList = groupCreateService.findAllGroupCreateByMemberId(createMemberId);
        return groupCreateList;
    }

}
