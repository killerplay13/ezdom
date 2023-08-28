package tw.com.cha102.employee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.cha102.employee.model.dao.EmployeeDAO;
import tw.com.cha102.employee.model.entity.EmployeeVO;
import tw.com.cha102.employee.service.EmployeeService;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO dao;

    @Override
    public EmployeeVO register(EmployeeVO employeeVO) {
        return null;
    }

    @Override
    public EmployeeVO login(EmployeeVO employeeVO) {
        return null;
    }

    @Override
    public EmployeeVO edit(EmployeeVO employeeVO) {
        return null;
    }

    @Override
    public List<EmployeeVO> findAll() {
        return null;
    }

    @Override
    public boolean remove(Integer employeeId) {
        return false;
    }

    @Override
    public boolean save(EmployeeVO employeeVO) {
        return false;
    }
}
