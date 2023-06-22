/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Dao;

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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class PronosticoDao implements IPronostico{
    
    
    final static String SQL_CONSULTAR = "SELECT * FROM pronostico";
    final static String SQL_INSERTAR = "INSERT INTO pronostico(id,tipo_tiempo,timezone,fecha,hora_amanecer,hora_atardecer,temperatura,id_ubicacion,humedad,calidad_aire,probabilidad_lluvia,velocidad_viento) "
            + "                                             VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    final static String SQL_CONSULTARID = "SELECT * FROM pronostico WHERE id = ?";
    final static String SQL_CONSULTARPORUBICACION = "SELECT * FROM pronostico WHERE id_ubicacion = ?";
    final static String SQL_BORRAR = "DELETE FROM pronostico WHERE id = ?";
    final static String SQL_BORRARPORUBICACION = "DELETE FROM pronostico WHERE id_ubicacion = ?";
    final static String SQL_ACTUALIZAR = "UPDATE pronostico SET tipo_tiempo = ?, timezone = ?, fecha = ?, hora_amanecer = ?, hora_atardecer = ?, temperatura = ?, id_ubicacion = ?, humedad = ?, calidad_aire = ?, probabilidad_lluvia = ?, velocidad_viento = ? WHERE id = ?";
    
    
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
    public int insertar(Pronostico pronostico) {
        Connection connection = null;
            PreparedStatement sentencia = null;
            int resultado = 0;
            try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_INSERTAR);
            sentencia.setInt(1, pronostico.getId() );
            sentencia.setString(2, pronostico.getTiempo());
            sentencia.setString(3,pronostico.getTimezone());
            sentencia.setTimestamp(4, pronostico.getFecha() );
            sentencia.setTimestamp(5, pronostico.getHoraAmanecer());
            sentencia.setTimestamp(6, pronostico.getHoraAtardecer()  ) ;
            sentencia.setFloat(7, pronostico.getTemperatura());
            
            Ubicacion dbUbi = this.getUbicacion(pronostico.getUbicacion() );
            
            sentencia.setString(8, dbUbi.getId());
            sentencia.setFloat(9, pronostico.getHumedad());
            sentencia.setInt(10, pronostico.getCalidadAire());
            sentencia.setFloat(11, pronostico.getProbabilidadLluvia());
            sentencia.setFloat(12, pronostico.getVelocidadViento());
            
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
    public List<Pronostico> consultar() {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List <Pronostico> pronosticos = new ArrayList<>();
        
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR);
            resultado = sentencia.executeQuery();
            while(resultado.next()){
                String timezone = resultado.getString("timezone");
                String tiempo = resultado.getString("tipo_tiempo");
                String fecha = resultado.getTimestamp("fecha").toString();
                String horaAmanecer = resultado.getTimestamp("hora_amanecer").toString();
                String horaAtardecer = resultado.getTimestamp("hora_atardecer").toString();
                int temperatura = resultado.getInt("temperatura");
                
                String idUbicacion = resultado.getString("id_ubicacion");
                Ubicacion ubicacion = this.getUbicacion(new Ubicacion(idUbicacion) );
                
                float humedad = resultado.getFloat("humedad");
                int calidadAire = resultado.getInt("calidad_aire");
                float probabilidadLluvia = resultado.getFloat("probabilidad_lluvia");
                float velocidadViento = resultado.getFloat("velocidad_viento");
                int id = resultado.getInt("id");
                Pronostico pronostico = new Pronostico(id, ubicacion, tiempo, fecha, timezone, horaAmanecer, horaAtardecer, temperatura, humedad, calidadAire, probabilidadLluvia, velocidadViento);
                pronosticos.add(pronostico);
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
        return pronosticos;
    }

    @Override
    public Pronostico consultarId(Pronostico pronostico) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        Pronostico rPronostico = null;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTARID);
            sentencia.setInt(1, pronostico.getId());
            resultado = sentencia.executeQuery();
            resultado.next();
            String tiempo = resultado.getString("tipo_tiempo");
            String timezone = resultado.getString("timezone");
            String fecha = resultado.getTimestamp("fecha").toString();
            String horaAmanecer = resultado.getTimestamp("hora_amanecer").toString();
            String horaAtardecer = resultado.getTimestamp("hora_atardecer").toString();
            int temperatura = resultado.getInt("temperatura");
            
            String idUbicacion = resultado.getString("id_ubicacion");
            
            Ubicacion ubicacion = this.getUbicacion(new Ubicacion(idUbicacion));
            
            float humedad = resultado.getFloat("humedad");
            int calidadAire = resultado.getInt("calidad_aire");
            float probabilidadLluvia = resultado.getFloat("probabilidad_lluvia");
            float velocidadViento = resultado.getFloat("velocidad_viento");
            int id = resultado.getInt("id");
            rPronostico = new Pronostico(id, ubicacion, tiempo, fecha, timezone, horaAmanecer, horaAtardecer, temperatura, humedad, calidadAire, probabilidadLluvia, velocidadViento);
                
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
            return rPronostico;
    }
    }
    
    public List<Pronostico> consultarPorUbicacion(String idUb) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List <Pronostico> pronosticos = new ArrayList<>();
        
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTARPORUBICACION);
            sentencia.setString (1,idUb);
            resultado = sentencia.executeQuery();
            while(resultado.next()){
                String timezone = resultado.getString("timezone");
                String tiempo = resultado.getString("tipo_tiempo");
                String fecha = resultado.getTimestamp("fecha").toString();
                String horaAmanecer = resultado.getTimestamp("hora_amanecer").toString();
                String horaAtardecer = resultado.getTimestamp("hora_atardecer").toString();
                int temperatura = resultado.getInt("temperatura");
                
                String idUbicacion = resultado.getString("id_ubicacion");
                Ubicacion ubicacion = this.getUbicacion(new Ubicacion(idUbicacion) );
                
                float humedad = resultado.getFloat("humedad");
                int calidadAire = resultado.getInt("calidad_aire");
                float probabilidadLluvia = resultado.getFloat("probabilidad_lluvia");
                float velocidadViento = resultado.getFloat("velocidad_viento");
                int id = resultado.getInt("id");
                Pronostico p = new Pronostico(id, ubicacion, tiempo, fecha, timezone, horaAmanecer, horaAtardecer, temperatura, humedad, calidadAire, probabilidadLluvia, velocidadViento);
                pronosticos.add(p);
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
        return pronosticos;
    }

    @Override
    public int borrar(Pronostico pronostico) {
        Connection connection = null;
            PreparedStatement sentencia = null;
            int resultado = 0;
            try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_BORRAR);
            sentencia.setInt(1, pronostico.getId());
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
    
    
    public int borrarPorUbicacion(String ubID) {
        Connection connection = null;
            PreparedStatement sentencia = null;
            int resultado = 0;
            try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_BORRARPORUBICACION);
            sentencia.setString(1, ubID);
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
    public int actualizar(Pronostico pronostico) {
        Connection connection = null;
            PreparedStatement sentencia = null;
            int resultado = 0;
            try {
                /*
                UPDATE pronostico SET tiempo = ?, timezone = ?
                fecha = ?, hora_amanecer = ?, hora_atardecer = ?, 
                temperatura = ?, id_ubicacion = ?, humedad = ?, calidad_aire = ?, 
                probabilidad_lluvia = ?, velocidad_viento = ? WHERE id = ?";
    
                */
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_ACTUALIZAR);
            sentencia.setInt(12, pronostico.getId());
            sentencia.setString(1, pronostico.getTiempo());
            sentencia.setString (2,pronostico.getTimezone() );
            sentencia.setTimestamp(3, pronostico.getFecha());
            sentencia.setTimestamp(4, pronostico.getHoraAmanecer());
            sentencia.setTimestamp(5, pronostico.getHoraAtardecer());
            sentencia.setFloat(6, pronostico.getTemperatura());
            sentencia.setString(7, pronostico.getUbicacion().getId());
            sentencia.setFloat(8, pronostico.getHumedad());
            sentencia.setInt(9, pronostico.getCalidadAire());
            sentencia.setFloat(10, pronostico.getProbabilidadLluvia());
            sentencia.setFloat(11, pronostico.getVelocidadViento());
            
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
