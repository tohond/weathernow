/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entity;

import java.sql.Timestamp;

/**
 *
 * @author Asus
 */
public class Ubicacion {
    private String id;
    private String pais;
    private String ciudad;
    private float latitud;
    private float longitud;
    
    public Ubicacion(){
        
    }

    public Ubicacion (String id){
        this.id=id;
    }
    public Ubicacion(String id, String pais, String ciudad, float latitud, float longitud) {
        this.id = id;
        this.pais = pais;
        this.ciudad = ciudad;
        this.latitud = latitud;
        this.longitud = longitud;
        
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Ubicacion{" + "id=" + id + ", pais=" + pais + ", ciudad=" + ciudad + ", latitud=" + latitud + ", longitud=" + longitud + '}';
    }

    
    
    
}
