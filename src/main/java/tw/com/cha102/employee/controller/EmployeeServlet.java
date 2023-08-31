package tw.com.cha102.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.employee.model.entity.EmployeeVO;
import tw.com.cha102.employee.service.EmployeeService;
import tw.com.cha102.member.dto.CommonResponse;
import tw.com.cha102.employee.dto.LoginRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employee")
public class EmployeeServlet {

    @Autowired
    private EmployeeService service;

        @PostMapping("/login")
        public CommonResponse<String> login(@RequestBody @Valid LoginRequest loginRequest,
                                            HttpServletRequest request,
                                            HttpServletResponse response
        ) {
            service.login(loginRequest,request, response);
            return new CommonResponse("登入成功");
        }
        @PostMapping("/add")
        public EmployeeVO addEmployee(@RequestBody EmployeeVO employeeVO){
            EmployeeVO vo=new EmployeeVO();
            employeeVO.setEmployeeStatus((byte)employeeVO.getEmployeeStatus());
            if(service.add(employeeVO) == true){
                vo.setSuccessful(true);
                vo.setMessage("新增成功");
                return vo;
            }else{
                vo.setSuccessful(false);
                vo.setMessage("新增失敗 請重新輸入");
                return vo;
            }
        }

        @PostMapping("/edit/{employeeId}")
        public  EmployeeVO editEmployee(@RequestBody EmployeeVO employeeVO,@PathVariable Integer employeeId){
            employeeVO.setEmployeeId(employeeId);
            if (service.edit(employeeVO)) {
                employeeVO.setSuccessful(true);
                employeeVO.setMessage("新增成功");
                return employeeVO;
            } else {
                employeeVO.setSuccessful(false);
                employeeVO.setMessage("新增失敗 請重新輸入");
                return employeeVO;
            }
        }

        @GetMapping("/all")
        public List<EmployeeVO> listAllEmployees() {
            List<EmployeeVO> employeeList = (List<EmployeeVO>) service.findAll();
            return employeeList;
        }

//        @DeleteMapping("/delete/{employeeId}")
//        public ResponseEntity<String> deleteEmployee(@PathVariable Integer employeeId) {
//            if (service.remove(employeeId)) {
//                return ResponseEntity.ok("員工資料已刪除");
//            } else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("刪除員工資料失敗");
//            }
//        }

//    @DeleteMapping("/employee/delete/{employeeId}")
//    public ResponseEntity<String> deleteEmployee(@PathVariable Integer employeeId) {
//        // 先刪除與員工相關的其他資料（例如 employee_auth 表中的記錄）
//        boolean authDeleted = employeeAuthService.removeByEmployeeId(employeeId); // 假設有一個名為 employeeAuthService 的服務
//
//        if (authDeleted) {
//            if (service.remove(employeeId)) {
//                return ResponseEntity.ok("員工資料已刪除");
//            } else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("刪除員工資料失敗");
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("刪除員工相關資料失敗");
//        }
//    }


//        @PostMapping("/login")
//        public








}
