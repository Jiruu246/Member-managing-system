/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplay;

import java.util.ArrayList;

/**
 *
 * @author thinh
 */
public class SetupUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // the database object to access the actual database
        EPlayUserDB db = new EPlayUserDB();

        // make sure no name conflicts
        try {
            db.destroyDBTable();
        } catch (Exception ex) {
        }

        // create the database table
        System.out.println("Create an empty database table User");
        db.createDBTable();

        System.out.println("Add several static records in the database table");

        // prepare data
        EPlayEmployee emp001 = new EPlayEmployee("00001", "Adam", "ee79976c9380d5e337fc1c095ece8c8f22f91f306ceeb161fa51fecede2c4ba1", "EPLAY-ADMIN", true,
                "Adam eyelash", "0123456789", "Spring st", "adam@gmail.com", 8000.0);
        EPlayEmployee emp002 = new EPlayEmployee("00002", "Bill", "33a7d3da476a32ac237b3f603a1be62fad00299e0d4b5a8db8d913104edec629", "EPLAY-ADMIN", true,
                "Bill eyelash", "0123456789", "Spring st", "bill@gmail.com", 2000.0);
        EPlayCustomer cus003 = new EPlayCustomer("00003", "Ceci", "afb47e00531153e93808589e43d02c11f6398c5bc877f7924cebca8211c8dd18", "EPLAY-USER", true,
                "Celi eyelash", "0123456789", "Spring st", "celi@gmail.com", "BRONZE", 9);
        EPlayCustomer cus004 = new EPlayCustomer("00004", "David", "b3c4b40750a97212e8981e4ac494d1ec77053f1eaf4e0934c276b74fc4f87c48", "EPLAY-USER", true,
                "David Boohya", "0123456789", "Spring st", "celi@gmail.com", "GOLD", 20);

        EPlayProduct pro001 = new EPlayProduct("00001", "ASUS ROG", "Laptop", 900.0);
        EPlayProduct pro002 = new EPlayProduct("00002", "Iphone 13", "Phone", 800.0);
        EPlayProduct pro003 = new EPlayProduct("00003", "Xbox 360", "Console", 400.0);

        ArrayList<EPlayEmployee> employeeList = new ArrayList<>();
        employeeList.add(emp001);
        employeeList.add(emp002);
        // prepare list
        ArrayList<EPlayCustomer> customerList = new ArrayList<>();
        customerList.add(cus003);
        customerList.add(cus004);
        
        ArrayList<EPlayProduct> productList = new ArrayList<>();
        productList.add(pro001);
        productList.add(pro002);
        productList.add(pro003);

        // add data to db
        db.addRecordsE(employeeList);
        db.addRecords(customerList);
        db.addRecordsP(productList);

    }

}
