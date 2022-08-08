/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EplayProduct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author thinh
 */
@Stateless
public class EPlayProductFacade implements EPlayProductFacadeLocal {

    @PersistenceContext(unitName = "ED-Secure-ejbPU")
    private EntityManager em;

    public EPlayProductFacade() {
    }

    private void create(EplayProduct entity) {
        em.persist(entity);
    }

    private void edit(EplayProduct entity) {
        em.merge(entity);
    }

    private void remove(EplayProduct entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public EplayProduct find(String id) {
        return em.find(EplayProduct.class, id);
    }
}
