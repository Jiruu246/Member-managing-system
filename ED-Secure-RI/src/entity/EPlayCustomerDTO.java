/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author thinh
 */
public class EPlayCustomerDTO implements Serializable {

    String userid;
    String name;
    String password;
    String userGroup;
    Boolean active;
    String Cusname;
    String Phone;
    String Address;
    String Email;
    String Rank;
    int pBrought;

    public EPlayCustomerDTO(String userid, String name, String password, 
            String userGroup, Boolean active, String Cusname, String Phone, 
            String Address, String Email, String Rank, int pBrought) {
        this.userid = userid;
        this.name = name;
        this.password = password;
        this.userGroup = userGroup;
        this.active = active;
        
        this.Cusname = Cusname;
        this.Phone = Phone;
        this.Address = Address;
        this.Email = Email;
        this.Rank = Rank;
        this.pBrought = pBrought;
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

    public String getCusname() {
        return Cusname;
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

    public String getRank() {
        return Rank;
    }

    public int getpBrought() {
        return pBrought;
    }
   
}
