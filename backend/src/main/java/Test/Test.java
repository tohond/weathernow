/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Model.Dao.UbicacionDao;
import Model.Dao.UsuarioDao;

/**
 *
 * @author Asus
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UsuarioDao ubDao = new UsuarioDao();
        System.out.println(ubDao.login("lole15@what.com","asd"));
    }
    
}
