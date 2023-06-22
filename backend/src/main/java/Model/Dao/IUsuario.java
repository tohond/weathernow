/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Dao;
import Model.Entity.Ubicacion;
import Model.Entity.Usuario;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface IUsuario {
     public int insertar(Usuario usuario);
    public List<Usuario> consultar();
    public Usuario consultarId(Usuario usuario);
    public int borrar(Usuario usuario);
    public int actualizar(Usuario usuario);
    public int actualizarContrasenia(Usuario usuario, String nueva);
    public int actualizarUbicacion(Usuario usuario,Ubicacion ubicacion);
}
