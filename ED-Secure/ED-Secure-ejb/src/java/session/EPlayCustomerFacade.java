/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EplayCustomer;
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
public class EPlayCustomerFacade implements EPlayCustomerFacadeLocal {

    @PersistenceContext(unitName = "ED-Secure-ejbPU")
    private EntityManager em;

    public EPlayCustomerFacade() {
    }

    private void create(EplayCustomer entity) {
        em.persist(entity);
    }

    private void edit(EplayCustomer entity) {
        em.merge(entity);
    }

    private void remove(EplayCustomer entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public EplayCustomer find(String id) {
        return em.find(EplayCustomer.class, id);
    }

    @Override
    public boolean hasCustomer(String empId) {
        return (find(empId) != null);
    }

    @Override
    public boolean addCustomer(EplayCustomer employee) {
        EplayCustomer e = find(employee.getUserid());

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
    public boolean updateCustomerDetails(EplayCustomer employee) {
        EplayCustomer e = find(employee.getUserid());

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

        EplayCustomer e = find(empId);

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
    public boolean updatePBrought(String cusId, int pbrought) {
        EplayCustomer e = find(cusId);

        // check again - just to play it safe
        if (e == null) {
            return false;
        }

        try {
            int totalb = Integer.parseInt(e.getPbrought()) + pbrought;
            e.setPbrought(String.valueOf(totalb));

            if (totalb > 128) {
                e.setRank("EMERALD");
            } else if (totalb > 64) {
                e.setRank("DIAMON");
            } else if (totalb > 32) {
                e.setRank("PLATINUM");
            } else if (totalb > 16) {
                e.setRank("GOLD");
            } else if (totalb > 8) {
                e.setRank("SILVER");
            } else {
                e.setRank("BRONZE");
            }
            
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteCustomer(String empId) {
        // find the employee
        EplayCustomer e = find(empId);

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
    public boolean removeCustomer(String empId) {
        // find the employee
        EplayCustomer e = find(empId);

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
