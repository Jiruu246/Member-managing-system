/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author thinh
 */
public class EPlayEmployeeDTO {

    String userid;
    String name;
    String password;
    String userGroup;
    Boolean active;
    String Empname;
    String Phone;
    String Address;
    String Email;
    Double Salary;

    public EPlayEmployeeDTO(String userid, String name, String password,
            String userGroup, Boolean active, String Empname, String Phone,
            String Address, String Email, Double Salary) {
        this.userid = userid;
        this.name = name;
        this.password = password;
        this.userGroup = userGroup;
        this.active = active;
        this.Empname = Empname;
        this.Phone = Phone;
        this.Address = Address;
        this.Email = Email;
        this.Salary = Salary;
    }

    public String getUserid() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public Boolean isActive() {
        return active;
    }

    public String getEmpname() {
        return Empname;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmail() {
        return Email;
    }

    public Double getSalary() {
        return Salary;
    }

}
