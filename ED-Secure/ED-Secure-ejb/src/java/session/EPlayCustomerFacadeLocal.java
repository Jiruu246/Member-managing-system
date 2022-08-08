/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EplayCustomer;
import javax.ejb.Local;

/**
 *
 * @author thinh
 */
@Local
public interface EPlayCustomerFacadeLocal {

    EplayCustomer find(String id);

    boolean hasCustomer(String empId);

    boolean addCustomer(EplayCustomer emp);

    boolean updateCustomerDetails(EplayCustomer employee);

    boolean updatePassword(String empId, String password);

    boolean deleteCustomer(String empId);

    boolean removeCustomer(String empId);

    boolean updatePBrought(String cusId, int pbrought);
}
