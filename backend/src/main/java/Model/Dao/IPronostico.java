/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Dao;

import Model.Entity.Pronostico;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface IPronostico {
    public int insertar(Pronostico pronostico);
    public List<Pronostico> consultar();
    public Pronostico consultarId(Pronostico pronostico);
    public int borrar(Pronostico pronostico);
    public int actualizar(Pronostico pronostico);
}
