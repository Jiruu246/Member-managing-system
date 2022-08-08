/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EPlayProductDTO;
import entity.EplayProduct;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author thinh
 */
@Stateful
public class EPlayShopCart implements EPlayShopCartRemote {

    @EJB
    private EPlayCustomerFacadeLocal customerFacade;

    @EJB
    private EPlayProductFacadeLocal productFacade;

    private ArrayList<EPlayProductDTO> cart;

    public EPlayShopCart() {
        cart = new ArrayList<EPlayProductDTO>();
    }

    private boolean add(EPlayProductDTO cartIte) {
        boolean result = false;
        try {
            result = cart.add(cartIte);
        } catch (Exception ex) {
        }
        return result;
    }

    private EplayProduct productDTO2Entity(EPlayProductDTO empDTO) {
        if (empDTO == null) {
            // just in case
            return null;
        }

        String proid = empDTO.getProductId();
        String name = empDTO.getName();
        String category = empDTO.getCatergory();
        double price = empDTO.getPrice();

        EplayProduct employee = new EplayProduct(proid, name, category, price);

        return employee;
    }

    private EPlayProductDTO productEntity2DTO(EplayProduct pro) {
        if (pro == null) {
            // just in case
            return null;
        }

        EPlayProductDTO proDTO = new EPlayProductDTO(
                pro.getProid(),
                pro.getName(),
                pro.getCatergory(),
                pro.getPrice()
        );

        return proDTO;
    }

    @Override
    public ArrayList<EPlayProductDTO> getCart() {
        return cart;
    }

    @Override
    public boolean addCartItem(String itemId) {
        boolean result = false;

        EplayProduct product = productFacade.find(itemId);
        EPlayProductDTO proDTO = productEntity2DTO(product);
        try {
//            for (EPlayProductDTO item : cart) {
//                if (item.getItemId().equals(cartItem.getProductId())) {
//                    item.setQuantity(item.getQuantity() + cartItem.getQuantity());
//                    return result = true;
//                }
//            }
            return result = cart.add(proDTO);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return result;
    }

    @Override
    public boolean deleteCartItem(String itemId) {
        boolean result = false;
        try {
            for (EPlayProductDTO item : cart) {
                if (item.getProductId().equals(itemId)) {
                    return result = cart.remove(item);
                }
            }
        } catch (Exception ex) {

        }
        return result;
    }

    @Remove
    public void remove() {
        cart = null;
    }

    @Override
    public double getTotal() {
        double total = 0.0;
        for (EPlayProductDTO ci : cart) {
            double unitPrice = ci.getPrice();
            total += unitPrice;
        }

        return total;
    }

    @Override
    public boolean checkOut(String cusId) {
        int pbrought = cart.size();
        cart.clear();
        return customerFacade.updatePBrought(cusId, pbrought);
    }

}
