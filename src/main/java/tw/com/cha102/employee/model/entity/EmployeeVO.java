package tw.com.cha102.employee.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEE",catalog ="cha102g4_test")
public class EmployeeVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private int employeeId;

    @Column(name = "EMPLOYEE_STATUS")
    private int employeeStatus;

    @Column(name = "EMPLOYEE_PASSWORD")
    private String employeePassword;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    @Column(name = "HIREDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hiredate;

    @Column(name = "EMPLOYEE_EMAIL")
    private String employeeEmail;

    @Column(name = "EMPLOYEE_PHONE")
    private String employeePhone;

    public EmployeeVO(int employeeId, int employeeStatus, String employeePassword, String employeeName, Date hiredate, String employeeEmail, String employeePhone) {
        this.employeeId = employeeId;
        this.employeeStatus = employeeStatus;
        this.employeePassword = employeePassword;
        this.employeeName = employeeName;
        this.hiredate = hiredate;
        this.employeeEmail = employeeEmail;
        this.employeePhone = employeePhone;
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(int employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }
}