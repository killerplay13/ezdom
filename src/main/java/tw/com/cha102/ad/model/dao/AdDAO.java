package tw.com.cha102.ad.model.dao;

import tw.com.cha102.ad.model.entity.AdVO;

import java.util.List;

public interface AdDAO {

    int insert(AdVO adVO);

    int deleteById(Integer adId);

    int update(AdVO adVO);

    AdVO selectById(Integer adId);

    List<AdVO> selectAll();
}

