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

@RestController
@RequestMapping("/groupcreate")
public class GroupCreateServlet {

    @Autowired
    private GroupCreateService groupCreateService;

//    @PostMapping("/create")
//    public GroupCreateVO create(@RequestBody GroupCreateVO groupCreateVO) {
//        return groupCreateService.create(groupCreateVO);
//    }

    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@RequestBody GroupCreateVO groupCreateVO) {

            if (groupCreateVO.getGroupPhoto() != null && groupCreateVO.getGroupPhoto().length > 0) {
                // 解码 Base64 编码的图片数据为 byte[]
                byte[] photoBytes = groupCreateVO.getGroupPhoto();

                // 将 byte[] 数据存储到 GroupCreateVO 对象中
                groupCreateVO.setGroupPhoto(photoBytes);
            }


            // 呼叫服務層的 create 方法處理創建群組
            GroupCreateVO result = groupCreateService.create(groupCreateVO);

            if (result.isSuccessful()) {
                return ResponseEntity.ok("Group created successfully");
            } else {
                return ResponseEntity.badRequest().body(result.getMessage());
            }

    }
}
