package tw.com.cha102.ad.model.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import tw.com.cha102.ad.model.dao.AdDAO;

import tw.com.cha102.ad.model.entity.AdVO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AdDAOImpl implements AdDAO {

    @PersistenceContext
    private Session session;

    @Override
    public int insert(AdVO adVO) {
        session.persist(adVO);
        return 1;
    }

    @Override
    public int deleteById(Integer adId) {
        AdVO adVO = session.find(AdVO.class, adId);
        if (adVO != null) {
            session.remove(adVO);
            return 1;
        }
        return 0;
    }

    @Override
    public int update(AdVO adVO) {
        session.merge(adVO);
        return 1;
    }

    @Override
    public AdVO selectById(Integer adId) {
        return session.find(AdVO.class, adId);
    }

    @Override
    public List<AdVO> selectAll() {
        return session.createQuery("SELECT a FROM AdVO a", AdVO.class)
                .getResultList();
    }
}
