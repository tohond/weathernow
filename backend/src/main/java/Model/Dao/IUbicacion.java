/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Dao;

import Model.Entity.Ubicacion;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface IUbicacion {
     public int insertar(Ubicacion ubicacion);
    public List<Ubicacion> consultar();
    public Ubicacion consultarId(Ubicacion ubicacion);
    public int borrar(Ubicacion ubicacion);
    public int actualizar(Ubicacion ubicacion);
}
