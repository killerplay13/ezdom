package tw.com.cha102.employee.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee_auth",catalog ="cha102g4_test")
public class EmployeeAuthVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SERIAL_NUMBER")
    private int serialNumber;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private EmployeeVO employeeId;

    @ManyToOne
    @JoinColumn(name = "PERMISSION_ID")
    private EmployeePermissionVO permission;
    private EmployeeVO employee;

    public EmployeeAuthVO(int serialNumber, EmployeeVO employeeId, EmployeePermissionVO permission, EmployeeVO employee) {
        this.serialNumber = serialNumber;
        this.employeeId = employeeId;
        this.permission = permission;
        this.employee = employee;
    }

// Constructors, getters and setters

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public EmployeeVO getEmployee() {
        return employeeId;
    }

    public void setEmployee(EmployeeVO employee) {
        this.employee = employee;
    }

    public EmployeePermissionVO getPermission() {
        return permission;
    }

    public void setPermission(EmployeePermissionVO permission) {
        this.permission = permission;
    }
}

