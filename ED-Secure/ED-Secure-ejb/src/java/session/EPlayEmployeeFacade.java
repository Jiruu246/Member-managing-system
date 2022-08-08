/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EplayEmployee;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author thinh
 */
@Stateless
public class EPlayEmployeeFacade implements EPlayEmployeeFacadeLocal {

    @PersistenceContext(unitName = "ED-Secure-ejbPU")
    private EntityManager em;

    public EPlayEmployeeFacade() {
    }

    private void create(EplayEmployee entity) {
        em.persist(entity);
    }

    private void edit(EplayEmployee entity) {
        em.merge(entity);
    }

    private void remove(EplayEmployee entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public EplayEmployee find(String id) {
        return em.find(EplayEmployee.class, id);
    }

    @Override
    public boolean hasEmployee(String empId) {
        return (find(empId) != null);
    }

    @Override
    public boolean addEmployee(EplayEmployee employee) {
        EplayEmployee e = find(employee.getUserid());

        if (e != null) {
            // could not add one
            return false;
        }

        try {
            employee.setPassword(toHexString(getSHA(employee.getUserid())));
        } catch (NoSuchAlgorithmException exception) {
            System.out.println("Exception thrown for incorrect algorithm: " + exception);
        }
        create(employee);

        return true;
    }

    @Override
    public boolean updateEmployeeDetails(EplayEmployee employee) {
        EplayEmployee e = find(employee.getUserid());

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        // no need to update the primary key - empId
        edit(employee);
        return true;
    }

    @Override
    public boolean updatePassword(String empId, String password) {

        EplayEmployee e = find(empId);

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        try {
            e.setPassword(toHexString(getSHA(password)));
        } catch (NoSuchAlgorithmException exception) {
            System.out.println("Exception thrown for incorrect algorithm: " + exception);
        }
        // only need to update the password field
        return true;
    }

    @Override
    public boolean deleteEmployee(String empId) {
        // find the employee
        EplayEmployee e = find(empId);

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        if (!e.isActive()) {
            // employee not active already
            return false;
        }

        e.setActive(false);
        return true;
    }

    @Override
    public boolean removeEmployee(String empId) {
        // find the employee
        EplayEmployee e = find(empId);

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        em.remove(e);
        return true;
    }

    private static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    private static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);

        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

}
