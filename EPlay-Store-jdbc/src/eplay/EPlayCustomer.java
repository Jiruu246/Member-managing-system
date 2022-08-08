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
public class EPlayCustomer extends EPlayUser implements Serializable {

    String Cusname;
    String Phone;
    String Address;
    String Email;
    String Rank;
    int pBrought;

    public EPlayCustomer(String userid, String name, String password, String userGroup, Boolean active, String Cusname, String Phone, String Address, String Email, String Rank, int pBrought) {
        super(userid, name, password, userGroup, active);
        this.Cusname = Cusname;
        this.Phone = Phone;
        this.Address = Address;
        this.Email = Email;
        this.Rank = Rank;
        this.pBrought = pBrought;
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
