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
public class EPlayUser implements Serializable{
    String userid;
    String name;
    String password;
    String userGroup;
    Boolean active;

    public EPlayUser(String userid, String name, String password, String userGroup, 
            Boolean active) {
        this.userid = userid;
        this.name = name;
        this.password = password;
        this.userGroup = userGroup;
        this.active = active;
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
}
