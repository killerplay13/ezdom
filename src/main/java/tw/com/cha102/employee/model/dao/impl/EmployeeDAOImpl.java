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
                .append("UPDATE EmployeeVO SET ");

        String password = employeeVO.getEmployeePassword();
        if (password != null && !password.isEmpty()) {
            hql.append("employeePassword = :password, ");
        }
        hql.append("employeeStatus = :status, ")
                .append("employeeName = :name, ")
                .append("hiredate = :hiredate, ")
                .append("employeeEmail = :email, ")
                .append("employeePhone = :phone, ")
                .append("employeePosition = :position ")
                .append("WHERE employeeId = :id");


        Query<?> query = session.createQuery(hql.toString());

        if (Objects.nonNull(password) && !password.isEmpty()) {
            query.setParameter("password", employeeVO.getEmployeePassword());
        }

        query.setParameter("status", employeeVO.getEmployeeStatus())
                .setParameter("name", employeeVO.getEmployeeName())
                .setParameter("hiredate", employeeVO.getHiredate())
                .setParameter("email", employeeVO.getEmployeeEmail())
                .setParameter("phone", employeeVO.getEmployeePhone())
                .setParameter("position", employeeVO.getEmployeePosition())
                .setParameter("id", employeeVO.getEmployeeId());

        return query.executeUpdate();
    }


    @Override
    public int deleteById(Integer employeeId) {
        EmployeeVO employeeVO = session.get(EmployeeVO.class, employeeId);
        if (employeeVO != null) {
            session.remove(employeeVO);
            return 1;
        }
        return 0;
    }

    @Override
    public List<EmployeeVO> selectAll() {
        final String hql = "FROM EmployeeVO ORDER BY employeeId";
        return session.createQuery(hql, EmployeeVO.class).getResultList();
    }


    @Override
    public EmployeeVO selectById(Integer employeeId) {
        return session.get(EmployeeVO.class, employeeId);
    }



}
