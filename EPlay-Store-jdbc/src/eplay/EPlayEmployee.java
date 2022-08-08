/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplay;

import java.io.Serializable;

/**
 *
 * @author thinh
 */
public class EPlayEmployee extends EPlayUser implements Serializable {

    String Empname;
    String Phone;
    String Address;
    String Email;
    Double Salary;

    public EPlayEmployee(String userid, String name, String password, String userGroup, Boolean active, String Empname, String Phone, String Address, String Email, Double Salary) {
        super(userid, name, password, userGroup, active);
        this.Empname = Empname;
        this.Phone = Phone;
        this.Address = Address;
        this.Email = Email;
        this.Salary = Salary;
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
