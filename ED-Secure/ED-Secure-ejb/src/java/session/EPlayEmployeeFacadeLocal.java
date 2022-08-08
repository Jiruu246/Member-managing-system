/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EplayEmployee;
import javax.ejb.Local;

/**
 *
 * @author thinh
 */
@Local
public interface EPlayEmployeeFacadeLocal {

    EplayEmployee find(String id);

    boolean hasEmployee(String empId);

    boolean addEmployee(EplayEmployee emp);

    boolean updateEmployeeDetails(EplayEmployee employee);

    boolean updatePassword(String empId, String password);
    
    boolean deleteEmployee(String empId);
    
    boolean removeEmployee(String empId);
    
}
