package tw.com.cha102.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tw.com.cha102.employee.model.dao.EmployeeRepository;
import tw.com.cha102.employee.model.entity.EmployeeVO;
import tw.com.cha102.employee.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/backend/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/getById")
    public EmployeeVO getEmployeeById(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String employeeId = (String) session.getAttribute("employeeId");
        EmployeeVO employee = employeeRepository.findByEmployeeId(employeeId);

        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "該員工不存在");
        }

        return employee;
    }


}
