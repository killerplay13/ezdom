package tw.com.cha102.support.model.dao;

import tw.com.cha102.support.model.entity.FaqVO;

import java.util.List;

public interface SupportDAO {
    int add(FaqVO faqVO);
    int update(FaqVO faqVO);
    int deleteById(Integer id);
    FaqVO findById(Integer id);
    List<FaqVO> findAll();
}
