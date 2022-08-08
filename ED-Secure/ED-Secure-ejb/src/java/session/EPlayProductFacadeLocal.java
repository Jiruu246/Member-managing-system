/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EplayProduct;
import javax.ejb.Local;

/**
 *
 * @author thinh
 */
@Local
public interface EPlayProductFacadeLocal {

    EplayProduct find(String id);
}
