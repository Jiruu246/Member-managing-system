/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author thinh
 */
public class EPlayProductDTO {
    String ProductId;
    String Name;
    String Catergory;
    double Price;

    public EPlayProductDTO(String ProductId, String Name, String Catergory, double Price) {
        this.ProductId = ProductId;
        this.Name = Name;
        this.Catergory = Catergory;
        this.Price = Price;
    }

    public String getProductId() {
        return ProductId;
    }

    public String getName() {
        return Name;
    }

    public String getCatergory() {
        return Catergory;
    }

    public double getPrice() {
        return Price;
    }
    
}
