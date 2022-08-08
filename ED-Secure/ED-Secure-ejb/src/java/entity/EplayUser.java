/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thinh
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@DiscriminatorColumn(name = "USERGROUP")
@Table(name = "EPLAY_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EplayUser.findAll", query = "SELECT e FROM EplayUser e"),
    @NamedQuery(name = "EplayUser.findByUserid", query = "SELECT e FROM EplayUser e WHERE e.userid = :userid"),
    @NamedQuery(name = "EplayUser.findByUsername", query = "SELECT e FROM EplayUser e WHERE e.username = :username"),
    @NamedQuery(name = "EplayUser.findByPassword", query = "SELECT e FROM EplayUser e WHERE e.password = :password"),
    @NamedQuery(name = "EplayUser.findByUsergroup", query = "SELECT e FROM EplayUser e WHERE e.usergroup = :usergroup"),
    @NamedQuery(name = "EplayUser.findByActive", query = "SELECT e FROM EplayUser e WHERE e.active = :active")})
public class EplayUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "USERID")
    private String userid;
    @Size(max = 25)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 300)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 20)
    @Column(name = "USERGROUP")
    private String usergroup;
    @Column(name = "ACTIVE")
    private Boolean active;

    public EplayUser() {
    }

    public EplayUser(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getUsergroup() {
        return usergroup;
    }

    public void setUsergroup(String usergroup) {
        this.usergroup = usergroup;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EplayUser)) {
            return false;
        }
        EplayUser other = (EplayUser) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EplayUser[ userid=" + userid + " ]";
    }
    
}
