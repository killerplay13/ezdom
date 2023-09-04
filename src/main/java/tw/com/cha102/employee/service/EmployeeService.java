package tw.com.cha102.employee.service;

import tw.com.cha102.employee.model.entity.EmployeeVO;
import tw.com.cha102.employee.dto.LoginRequest;
import tw.com.cha102.product.model.entity.ProductVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface EmployeeService {

    public boolean add(EmployeeVO employeeVO);

    public boolean edit(EmployeeVO employeeVO);

    public List<EmployeeVO> findAll();

    boolean remove(Integer employeeId);

    boolean save(EmployeeVO employeeVO);

//    EmployeeVO getById(Integer EmployeeId);

    EmployeeVO getById(Integer EmployeeId);

    void  login(LoginRequest loginRequest , HttpServletRequest request, HttpServletResponse response);
}

