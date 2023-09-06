package tw.com.cha102.employee.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.com.cha102.employee.model.entity.EmployeeVO;
import tw.com.cha102.member.model.entity.Member;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeVO, Integer> {
    EmployeeVO findByEmployeeId(String account) ;

    @Query("SELECT MAX(e.employeeId) FROM EmployeeVO e")
    Integer findLastEmployeeId();


}
