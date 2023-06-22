/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Dao;


import static Model.Dao.PronosticoDao.SQL_BORRAR;
import Model.Entity.Pronostico;
import Model.Entity.Ubicacion;
import Red.BaseDatos;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class UbicacionDao implements IUbicacion{
    final static String SQL_CONSULTAR = "SELECT * FROM ubicacion";
    final static String SQL_INSERTAR = "INSERT INTO ubicacion(id,pais,ciudad,latitud,longitud)"
            + "                                         VALUES(?,?,?,?,?)";
    final static String SQL_CONSULTARID = "SELECT * FROM ubicacion WHERE id = ?";
    final static String SQL_CONSULTARPAIS = "SELECT * FROM ubicacion WHERE pais = ?";
    final static String SQL_BORRAR = "DELETE FROM ubicacion WHERE id = ?";
    final static String SQL_BORRARPAIS = "DELETE FROM ubicacion WHERE pais = ?";
    
    final static String SQL_ACTUALIZAR = "UPDATE ubicacion SET pais = ?, ciudad = ?, latitud = ?, longitud = ? WHERE id=?";
    @Override
    public int insertar(Ubicacion ubicacion) {
     Connection connection = null;
            PreparedStatement sentencia = null;
            int resultado = 0;
            try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(this.SQL_INSERTAR);
            sentencia.setString(1, ubicacion.getId());
            sentencia.setString(2, ubicacion.getPais());
            sentencia.setString(3, ubicacion.getCiudad());
            sentencia.setFloat(4, ubicacion.getLatitud());
            sentencia.setFloat(5, ubicacion.getLongitud());
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
    public List<Ubicacion> consultar() {
     Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List <Ubicacion> ubicaciones = new ArrayList<>();
        
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(this.SQL_CONSULTAR);
            resultado = sentencia.executeQuery();
            while(resultado.next()){
                String id = resultado.getString("id");
                String pais = resultado.getString("pais");
                String ciudad = resultado.getString("ciudad");
                float latitud = resultado.getFloat("latitud");
                float longitud = resultado.getFloat("longitud");
                Ubicacion ubicacion = new Ubicacion(id, pais, ciudad, latitud, longitud);
                ubicaciones.add(ubicacion);
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
        return ubicaciones;   
    
    }
    
    public List<Ubicacion> consultarPais(String paisConsulta) {
     Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List <Ubicacion> ubicaciones = new ArrayList<>();
        
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(this.SQL_CONSULTARPAIS);
            sentencia.setString(1, paisConsulta);
            resultado = sentencia.executeQuery();
            while(resultado.next()){
                String id = resultado.getString("id");
                String pais = resultado.getString("pais");
                String ciudad = resultado.getString("ciudad");
                float latitud = resultado.getFloat("latitud");
                float longitud = resultado.getFloat("longitud");
                Ubicacion ubicacion = new Ubicacion(id, pais, ciudad, latitud, longitud);
                ubicaciones.add(ubicacion);
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
        return ubicaciones;   
    
    }


    @Override
    public Ubicacion consultarId(Ubicacion ubicacion) {
       Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        Ubicacion retUb = null;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(this.SQL_CONSULTARID);
            sentencia.setString(1, ubicacion.getId());
            resultado = sentencia.executeQuery();
            resultado.next();
            String id = resultado.getString("id");
            String pais = resultado.getString("pais");
            String ciudad = resultado.getString("ciudad");
            float latitud = resultado.getFloat("latitud");
            float longitud = resultado.getFloat("longitud");
            retUb = new Ubicacion(id, pais, ciudad, latitud, longitud);
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
        return retUb; 
    }

    @Override
    public int borrar(Ubicacion ubicacion) {
        Connection connection = null;
            PreparedStatement sentencia = null;
            int resultado = 0;
            try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(this.SQL_BORRAR);
            sentencia.setString(1, ubicacion.getId());
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
    
    public int borrarPais(Ubicacion ubicacion) {
        Connection connection = null;
            PreparedStatement sentencia = null;
            int resultado = 0;
            try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(this.SQL_BORRARPAIS);
            sentencia.setString(1, ubicacion.getPais());
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
    public int actualizar(Ubicacion ubicacion) {
       Connection connection = null;
            PreparedStatement sentencia = null;
            int resultado = 0;
            try {
            connection = BaseDatos.getConnection();
            //"UPDATE ubicacion SET pais = ?, ciudad = ?, latitud = ?, longitud = ? WHERE id=?";
   
            sentencia = connection.prepareStatement(this.SQL_ACTUALIZAR);
            
            sentencia.setString(1, ubicacion.getPais() );
            sentencia.setString(2, ubicacion.getCiudad() );
            sentencia.setFloat(3,ubicacion.getLatitud());
            sentencia.setFloat(4,ubicacion.getLongitud());
            sentencia.setString(5, ubicacion.getId() );
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
    
}
