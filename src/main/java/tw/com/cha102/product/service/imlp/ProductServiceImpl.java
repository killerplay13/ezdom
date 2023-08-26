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
    private ProductDao dao;


    @Override
    public boolean addProduct(ProductVO productVO) {
        return dao.insert(productVO)>0;
    }
    @Override
    public List<ProductVO> findProductsByStatus(int productStatus){return dao.selectByStatus(productStatus);}
    @Override
    public ProductVO getById(Integer productId){
        if(productId !=null){
           return dao.selectById(productId);
        }else {
            return null;
        }
    }

    @Override
    public boolean editProduct(ProductVO productVO) {
        return dao.update(productVO)>0;
    }

    @Override
    public boolean upOrdownProduct(Integer productId) {
        ProductVO productVO = dao.selectById(productId);
        if(productVO.getProductStatus()==((byte) 1)){
            productVO.setProductStatus ((byte) 2);
        }else if(productVO.getProductStatus()==((byte) 2)) {
            productVO.setProductStatus((byte) 1);
        }
        return dao.updateToStatus(productVO)>0;
    }

    @Override
    public List<ProductVO> showProducts(Integer value) {
        return dao.selectBy12(value);
    }
    @Override
    public int findProductCount(){
        return dao.selectProductCount();
    }




}
