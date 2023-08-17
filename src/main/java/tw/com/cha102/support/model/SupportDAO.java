package tw.com.cha102.support.model;

import java.util.List;

public interface SupportDAO {
    void add(SupportVO supportVO);
    void update(SupportVO supportVO);
    void deleteById(Integer id);
    SupportVO findById(Integer id);
    List<SupportVO> findAll();
}
