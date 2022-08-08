/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EPlayProductDTO;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author thinh
 */
@Remote
public interface EPlayShopCartRemote {

    ArrayList<EPlayProductDTO> getCart();

    boolean addCartItem(String itemId);

    boolean deleteCartItem(String itemId);
    
    void remove();

    double getTotal();

    boolean checkOut(String cusId);
}
