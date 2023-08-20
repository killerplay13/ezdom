package tw.com.cha102.product.service.imlp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.cha102.product.model.dao.ProductDao;
import tw.com.cha102.product.model.entity.ProductVO;
import tw.com.cha102.product.service.ProductService;

import java.util.List;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao dao;


    @Override
    public boolean addProduct(ProductVO productVO) {
        return dao.insert(productVO)>0;
    }

    public List<ProductVO> findProduct(int productStatus){return dao.selectByStatus(productStatus);}
}
