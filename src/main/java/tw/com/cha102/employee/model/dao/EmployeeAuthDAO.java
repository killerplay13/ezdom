package tw.com.cha102.employee.model.dao;

import tw.com.cha102.employee.model.entity.EmployeeAuthVO;

import java.util.List;

public interface EmployeeAuthDAO {

    public int insert(EmployeeAuthVO employeeAuthVO);

    public int deleteBySN(Integer serialNumber);

    public int update(EmployeeAuthVO employeeAuthVO);

    public EmployeeAuthVO selectById(Integer serialNumber);

    public List<EmployeeAuthVO> selectAll();


}
