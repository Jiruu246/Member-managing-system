/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.EPlayEmployeeDTO;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import session.EPlayUserManagementRemote;

/**
 *
 * @author thinh
 */
@Named(value = "ePlayEmployeeManagedBean")
@ConversationScoped
public class EPlayEmployeeManagedBean implements Serializable {

    @Inject
    private Conversation conversation;
    @EJB
    private EPlayUserManagementRemote userManagement;

    private String userId;
    private String username;
    private String password;
    private String newPassword;
    private String confirmPassword;
    private String userGroup;
    private Boolean active;

    private String empName;
    private String phone;
    private String address;
    private String email;
    private double salary;

    public EPlayEmployeeManagedBean() {
        userId = null;
        username = null;
        password = null;
        newPassword = null;
        confirmPassword = null;
        userGroup = null;
        active = false;
        empName = null;
        phone = null;
        address = null;
        email = null;
        salary = 0.0;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void startConversation() {
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }

    public String setEmployeeDetailsForChange() {
        // check empId is null
        if (isNull(userId) || conversation == null) {
            return "debug";
        }

        if (!userManagement.hasEmployee(userId)) {
            return "failure";
        }

        // note the startConversation of the conversation
        startConversation();

        // get employee details
        return setEmployeeDetails();

    }

    public String changeEmployee() {
        // check empId is null
        if (isNull(userId)) {
            return "debug";
        }

        EPlayEmployeeDTO empDTO = new EPlayEmployeeDTO(userId, username, password,
                userGroup, active, empName, phone, address, email, salary);

        boolean result = userManagement.updateEmpolyeeDetails(empDTO);

        // note the endConversation of the conversation
        endConversation();

        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }

    public void validatePasswordPair(FacesContext context,
            UIComponent componentToValidate,
            Object pwdValue) throws ValidatorException {

        // get password
        String pwd = (String) pwdValue;

        // get confirm password
        UIInput cnfPwdComponent = (UIInput) componentToValidate.getAttributes().get("cnfpwd");
        String cnfPwd = (String) cnfPwdComponent.getSubmittedValue();

        System.out.println("password : " + pwd);
        System.out.println("confirm password : " + cnfPwd);

        if (!pwd.equals(cnfPwd)) {
            FacesMessage message = new FacesMessage(
                    "Password and Confirm Password are not the same! They must be the same.");
            throw new ValidatorException(message);
        }
    }

    public String changeEmployeePassword() {
        // check empId is null
        if (isNull(userId)) {
            return "debug";
        }

        // newPassword and confirmPassword are the same - checked by the validator during input to JSF form
        boolean result = userManagement.updateEmployeePassword(userId, newPassword);

        System.out.println("result = " + result);

        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }

    private boolean isNull(String s) {
        return (s == null);
    }

    private String setEmployeeDetails() {

        if (isNull(userId) || conversation == null) {
            return "debug";
        }

        EPlayEmployeeDTO e = userManagement.getEmployeeDetails(userId);

        if (e == null) {
            // no such employee
            return "failure";
        } else {
            // found - set details for display
            this.userId = e.getUserid();
            this.username = e.getName();
            this.password = e.getPassword();
            this.userGroup = e.getUserGroup();
            this.active = e.isActive();

            this.empName = e.getEmpname();
            this.phone = e.getPhone();
            this.address = e.getAddress();
            this.email = e.getEmail();
            this.salary = e.getSalary();
            return "success";
        }
    }

    private boolean validAddEmployeeInfo() {
        return (userId != null && username != null && password != null && userGroup != null
                && active!= null && empName != null && phone != null && address != null
                && email != null && salary >= 0.0);
    }
}
