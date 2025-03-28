
package pe.edu.uni.semana02.service;

import java.util.Arrays;
import java.util.Random;


public class Problema6Service {
    
    public int [] getDatos(){
        
        //Variables
        int [] datos;
        
        //Proceso
        Random random = new Random();
        datos = new int[3];
        for (int i = 0; i < datos.length; i++) {
            datos[i] = random.nextInt(50)+1;            
        }
        //Reporte
        return datos;
    }
    
    public int getMayor(int [] arreglo){
        //Variables
        int mayor;
        //Proceso
        Arrays.sort(arreglo);
        mayor = arreglo[2];
        //Reporte
        return mayor;
    }
    
    public int getMenor(int [] arreglo){
        //Variables
        int menor;
        //Proceso
        Arrays.sort(arreglo);
        menor = arreglo[0];
        //Reporte
        return menor;
    }
    
    public int getCentral(int [] arreglo){
        //Variables
        int central;
        //Proceso
        Arrays.sort(arreglo);
        central = arreglo[1];
        //Reporte
        return central;
    }
}

