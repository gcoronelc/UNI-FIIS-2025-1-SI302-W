package pe.edu.uni.semana01.prueba;

import pe.edu.uni.semana01.service.MateService;

public class PruebaMultiplicacion {
    public static void main(String [] args ){
         // Datos
        int n1 = 40;
        int n2 = 15;
        // Proceso
        MateService mateService = new MateService();
        int producto = mateService.multiplicar(n1, n2);
        // Reporte
        System.out.println("N1: " + n1);
        System.out.println("N2: " + n2);
        System.out.println("PRODUCTO: " + producto);
        
    }
    
 
 
}
