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


public class Pronostico {
    //eliminar id, solo dejar ubicacion
    private int id;
    private String tiempo;
    private Timestamp fecha;

    
    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    private String timezone;
    private Timestamp  horaAmanecer;
    private Timestamp  horaAtardecer;
    private float temperatura;
    private Ubicacion ubicacion;
    private float humedad;
    private int calidadAire;
    private float probabilidadLluvia;
    private float velocidadViento;
    
    public Pronostico(){
        
    }
    
    
    
    public Pronostico(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pronostico{" + "id=" + id + ", tiempo=" + tiempo + ", fecha=" + fecha + ", timezone=" + timezone + ", horaAmanecer=" + horaAmanecer + ", horaAtardecer=" + horaAtardecer + ", temperatura=" + temperatura + ", ubicacion=" + ubicacion + ", humedad=" + humedad + ", calidadAire=" + calidadAire + ", probabilidadLluvia=" + probabilidadLluvia + ", velocidadViento=" + velocidadViento + '}';
    }
    
    public Pronostico(int id, Ubicacion ubicacion, String tiempo, Timestamp fecha, String timezone, Timestamp  horaAmanecer, Timestamp  horaAtardecer, float temperatura,  float humedad, int calidadAire, float probabilidadLluvia, float velocidadViento) {
        this.id = id;
        this.tiempo = tiempo;
        this.fecha = fecha;
        this.timezone = timezone;
        this.horaAmanecer = horaAmanecer;
        this.horaAtardecer = horaAtardecer;
        this.temperatura = temperatura;
        this.ubicacion = ubicacion;
        this.humedad = humedad;
        this.calidadAire = calidadAire;
        this.probabilidadLluvia = probabilidadLluvia;
        this.velocidadViento = velocidadViento;
    }

    
    
    public Pronostico(int id, Ubicacion ubicacion, String tiempo, String fecha, String timezone, String horaAmanecer, String horaAtardecer, float temperatura, float humedad, int calidadAire, float probabilidadLluvia, float velocidadViento) {
        this.id = id;
        this.tiempo = tiempo;
        this.fecha = Timestamp.valueOf(fecha);
        this.timezone = timezone;
        this.horaAmanecer = Timestamp.valueOf(horaAmanecer);
        this.horaAtardecer = Timestamp.valueOf(horaAtardecer);
        this.temperatura = temperatura;
        this.ubicacion = ubicacion;
        this.humedad = humedad;
        this.calidadAire = calidadAire;
        this.probabilidadLluvia = probabilidadLluvia;
        this.velocidadViento = velocidadViento;
    }
    
    public Pronostico(int id, Ubicacion ubicacion, String tiempo, Timestamp fecha, String timezone, Timestamp  horaAmanecer, Timestamp  horaAtardecer, float temperaturaMax, float temperaturaMin,  float humedad, int calidadAire, float probabilidadLluvia, float velocidadViento) {
        this.id = id;
        this.tiempo = tiempo;
        this.fecha = fecha;
        this.timezone = timezone;
        this.horaAmanecer = horaAmanecer;
        this.horaAtardecer = horaAtardecer;
        this.temperatura = (float) ( (temperaturaMax+temperaturaMin)/2f );
        this.ubicacion = ubicacion;
        this.humedad = humedad;
        this.calidadAire = calidadAire;
        this.probabilidadLluvia = probabilidadLluvia;
        this.velocidadViento = velocidadViento;
    }

    
    
    public Pronostico(int id, Ubicacion ubicacion, String tiempo, String fecha, String timezone, String horaAmanecer, String horaAtardecer, float temperaturaMax, float temperaturaMin, float humedad, int calidadAire, float probabilidadLluvia, float velocidadViento) {
        this.id = id;
        this.tiempo = tiempo;
        this.fecha = Timestamp.valueOf(fecha);
        this.timezone = timezone;
        this.horaAmanecer = Timestamp.valueOf(horaAmanecer);
        this.horaAtardecer = Timestamp.valueOf(horaAtardecer);
        this.temperatura = (float) ( (temperaturaMax+temperaturaMin)/2f );
        this.ubicacion = ubicacion;
        this.humedad = humedad;
        this.calidadAire = calidadAire;
        this.probabilidadLluvia = probabilidadLluvia;
        this.velocidadViento = velocidadViento;
    }

    

    

    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tiempo
     */
    public String getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * @return the fecha
     */
    public Timestamp getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the horaAmanecer
     */
    public Timestamp  getHoraAmanecer() {
        return horaAmanecer;
    }

    /**
     * @param horaAmanecer the horaAmanecer to set
     */
    public void setHoraAmanecer(Timestamp  horaAmanecer) {
        this.horaAmanecer = horaAmanecer;
    }

    /**
     * @return the horaAtardecer
     */
    public Timestamp  getHoraAtardecer() {
        return horaAtardecer;
    }

    /**
     * @param horaAtardecer the horaAtardecer to set
     */
    public void setHoraAtardecer(Timestamp  horaAtardecer) {
        this.horaAtardecer = horaAtardecer;
    }

    /**
     * @return the temperatura
     */
    public float getTemperatura() {
        return temperatura;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * @return the ubicacion
     */
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the humedad
     */
    public float getHumedad() {
        return humedad;
    }

    /**
     * @param humedad the humedad to set
     */
    public void setHumedad(float humedad) {
        this.humedad = humedad;
    }

    /**
     * @return the calidadAire
     */
    public int getCalidadAire() {
        return calidadAire;
    }

    /**
     * @param calidadAire the calidadAire to set
     */
    public void setCalidadAire(int calidadAire) {
        this.calidadAire = calidadAire;
    }

    /**
     * @return the probabilidadLluvia
     */
    public float getProbabilidadLluvia() {
        return probabilidadLluvia;
    }

    /**
     * @param probabilidadLluvia the probabilidadLluvia to set
     */
    public void setProbabilidadLluvia(float probabilidadLluvia) {
        this.probabilidadLluvia = probabilidadLluvia;
    }

    /**
     * @return the velocidadViento
     */
    public float getVelocidadViento() {
        return velocidadViento;
    }

    /**
     * @param velocidadViento the velocidadViento to set
     */
    public void setVelocidadViento(float velocidadViento) {
        this.velocidadViento = velocidadViento;
    }

    
    
}
