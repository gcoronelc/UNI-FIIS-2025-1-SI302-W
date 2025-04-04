
package com.uni.pe.prueba;

import com.uni.pe.service.Proyecto1Service;

public class Proyecto1Prueba {
    public static void main(String[] args) {
        //Variables
        Proyecto1Service service = new Proyecto1Service();
        int dia = 12;
        int mes = 5;
        int anio = 1551;
        String Tri = "a";
        String Est = "a";
        
        //Proceso
        if(service.getValidezFecha(anio, mes, dia)){
            Tri = service.getTrimestre(mes);
            Est = service.getEstacion(mes, dia);
        }
        
        //Salida
        if(service.getValidezFecha(anio, mes, dia)){
            System.out.println("Fecha válida--- "+dia+" de "+mes+" del "+anio);
            System.out.println("Trimestre: "+Tri);
            System.out.println("Estacion: "+Est);
        }
        else{
            System.out.println("Fecha no válida---");
        }
    }
}
