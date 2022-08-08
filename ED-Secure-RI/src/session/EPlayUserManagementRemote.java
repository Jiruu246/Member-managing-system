/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EPlayCustomerDTO;
import entity.EPlayEmployeeDTO;
import javax.ejb.Remote;

/**
 *
 * @author thinh
 */
@Remote
public interface EPlayUserManagementRemote {

    boolean hasEmployee(String empid);

    boolean hasCustomer(String cusid);

    boolean addEmployee(EPlayEmployeeDTO empDTO);

    boolean updateEmpolyeeDetails(EPlayEmployeeDTO empDTO);

    boolean updateCustomerDetails(EPlayCustomerDTO cusDTO);

    boolean updateEmployeePassword(String empid, String newPassword);

    boolean updateCustomerPassword(String cusid, String newPassword);

    EPlayEmployeeDTO getEmployeeDetails(String empid);

    EPlayCustomerDTO getCustomerDetails(String cusid);

    boolean deleteEmployee(String empid);

    boolean removeEmployee(String empid);

}
