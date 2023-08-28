package tw.com.cha102.employee.model.dao;

import tw.com.cha102.employee.model.entity.EmployeePermissionVO;
import tw.com.cha102.employee.model.entity.EmployeeVO;

import java.util.List;

public interface EmployeePermissionDAO {
    public int insert(EmployeePermissionVO employeePermissionVO);

    public int update(EmployeePermissionVO employeePermissionVO);

    public int deleteBySN(Integer serialNumber);

    public List<EmployeePermissionVO> selectAll();

    public EmployeePermissionVO selectById(Integer serialNumber);

}
