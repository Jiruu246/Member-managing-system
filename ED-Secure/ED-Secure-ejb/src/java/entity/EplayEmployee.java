/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thinh
 */
@Entity
@PrimaryKeyJoinColumn(name = "EMPID")
@DiscriminatorValue("EPLAY-ADMIN")
@Table(name = "EPLAY_EMPLOYEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EplayEmployee.findAll", query = "SELECT e FROM EplayEmployee e"),
//    @NamedQuery(name = "EplayEmployee.findByEmpid", query = "SELECT e FROM EplayEmployee e WHERE e.empid = :empid"),
    @NamedQuery(name = "EplayEmployee.findByEmpname", query = "SELECT e FROM EplayEmployee e WHERE e.empname = :empname"),
    @NamedQuery(name = "EplayEmployee.findByPhone", query = "SELECT e FROM EplayEmployee e WHERE e.phone = :phone"),
    @NamedQuery(name = "EplayEmployee.findByAddress", query = "SELECT e FROM EplayEmployee e WHERE e.address = :address"),
    @NamedQuery(name = "EplayEmployee.findByEmail", query = "SELECT e FROM EplayEmployee e WHERE e.email = :email"),
    @NamedQuery(name = "EplayEmployee.findBySalary", query = "SELECT e FROM EplayEmployee e WHERE e.salary = :salary")})
public class EplayEmployee extends EplayUser implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 5)
//    @Column(name = "EMPID")
//    private String empid;
    @Size(max = 25)
    @Column(name = "EMPNAME")
    private String empname;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 10)
    @Column(name = "PHONE")
    private String phone;
    @Size(max = 30)
    @Column(name = "ADDRESS")
    private String address;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "EMAIL")
    private String email;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SALARY")
    private double salary;
    @JoinColumn(name = "EMPID", referencedColumnName = "USERID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private EplayUser eplayUser;

    public EplayEmployee() {
    }
    
    public EplayEmployee(String userid, String name, String password,
            String userGroup, Boolean active, String Empname, String Phone,
            String Address, String Email, Double Salary){
        setUserid(userid);
        setUsername(name);
        setPassword(password);
        setUsergroup(userGroup);
        setActive(active);
//        this.empid = userid;
        this.empname = Empname;
        this.phone = Phone;
        this.address = Address;
        this.email = Email;
        this.salary = Salary;
    }

//    public EplayEmployee(String empid) {
//        this.empid = empid;
//    }
//
//    public String getEmpid() {
//        return empid;
//    }
//
//    public void setEmpid(String empid) {
//        this.empid = empid;
//    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
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

    public EplayUser getEplayUser() {
        return eplayUser;
    }

    public void setEplayUser(EplayUser eplayUser) {
        this.eplayUser = eplayUser;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (empid != null ? empid.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof EplayEmployee)) {
//            return false;
//        }
//        EplayEmployee other = (EplayEmployee) object;
//        if ((this.empid == null && other.empid != null) || (this.empid != null && !this.empid.equals(other.empid))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "entity.EplayEmployee[ empid=" + empid + " ]";
//    }
    
}
