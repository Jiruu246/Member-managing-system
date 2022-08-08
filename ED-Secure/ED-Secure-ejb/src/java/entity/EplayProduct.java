/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Entity
@Table(name = "EPLAY_PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EplayProduct.findAll", query = "SELECT e FROM EplayProduct e"),
    @NamedQuery(name = "EplayProduct.findByProid", query = "SELECT e FROM EplayProduct e WHERE e.proid = :proid"),
    @NamedQuery(name = "EplayProduct.findByName", query = "SELECT e FROM EplayProduct e WHERE e.name = :name"),
    @NamedQuery(name = "EplayProduct.findByCatergory", query = "SELECT e FROM EplayProduct e WHERE e.catergory = :catergory"),
    @NamedQuery(name = "EplayProduct.findByPrice", query = "SELECT e FROM EplayProduct e WHERE e.price = :price")})
public class EplayProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "PROID")
    private String proid;
    @Size(max = 25)
    @Column(name = "NAME")
    private String name;
    @Size(max = 10)
    @Column(name = "CATERGORY")
    private String catergory;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private double price;

    public EplayProduct() {
    }

    public EplayProduct(String proid, String name, String catergory, double price) {
        this.proid = proid;
        this.name = name;
        this.catergory = catergory;
        this.price = price;
    }
    
    

    public EplayProduct(String proid) {
        this.proid = proid;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatergory() {
        return catergory;
    }

    public void setCatergory(String catergory) {
        this.catergory = catergory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proid != null ? proid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EplayProduct)) {
            return false;
        }
        EplayProduct other = (EplayProduct) object;
        if ((this.proid == null && other.proid != null) || (this.proid != null && !this.proid.equals(other.proid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EplayProduct[ proid=" + proid + " ]";
    }
    
}
