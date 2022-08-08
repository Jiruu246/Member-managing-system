/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EPlayCustomerDTO;
import entity.EPlayEmployeeDTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import entity.EplayCustomer;
import entity.EplayEmployee;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;

@DeclareRoles({"EPLAY-ADMIN", "EPLAY-USER"})
@Stateless
public class EPlayUserManagement implements EPlayUserManagementRemote {

    @EJB
    private EPlayEmployeeFacadeLocal employeeFacade;

    @EJB
    private EPlayCustomerFacadeLocal customerFacade;

    private EplayEmployee employeeDTO2Entity(EPlayEmployeeDTO empDTO) {
        if (empDTO == null) {
            // just in case
            return null;
        }
        String userid = empDTO.getUserid();
        String name = empDTO.getName();
        String password = empDTO.getPassword();
        String userGroup = empDTO.getUserGroup();
        boolean active = empDTO.isActive();

        String Empname = empDTO.getEmpname();
        String Phone = empDTO.getPhone();
        String Address = empDTO.getAddress();
        String Email = empDTO.getEmail();
        double Salary = empDTO.getSalary();

        EplayEmployee employee = new EplayEmployee(userid, name, password,
                userGroup, active, Empname, Phone,
                Address, Email, Salary);

        return employee;
    }

    private EplayCustomer customerDTO2Entity(EPlayCustomerDTO empDTO) {
        if (empDTO == null) {
            // just in case
            return null;
        }

        String userid = empDTO.getUserid();
        String name = empDTO.getName();
        String password = empDTO.getPassword();
        String userGroup = empDTO.getUserGroup();
        boolean active = empDTO.isActive();

        String Cusname = empDTO.getCusname();
        String Phone = empDTO.getPhone();
        String Address = empDTO.getAddress();
        String Email = empDTO.getEmail();
        String Rank = empDTO.getRank();
        int pBrought = empDTO.getpBrought();

        EplayCustomer employee = new EplayCustomer(userid, name, password,
                userGroup, active, Cusname, Phone,
                Address, Email, Rank, pBrought);

        return employee;
    }

    private EPlayEmployeeDTO employeeEntity2DTO(EplayEmployee employee) {
        if (employee == null) {
            // just in case
            return null;
        }

        EPlayEmployeeDTO empDTO = new EPlayEmployeeDTO(
                employee.getUserid(),
                employee.getUsername(),
                employee.getPassword(),
                employee.getUsergroup(),
                employee.isActive(),
                employee.getEmpname(),
                employee.getPhone(),
                employee.getAddress(),
                employee.getEmail(),
                employee.getSalary()
        );

        return empDTO;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    /**
     * check whether the employee is in the system
     *
     * @param empId
     * @return true if the employee is in the system, false otherwise
     */
    @Override
    @RolesAllowed({"EPLAY-ADMIN"})
    public boolean hasEmployee(String empId) {
        return employeeFacade.hasEmployee(empId);
    }

    @Override
    @RolesAllowed({"EPLAY-ADMIN", "EPLAY-USER"})
    public boolean hasCustomer(String empId) {
        return customerFacade.hasCustomer(empId);
    }

    /**
     * add an employee to the system
     *
     * @param empDTO
     * @return true if addition is successful, false otherwise
     */
    @Override
    @RolesAllowed({"EPLAY-ADMIN"})
    public boolean addEmployee(EPlayEmployeeDTO empDTO) {

        if (empDTO == null) {
            // just in case
            return false;
        }

        // check employee exist?
        if (hasEmployee(empDTO.getUserid())) {
            // employee exists, cannot add one
            return false;
        }

        // employee not exist
        // convert to entity
        EplayEmployee emp = this.employeeDTO2Entity(empDTO);
        // add one
        return employeeFacade.addEmployee(emp);
    }

    /**
     * update employee details without updating password
     *
     * @param empDTO
     * @return true if update is successful, false otherwise
     */
    @Override
    @RolesAllowed({"EPLAY-ADMIN"})
    public boolean updateEmpolyeeDetails(EPlayEmployeeDTO empDTO) {
        // check employee exist?
        if (!hasEmployee(empDTO.getUserid())) {
            return false;
        }

        // employee exist, update details
        // convert to entity class
        EplayEmployee employee = this.employeeDTO2Entity(empDTO);
        // update details
        return employeeFacade.updateEmployeeDetails(employee);
    }

    @Override
    @RolesAllowed({"EPLAY-ADMIN", "EPLAY-USER"})
    public boolean updateCustomerDetails(EPlayCustomerDTO empDTO) {
        // check employee exist?
        if (!hasCustomer(empDTO.getUserid())) {
            return false;
        }

        // employee exist, update details
        // convert to entity class
        EplayCustomer employee = this.customerDTO2Entity(empDTO);
        // update details
        return customerFacade.updateCustomerDetails(employee);
    }

    /**
     * update employee's password
     *
     * @param empId
     * @param newPassword
     * @return true if update successful, false otherwise
     */
    @Override
    @RolesAllowed({"EPLAY-ADMIN"})
    public boolean updateEmployeePassword(String empId, String newPassword) {
        return employeeFacade.updatePassword(empId, newPassword);
    }

    @Override
    @RolesAllowed({"EPLAY-ADMIN", "EPLAY-USER"})
    public boolean updateCustomerPassword(String empId, String newPassword) {
        return customerFacade.updatePassword(empId, newPassword);
    }

    /**
     * get employee details and use a DTO to transmit the details
     *
     * @param empId
     * @return a DTO containing the information of the employee if exists, null
     * otherwise
     */
    @Override
    @RolesAllowed({"EPLAY-ADMIN", "EPLAY-USER"})
    public EPlayEmployeeDTO getEmployeeDetails(String empId) {
        // get the employee
        EplayEmployee employee = employeeFacade.find(empId);

        if (employee == null) {
            // not found - no such employee
            return null;
        } else {
            // found - prepare details
            EPlayEmployeeDTO empDTO = new EPlayEmployeeDTO(
                    employee.getUserid(),
                    employee.getUsername(),
                    employee.getPassword(),
                    employee.getUsergroup(),
                    employee.isActive(),
                    employee.getEmpname(),
                    employee.getPhone(),
                    employee.getAddress(),
                    employee.getEmail(),
                    employee.getSalary()
            );

            return empDTO;
        }
    }

    @Override
    @RolesAllowed({"EPLAY-USER"})
    public EPlayCustomerDTO getCustomerDetails(String empId) {
        // get the employee
        EplayCustomer employee = customerFacade.find(empId);

        if (employee == null) {
            // not found - no such employee
            return null;
        } else {
            // found - prepare details
            EPlayCustomerDTO empDTO = new EPlayCustomerDTO(
                    employee.getUserid(),
                    employee.getUsername(),
                    employee.getPassword(),
                    employee.getUsergroup(),
                    employee.isActive(),
                    employee.getCusname(),
                    employee.getPhone(),
                    employee.getAddress(),
                    employee.getEmail(),
                    employee.getRank(),
                    Integer.parseInt(employee.getPbrought())
            );

            return empDTO;
        }
    }

    /**
     * set the employee's active status to false
     *
     * @param empId
     * @return true if it can be set to inactive and have set to inactive; false
     * otherwise
     */
    @Override
    @RolesAllowed({"EPLAY-ADMIN"})
    public boolean deleteEmployee(String empId) {
        return employeeFacade.deleteEmployee(empId);
    }

    /**
     * physically remove an employee's record from database
     *
     * This is for lab purposes - never ever do this in real world applications
     *
     * @param empId
     * @return true if the employee record has been physically removed from the
     * database, false otherwise
     */
    @Override
    @RolesAllowed({"EPLAY-ADMIN"})
    public boolean removeEmployee(String empId) {
        return employeeFacade.removeEmployee(empId);
    }

}
