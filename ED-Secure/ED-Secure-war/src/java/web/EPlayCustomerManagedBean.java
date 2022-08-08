/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import entity.EPlayCustomerDTO;
import session.EPlayUserManagementRemote;

@Named(value = "ePlayCustomerManagedBean")
@ConversationScoped
public class EPlayCustomerManagedBean implements Serializable {

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

    private String cusname;
    private String phone;
    private String address;
    private String email;
    private String rank;
    private int pbrought;

    /**
     * Creates a new instance of MyEmpManagedBean
     */
    public EPlayCustomerManagedBean() {
//        FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        userId = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        username = null;
        password = null;
        newPassword = null;
        confirmPassword = null;
        userGroup = null;
        active = false;

        cusname = null;
        phone = null;
        address = null;
        email = null;
        rank = null;
        pbrought = 0;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getPbrought() {
        return pbrought;
    }

    public void setPbrought(int pbrought) {
        this.pbrought = pbrought;
    }

    public void startConversation() {
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }

    public String setCustomerDetailsForChange() {
        // check empId is null
        if (isNull(userId) || conversation == null) {
            return "debug";
        }

        System.out.print("id is not null");
        if (!userManagement.hasCustomer(userId)) {
            System.out.print("cant find this employee");
            return "failure";
        }

        // note the startConversation of the conversation
        startConversation();

        // get employee details
        return setCustomerDetails();
    }

    public String changeCustomer() {
        // check empId is null
        if (isNull(userId)) {
            return "debug";
        }
        
        EPlayCustomerDTO empDTO = new EPlayCustomerDTO(userId, username, password,
                userGroup, active, cusname, phone, address, email, rank, pbrought);

        boolean result = userManagement.updateCustomerDetails(empDTO);

        // note the endConversation of the conversation
        endConversation();

        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }

    public void validateNewPassword(FacesContext context,
            UIComponent componentToValidate, Object value)
            throws ValidatorException {

        // get new password
        String oldPwd = (String) value;

        // get old password
        UIInput newPwdComponent = (UIInput) componentToValidate.getAttributes().get("newpwd");
        String newPwd = (String) newPwdComponent.getSubmittedValue();

        if (oldPwd.equals(newPwd)) {
            FacesMessage message = new FacesMessage(
                    "Old Password and New Password are the same! They must be different.");
            throw new ValidatorException(message);
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

    public void validateNewPasswordPair(FacesContext context,
            UIComponent componentToValidate,
            Object newValue) throws ValidatorException {

        // get new password
        String newPwd = (String) newValue;

        // get confirm password
        UIInput cnfPwdComponent = (UIInput) componentToValidate.getAttributes().get("cnfpwd");
        String cnfPwd = (String) cnfPwdComponent.getSubmittedValue();

        System.out.println("new password : " + newPwd);
        System.out.println("confirm password : " + cnfPwd);

        if (!newPwd.equals(cnfPwd)) {
            FacesMessage message = new FacesMessage(
                    "New Password and Confirm New Password are not the same! They must be the same.");
            throw new ValidatorException(message);
        }
    }

    public String changeCustomerPassword() {
        // check empId is null
        if (isNull(userId)) {
            return "debug";
        }

        // newPassword and confirmPassword are the same - checked by the validator during input to JSF form
        boolean result = userManagement.updateCustomerPassword(userId, newPassword);

        System.out.println("result = " + result);

        if (result) {
            return setCustomerDetails();
        } else {
            return "failure";
        }
    }

    public String displayCustomer() {
        // check empId is null
        if (isNull(userId) || conversation == null) {
            return "debug";
        }

        return setCustomerDetails();
    }

    private boolean isNull(String s) {
        return (s == null);
    }

    private String setCustomerDetails() {

        if (isNull(userId) || conversation == null) {
            return "debug";
        }

        EPlayCustomerDTO e = userManagement.getCustomerDetails(userId);

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

            this.cusname = e.getCusname();
            this.phone = e.getPhone();
            this.address = e.getAddress();
            this.email = e.getEmail();
            this.rank = e.getRank();
            this.pbrought = e.getpBrought();
            return "success";
        }
    }

    private boolean validAddEmployeeInfo() {
        return (userId != null && username != null && password != null 
                && userGroup != null && active != null && cusname != null 
                && phone != null && address != null && email != null  
                && rank != null && pbrought > 0);
    }

}
