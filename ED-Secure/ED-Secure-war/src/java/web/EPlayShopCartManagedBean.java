/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.EPlayProductDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import session.EPlayShopCartRemote;

/**
 *
 * @author thinh
 */
@Named(value = "ePlayShopCartManagedBean")
@SessionScoped
public class EPlayShopCartManagedBean implements Serializable {

//    @Inject
//    private Conversation conversation;
    @EJB
    private EPlayShopCartRemote shopCart;

    private String cusId;
    private String proId;
    
    public EPlayShopCartManagedBean() {
            cusId = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();

    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }
 
    public String addCart(String itemId){
        if(shopCart.addCartItem(itemId)){
            return "success";
        }else {
            return "failure";
        }
    }
    
    public ArrayList<EPlayProductDTO> getCart(){
        return shopCart.getCart();
    }
    
    public double getTotal(){
        return shopCart.getTotal();
    }
    
    public String checkOut(){
        if(shopCart.checkOut(cusId)){
            return "success";
        }
        else{
            return "fail";
        }
    }
}
