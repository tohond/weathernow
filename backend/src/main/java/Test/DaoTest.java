/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import Model.Dao.*;
import Model.Entity.*;
import java.util.ArrayList;
import java.util.List;

/**+
 *
 * @author 57313
 */
public class DaoTest {

    public static void main(String[] args) {
        //ubicacion();
        pronostico();
    }
    
    public static void ubicacion(){
        UbicacionDao ubDao = new UbicacionDao();
        Ubicacion ub = new Ubicacion ("121312","colombia","cucuta",1.232f,2.323f);
        
        int result = ubDao.insertar(ub);
        System.out.println("insertar 1:"+result);
        
        
         result = ubDao.insertar(ub);
        System.out.println("insertar repetido:"+result);
        
        ub = new Ubicacion ("1213213","colombia","bogota",1.242f,2.323f);
        
        result = ubDao.insertar(ub);
        System.out.println("insertar 2:"+result);
        
        
        List<Ubicacion> resultL = ubDao.consultar();
        for (Ubicacion ubi : resultL) {
            System.out.println("consultar:"+ubi.toString());
        }
        
        ub = new Ubicacion("1213213");
        ub = ubDao.consultarId(ub);
        System.out.println("consultarId:"+ub);
        
        result = ubDao.actualizar(new Ubicacion ("1213213","colombia","medellin",2.245f,13.21f));
        System.out.println("actualizar uno:"+result);
        result = ubDao.actualizar(new Ubicacion ("121312","colombia","bucaramanga",2.885f,21.21f));
        System.out.println("actualizar dos:"+result);
        
        System.out.println("Borrar 1:"+ubDao.borrar(new Ubicacion("1213213")));
        System.out.println("Borrar 2:"+ubDao.borrar(new Ubicacion("121312")));
    }
    
    public static void pronostico(){
        UbicacionDao ubDao = new UbicacionDao();
        PronosticoDao pDao = new PronosticoDao();
        Ubicacion ub = new Ubicacion ("121312","colombia","cucuta",1.232f,2.323f);
        
        int result = ubDao.insertar(ub);
        System.out.println("insertar ubicacion:"+result);
        
        /* this.id = id;
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
        this.velocidadViento = velocidadViento;*/
        
        Pronostico p = new Pronostico (0,ub,"nublado","2018-09-01 00:00:00","America/Bogota",
                "2018-09-01 05:01:15","2018-09-01 09:01:15",23,23.2f,22,14f,14);
        
         result = pDao.insertar(p);
         result = pDao.insertar(p);
         result = pDao.insertar(p);
        System.out.println("insertar pronostico:"+result);
        
        ub = new Ubicacion ("1213213","colombia","bogota",1.242f,2.323f);
        ubDao.insertar(ub);
         p = new Pronostico (0,ub,"nublado","2018-09-01 00:00:00","America/Bogota",
                "2018-09-01 05:01:15","2018-09-01 09:01:15",23,23.2f,22,14f,14);
        
        result = pDao.insertar(p);
        
        System.out.println("insertar pronostico 2:"+result);
        
        
        List<Pronostico> resultL = pDao.consultar();
        for (Pronostico pro : resultL) {
            System.out.println("consultar:"+pro.toString());
        }
        
        p = new Pronostico(1);
        p = pDao.consultarId(p);
        System.out.println("consultarId:"+p);
        
        result = pDao.actualizar(new Pronostico (1,ub,"soleado","2018-09-01 00:00:00","America/Bogota",
                "2018-09-01 05:01:15","2018-09-01 09:01:15",23,23.2f,22,14f,14) );
        System.out.println("actualizar uno:"+result);
//            ub = new Ubicacion ("1213125","colombia","bucaramanga",2.885f,21.21f);
//            ubDao.insertar(ub);
////        result = pDao.actualizar(new Pronostico (1,ub,"soleado","2018-09-01 00:00:00","America/Bogota",
////                "2018-09-01 05:01:15","2018-09-01 09:01:15",23,23.2f,22,14f,14) );
////        System.out.println("actualizar dos:"+result);
        
        System.out.println("Borrar ub:"+pDao.borrarPorUbicacion("1213213"));
        System.out.println("Borrar 1:"+pDao.borrar(new Pronostico(1)));
        System.out.println("Borrar 2:"+pDao.borrar(new Pronostico(2)));
        
    }
}
