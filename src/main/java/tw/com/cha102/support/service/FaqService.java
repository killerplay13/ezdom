package tw.com.cha102.support.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.member.model.dao.MemberRepository;
import tw.com.cha102.member.model.entity.Member;
import tw.com.cha102.support.model.dao.FaqRepository;
import tw.com.cha102.support.model.entity.FaqVO;

import java.util.List;
import java.util.Optional;

@Service
public class FaqService {

    @Autowired
    private FaqRepository faqRepository;

    @Autowired
    private MemberRepository memberRepository;

    // ==================== 後台FAQ管理 ==================== //

    // 新增FAQ
    public FaqVO addFaq(FaqVO faqVO) {
        faqRepository.save(faqVO);
        faqVO.setMessage("新增成功");
        faqVO.setSuccessful(true);
        return faqVO;
    }

    // 刪除FAQ
    public String deleteFaq(Integer faqId) {
        faqRepository.deleteById(faqId);
        return "刪除FAQ成功";
    }

    // 修改FAQ
    public FaqVO updateFaq(Integer faqId, FaqVO updateFaq) {
        Optional<FaqVO> check = faqRepository.findById(faqId);

        if (check.isPresent()) {
            FaqVO faqVO = check.get();
            faqVO.setFaqName(updateFaq.getFaqName());
            faqVO.setFaqAns(updateFaq.getFaqAns());
            faqVO.setFaqTag(updateFaq.getFaqTag());
            faqVO.setMessage("修改FAQ成功");
            faqVO.setSuccessful(true);
            return faqRepository.save(faqVO);
        } else {
            return null;
        }
    }

    // ==================== FAQ List ==================== //
    public List<FaqVO> getFaqList(String faqTag) {

        // 預設為查詢所有類別
        if (faqTag == null) {
            return faqRepository.findAll();
        }

        // 依照傳入的類別查詢
        return faqRepository.findAllByFaqTag(faqTag);
    }


    // ==================== 獲取會員資訊 ==================== //
    public Member getMember(Integer memberId) {
//        Integer memberId = 1;
        Optional<Member> check = memberRepository.findById(memberId);

        if(check.isPresent()){
            return check.get();
        }else {
            return null;
        }
    }

}
