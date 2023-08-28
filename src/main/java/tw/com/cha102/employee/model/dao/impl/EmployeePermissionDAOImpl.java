package tw.com.cha102.employee.model.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import tw.com.cha102.employee.model.dao.EmployeePermissionDAO;
import tw.com.cha102.employee.model.entity.EmployeePermissionVO;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeePermissionDAOImpl implements EmployeePermissionDAO {

    @PersistenceContext
    private Session session;

    @Override
    public int insert(EmployeePermissionVO employeePermissionVO) {
        session.save(employeePermissionVO);
        return 1;
    }

    @Override
    public int update(EmployeePermissionVO employeePermissionVO) {
        session.update(employeePermissionVO);
        return 1;
    }

    @Override
    public int deleteBySN(Integer serialNumber) {
        EmployeePermissionVO permissionVO = session.get(EmployeePermissionVO.class, serialNumber);
        if (permissionVO != null) {
            session.delete(permissionVO);
            return 1;
        }
        return 0;
    }

    @Override
    public List<EmployeePermissionVO> selectAll() {
        return session.createQuery("from EmployeePermissionVO", EmployeePermissionVO.class).list();
    }

    @Override
    public EmployeePermissionVO selectById(Integer serialNumber) {
        return session.get(EmployeePermissionVO.class, serialNumber);
    }


}
