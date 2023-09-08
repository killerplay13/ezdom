package tw.com.cha102.employee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tw.com.cha102.employee.dto.LoginRequest;
import tw.com.cha102.employee.model.dao.EmployeeDAO;
import tw.com.cha102.employee.model.dao.EmployeeRepository;
import tw.com.cha102.employee.model.entity.EmployeeVO;
import tw.com.cha102.employee.service.EmployeeService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO dao;
    @Autowired
    private EmployeeRepository employeeRepository;




    @Override
    public boolean add(EmployeeVO employeeVO) {
        String employeePassword = employeeVO.getEmployeePassword();
        String hashPwd = sha256Hash(employeePassword);//把傳遞的密碼加密
        employeeVO.setEmployeePassword(hashPwd);
        return dao.insert(employeeVO)>0;
    }


    @Override
    public boolean edit(EmployeeVO employeeVO) {
        return dao.update(employeeVO)>0;
    }

    @Override
    public List<EmployeeVO> findAll() {
        return dao.selectAll();
    }

    @Override
    public boolean remove(Integer employeeId) {
        return dao.deleteById(employeeId)>0;
    }

    @Override
    public boolean save(EmployeeVO employeeVO) {
        return dao.update(employeeVO) > 0;
    }

    @Override
    public EmployeeVO getById(Integer EmployeeId) {
        if(EmployeeId !=null){
            return dao.selectById(EmployeeId);
        }else {
            return null;
        }
    }

    @Override
    public void login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {

        EmployeeVO employeeVO = employeeRepository.findByEmployeeId(loginRequest.getAccount());
        if (employeeVO == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "無此使用者");

        // 對使用者提供的密碼進行雜湊處理，然後與資料庫中儲存的雜湊密碼進行比較
        String hashReqPwd = sha256Hash(loginRequest.getPassword());
        if (!employeeVO.getEmployeePassword().equals(hashReqPwd))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "帳號密碼錯誤");

        HttpSession httpSession = request.getSession();
        // 添加 Cookie 到回應中
        Cookie sessionCookie = new Cookie("EMPLOYEEID", httpSession.getId());
        sessionCookie.setMaxAge(60 * 60); // 60 分鐘的過期時間
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);
        httpSession.setAttribute("employeeId", employeeVO.getEmployeeId()); // 保存目前登入的會員id，供後續使用
    }



    public String sha256Hash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            String sha256Hash = hexString.toString();
            return sha256Hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "密碼格式異常");
    }
}
