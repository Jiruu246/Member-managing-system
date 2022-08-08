/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplay;

/**
 *
 * @author thinh
 */
public class EPlayProduct {
    String productId;
    String name;
    String catergory;
    double price;

    public EPlayProduct(String productId, String name, String catergory, double price) {
        this.productId = productId;
        this.name = name;
        this.catergory = catergory;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getCatergory() {
        return catergory;
    }

    public double getPrice() {
        return price;
    }
    
}
