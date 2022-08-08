/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thinh
 */
@Entity
@PrimaryKeyJoinColumn(name = "CUSID")
@DiscriminatorValue("EPLAY-USER")
@Table(name = "EPLAY_CUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EplayCustomer.findAll", query = "SELECT e FROM EplayCustomer e"),
//    @NamedQuery(name = "EplayCustomer.findByCusid", query = "SELECT e FROM EplayCustomer e WHERE e.cusid = :cusid"),
    @NamedQuery(name = "EplayCustomer.findByCusname", query = "SELECT e FROM EplayCustomer e WHERE e.cusname = :cusname"),
    @NamedQuery(name = "EplayCustomer.findByPhone", query = "SELECT e FROM EplayCustomer e WHERE e.phone = :phone"),
    @NamedQuery(name = "EplayCustomer.findByAddress", query = "SELECT e FROM EplayCustomer e WHERE e.address = :address"),
    @NamedQuery(name = "EplayCustomer.findByEmail", query = "SELECT e FROM EplayCustomer e WHERE e.email = :email"),
    @NamedQuery(name = "EplayCustomer.findByRank", query = "SELECT e FROM EplayCustomer e WHERE e.rank = :rank"),
    @NamedQuery(name = "EplayCustomer.findByPbrought", query = "SELECT e FROM EplayCustomer e WHERE e.pbrought = :pbrought")})
public class EplayCustomer extends EplayUser implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 5)
//    @Column(name = "CUSID")
//    private String cusid;
    @Size(max = 25)
    @Column(name = "CUSNAME")
    private String cusname;
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
    @Size(max = 20)
    @Column(name = "RANK")
    private String rank;
    @Size(max = 5)
    @Column(name = "PBROUGHT")
    private String pbrought;
    @JoinColumn(name = "CUSID", referencedColumnName = "USERID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private EplayUser eplayUser;

    public EplayCustomer() {
    }
    
    public EplayCustomer(String userid, String name, String password, 
            String userGroup, Boolean active, String Cusname, String Phone, 
            String Address, String Email, String Rank, int pBrought) {
        setUserid(userid);
        setUsername(name);
        setPassword(password);
        setUsergroup(userGroup);
        setActive(active);
        
        this.cusname = Cusname;
        this.phone = Phone;
        this.address = Address;
        this.email = Email;
        this.rank = Rank;
        this.pbrought = String.valueOf(pBrought);
        
    }

//    public EplayCustomer(String cusid) {
//        this.cusid = cusid;
//    }
//
//    public String getCusid() {
//        return cusid;
//    }
//
//    public void setCusid(String cusid) {
//        this.cusid = cusid;
//    }

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

    public String getPbrought() {
        return pbrought;
    }

    public void setPbrought(String pbrought) {
        this.pbrought = pbrought;
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
//        hash += (cusid != null ? cusid.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof EplayCustomer)) {
//            return false;
//        }
//        EplayCustomer other = (EplayCustomer) object;
//        if ((this.cusid == null && other.cusid != null) || (this.cusid != null && !this.cusid.equals(other.cusid))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "entity.EplayCustomer[ cusid=" + cusid + " ]";
//    }
//    
}
