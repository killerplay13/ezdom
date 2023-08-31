package tw.com.cha102.employee.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tw.com.cha102.core.vo.Core;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "EMPLOYEE",catalog ="cha102g4_test")
public class EmployeeVO extends Core {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Integer employeeId;

    @Column(name = "EMPLOYEE_STATUS")
    private byte employeeStatus;

    @Column(name = "EMPLOYEE_PASSWORD")
    private String employeePassword;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;


    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "HIREDATE")
    private Date hiredate;

    @Column(name = "EMPLOYEE_EMAIL")
    private String employeeEmail;

    @Column(name = "EMPLOYEE_PHONE")
    private String employeePhone;

    @Column(name = "EMPLOYEE_POSITION")
    private byte employeePosition;



    public Integer getEmployeeId() {
        return employeeId;
    }

}