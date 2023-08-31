package tw.com.cha102.employee.service;

import tw.com.cha102.employee.model.entity.EmployeeVO;

import java.util.List;

public interface EmployeeService {

    EmployeeVO register(EmployeeVO employeeVO);

    EmployeeVO login(EmployeeVO employeeVO);

    EmployeeVO edit(EmployeeVO employeeVO);

    List<EmployeeVO> findAll();

    boolean remove(Integer employeeId);

    boolean save(EmployeeVO employeeVO);
}

