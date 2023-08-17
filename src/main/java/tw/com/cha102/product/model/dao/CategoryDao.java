package tw.com.cha102.product.model.dao;


import tw.com.cha102.product.model.entity.CategoryVO;

import java.util.List;

public interface CategoryDao {

    public int insert(CategoryVO categoryVO);

    public int deleteById(Integer categoryId);

    public int update(CategoryVO categoryVO);

    public CategoryVO selectById(Integer categoryId);

    public List<CategoryVO> selectAll();
}
