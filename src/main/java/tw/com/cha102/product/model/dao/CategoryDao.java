package tw.com.cha102.product.model.dao;


import tw.com.cha102.product.model.entity.CategoryVO;

import java.util.List;

public interface CategoryDao {

    int insert(CategoryVO categoryVO);

    int deleteById(Integer categoryId);

    int update(CategoryVO categoryVO);

    CategoryVO selectById(Integer categoryId);

    List<CategoryVO> selectAll();
}
