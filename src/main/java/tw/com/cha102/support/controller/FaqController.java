package tw.com.cha102.support.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.support.model.dao.FaqRepository;
import tw.com.cha102.support.model.entity.FaqVO;
import tw.com.cha102.support.service.FaqService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/faq")
public class FaqController {

    @Autowired
    private FaqService faqService;

    @PostMapping("/add")
    public FaqVO add(@Valid @RequestBody FaqVO faqVO){
        return faqService.addFaq(faqVO);
    }

    @DeleteMapping("/delete/{faqId}")
    public String delete(@PathVariable Integer faqId){
        return faqService.deleteFaq(faqId);
    }

    @PutMapping("/update/{faqId}")
    public FaqVO update(@PathVariable Integer faqId,
                        @Valid @RequestBody FaqVO updateFaq){
        return faqService.updateFaq(faqId, updateFaq);
    }

    @GetMapping("/list")
    public List<FaqVO> faqList(@RequestParam(required = false) String faqTag){
        return faqService.getFaqList(faqTag);
    }

}
