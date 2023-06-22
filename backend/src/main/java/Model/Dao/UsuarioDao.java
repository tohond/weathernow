/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Dao;

import Model.Entity.Ubicacion;
import Model.Entity.Usuario;
import Red.BaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UsuarioDao implements IUsuario{
    
    final static String SQL_CONSULTAR = "SELECT * FROM usuario";
    final static String SQL_INSERTAR = "INSERT INTO usuario(correo, contrasenia, id_ubicacion) VALUES(?,?,?)";
    final static String SQL_CONSULTARID = "SELECT * FROM usuario WHERE correo = ?";
    final static String SQL_BORRAR = "DELETE FROM usuario WHERE correo = ?";
    final static String SQL_ACTUALIZAR = "UPDATE usuario SET contrasenia = ?, id_ubicacion = ? WHERE correo = ?";
    final static String SQL_LOGIN = "SELECT * FROM usuario WHERE correo = ? AND contrasenia = ?";
    
    
    
    private Ubicacion getUbicacion (Ubicacion idUb){
        Ubicacion retUbicacion = null;
        try{
                UbicacionDao ubDao = new UbicacionDao();
                 retUbicacion = ubDao.consultarId(idUb ) ;
                if ( Objects.isNull( retUbicacion) ) {
                    throw new RuntimeException("No existe ubicacion");
                }
                return retUbicacion;
                
            
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        
        return retUbicacion;
    }
    
    
    @Override
    public int insertar(Usuario usuario) {
        Connection connection = null;
            PreparedStatement sentencia = null;
            int resultado = 0;
            try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_INSERTAR);
            sentencia.setString(1, usuario.getCorreo());
            sentencia.setString(2, usuario.getContrasenia());
            sentencia.setString(3, usuario.getUbicacion().getId());
            
            resultado = sentencia.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            finally {
            try {
                BaseDatos.close(sentencia);
            BaseDatos.close(sentencia);
            BaseDatos.close(connection);
        }
            catch (SQLException ex) {
                Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }
    
    public int login(String correo,String contraseña) {
        Connection connection = null;
            PreparedStatement sentencia = null;
            ResultSet resultado = null;
            int res=0;
            
            try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_LOGIN);
            sentencia.setString(1, correo);
            sentencia.setString(2, contraseña);
           
             
             resultado= sentencia.executeQuery();
             while(resultado.next()){
                 res++;
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            finally {
            try {
                BaseDatos.close(sentencia);
            BaseDatos.close(sentencia);
            BaseDatos.close(connection);
        }
            catch (SQLException ex) {
                Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }

    @Override
    public List<Usuario> consultar() {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List <Usuario> usuarios = new ArrayList<>();
        
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR);
            resultado = sentencia.executeQuery();
            while(resultado.next()){
                String correo = resultado.getString("correo");
                String contrasenia = "";
                String id_ubicacion = resultado.getString("id_ubicacion");
                Ubicacion dbUb = this.getUbicacion(new Ubicacion(id_ubicacion));
                
                Usuario usuario = new Usuario(dbUb,correo,contrasenia);
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                BaseDatos.close(resultado);
            BaseDatos.close(sentencia);
            BaseDatos.close(connection);
        }
            catch (SQLException ex) {
                Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuarios;
    }

    @Override
    public Usuario consultarId(Usuario usuario) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        Usuario rUsuario = null;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTARID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            sentencia.setString(1, usuario.getCorreo());
            resultado = sentencia.executeQuery();
            resultado.next();
            String contrasenia = "";
            String idUbicacion = resultado.getString("id_ubicacion");
            
            Ubicacion ubicacion = this.getUbicacion (new Ubicacion(idUbicacion));
            String correo = resultado.getString("correo");
            rUsuario = new Usuario(ubicacion, correo, contrasenia);

        } catch (SQLException ex) {
            Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDatos.close(resultado);
                BaseDatos.close(sentencia);
                BaseDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return rUsuario;
    }
    }

    @Override
    public int borrar(Usuario usuario) {
        Connection connection = null;
            PreparedStatement sentencia = null;
            int resultado = 0;
            try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_BORRAR);
            sentencia.setString(1, usuario.getCorreo());
            resultado = sentencia.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            finally {
            try {
                BaseDatos.close(sentencia);
            BaseDatos.close(sentencia);
            BaseDatos.close(connection);
        }
            catch (SQLException ex) {
                Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    @Override
    public int actualizar(Usuario usuario) {
        Connection connection = null;
            PreparedStatement sentencia = null;
            int resultado = 0;
            try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_ACTUALIZAR);
            sentencia.setString(3, usuario.getCorreo());
            sentencia.setString(1, usuario.getContrasenia());
            sentencia.setString(2, usuario.getUbicacion().getId());
            resultado = sentencia.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            finally {
            try {
                BaseDatos.close(sentencia);
            BaseDatos.close(sentencia);
            BaseDatos.close(connection);
        }
            catch (SQLException ex) {
                Logger.getLogger(PronosticoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    
    }

    @Override
    public int actualizarContrasenia(Usuario usuario, String nueva) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int actualizarUbicacion(Usuario usuario, Ubicacion ubicacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
