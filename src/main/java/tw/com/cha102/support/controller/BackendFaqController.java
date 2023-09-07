package tw.com.cha102.support.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.support.model.entity.FaqVO;
import tw.com.cha102.support.service.FaqService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/backend/faq")
public class BackendFaqController {

    @Autowired
    private FaqService faqService;

    // ==================== 瀏覽常見問題 ==================== //
    @GetMapping("/list")
    public List<FaqVO> faqList(@RequestParam(required = false) String faqTag){
        return faqService.getFaqList(faqTag);
    }

    // ==================== 新增常見問題 ==================== //
    @PostMapping("/add")
    public FaqVO add(@Valid @RequestBody FaqVO faqVO){
        return faqService.addFaq(faqVO);
    }

    // ==================== 刪除常見問題 ==================== //
    @DeleteMapping("/delete/{faqId}")
    public String delete(@PathVariable Integer faqId){
        return faqService.deleteFaq(faqId);
    }

    // ==================== 修改常見問題 ==================== //
    @PutMapping("/update/{faqId}")
    public FaqVO update(@PathVariable Integer faqId,
                        @Valid @RequestBody FaqVO updateFaq){
        return faqService.updateFaq(faqId, updateFaq);
    }

}
