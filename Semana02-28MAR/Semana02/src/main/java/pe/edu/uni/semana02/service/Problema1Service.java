package pe.edu.uni.semana02.service;

import java.util.Random;

public class Problema1Service {
    
    public int[] getDatos(){
        // Variables
        int[] datos;
        // Proceso
        Random random = new Random();
        datos = new int[4];
        for (int i = 0; i < datos.length; i++) {
            datos[i] = random.nextInt(9) + 1;
        }
        // Reporte
        return datos;
        //return new int[]{5,5,5,5};
    }
    
    
    public int getMenor(int[] datos){
        // Variables
        int menor;
        // Proceso
        menor = datos[0];
        for (int dato : datos) {
            if(menor > dato){
                menor = dato;
            }
        }
        // Reporte
        return menor;
    }
    
    
    public boolean sonIguales(int[] datos){
        // Variables
        boolean iguales;
        // Proceso
        boolean r1 = datos[0] == datos[1];
        boolean r2 = datos[2] == datos[3];
        iguales = r1 && r2;
        // Reporte
        return iguales;
    }
    
    
}
