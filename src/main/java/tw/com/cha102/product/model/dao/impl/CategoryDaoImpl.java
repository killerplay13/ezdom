package tw.com.cha102.product.model.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import tw.com.cha102.order.model.entity.OrderDetailVO;
import tw.com.cha102.product.model.dao.CategoryDao;
import tw.com.cha102.product.model.entity.CategoryVO;

import javax.persistence.PersistenceContext;
import java.nio.channels.SeekableByteChannel;
import java.util.List;
@Repository
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext
    private Session session;

    @Override
    public int insert(CategoryVO categoryVO) {
        session.persist(categoryVO);
        return 1;
    }

    @Override
    public int deleteById(Integer categoryId) {
        CategoryVO category = session.load(CategoryVO.class, categoryId);
        session.remove(category);
        return 1;
    }

    @Override
    public int update(CategoryVO categoryVO) {
        return 0;
    }

    @Override
    public CategoryVO selectById(Integer categoryId) {
        return session.get(CategoryVO.class, categoryId);
    }

    @Override
    public List<CategoryVO> selectAll() {
        final String hql="FROM CategoryVO ORDER BY categoryId";
        return session.createQuery(hql, CategoryVO.class).getResultList();
    }
}
