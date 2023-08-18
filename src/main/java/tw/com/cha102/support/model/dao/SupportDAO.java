package tw.com.cha102.support.model.dao;

import tw.com.cha102.support.model.entity.SupportVO;

import java.util.List;

public interface SupportDAO {
    int add(SupportVO supportVO);
    int update(SupportVO supportVO);
    int deleteById(Integer id);
    SupportVO findById(Integer id);
    List<SupportVO> findAll();
}
