package tw.com.cha102.employee.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.employee.model.dao.EmployeeDAO;
import tw.com.cha102.employee.model.entity.EmployeeVO;

import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @PersistenceContext
    private Session session;

    @Override
    public int insert(EmployeeVO employeeVO) {
        session.persist(employeeVO);
        return 1;
    }

    @Override
    public int update(EmployeeVO employeeVO) {
        StringBuilder hql = new StringBuilder()
                .append("UPDATE Employee SET ");

        String password = employeeVO.getEmployeePassword();
        if (password != null && !password.isEmpty()) {
            hql.append("employeePassword = :password, ");
        }
        hql.append("employeeStatus = :status, ")
                .append("employeeName = :name, ")
                .append("hiredate = :hiredate, ")
                .append("employeeEmail = :email, ")
                .append("employeePhone = :phone ")
                .append("WHERE employeeId = :id");


        Query<?> query = session.createQuery(hql.toString());

        if (Objects.nonNull(password) && !password.isEmpty()) {
            query.setParameter("password", password);
        }

        query.setParameter("status", employeeVO.getEmployeeStatus())
                .setParameter("name", employeeVO.getEmployeeName())
                .setParameter("hiredate", employeeVO.getHiredate())
                .setParameter("email", employeeVO.getEmployeeEmail())
                .setParameter("phone", employeeVO.getEmployeePhone())
                .setParameter("id", employeeVO.getEmployeeId());

        return query.executeUpdate();
    }


    @Override
    public int deleteById(Integer employeeId) {
        EmployeeVO employeeVO = session.get(EmployeeVO.class, employeeId);
        session.remove(employeeId);
        return 1;
    }

    @Override
    public List<EmployeeVO> selectAll() {
        final String hql = "FROM EMPLOYEE ORDER BY EMPLOYEE";
        return session.createQuery(hql, EmployeeVO.class).getResultList();
    }

    @Override
    public EmployeeVO selectById(Integer employeeId) {
        return session.get(EmployeeVO.class, employeeId);
    }
}
