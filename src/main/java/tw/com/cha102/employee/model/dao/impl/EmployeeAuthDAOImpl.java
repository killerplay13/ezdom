//package tw.com.cha102.employee.model.dao.impl;
//
//import org.hibernate.Session;
//import org.springframework.stereotype.Repository;
//import tw.com.cha102.employee.model.dao.EmployeeAuthDAO;
//import tw.com.cha102.employee.model.entity.EmployeeAuthVO;
//
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Repository
//public class EmployeeAuthDAOImpl implements EmployeeAuthDAO {
//
//    @PersistenceContext
//    private Session session;
//
//    @Override
//    public int insert(EmployeeAuthVO employeeAuthVO) {
//        session.save(employeeAuthVO);
//        return 1;
//    }
//
//    @Override
//    public int deleteBySN(Integer serialNumber) {
//
//        EmployeeAuthVO employeeAuthVO = session.get(EmployeeAuthVO.class, serialNumber);
//        if (employeeAuthVO != null) {
//            session.delete(employeeAuthVO);
//            return 1;
//        }
//        return 0;
//    }
//
//    @Override
//    public int update(EmployeeAuthVO employeeAuthVO) {
//        session.update(employeeAuthVO);
//        return 1;
//    }
//
//    @Override
//    public EmployeeAuthVO selectById(Integer serialNumber) {
//        return session.get(EmployeeAuthVO.class, serialNumber);
//    }
//
//    @Override
//    public List<EmployeeAuthVO> selectAll() {
//        return session.createQuery("from EmployeeAuthVO", EmployeeAuthVO.class).list();
//    }
//
//}