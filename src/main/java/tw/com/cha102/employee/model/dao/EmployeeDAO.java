package tw.com.cha102.employee.model.dao;

import tw.com.cha102.employee.model.entity.EmployeeVO;

import java.util.List;

public interface EmployeeDAO {
    public int insert(EmployeeVO employeeVO);

    public int update(EmployeeVO employeeVO);

    public int deleteById(Integer employeeId);

    public List<EmployeeVO> selectAll();

    public EmployeeVO selectById(Integer employeeId);


}