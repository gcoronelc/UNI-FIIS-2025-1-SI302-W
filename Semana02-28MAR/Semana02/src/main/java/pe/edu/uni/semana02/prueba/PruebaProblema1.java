package pe.edu.uni.semana02.prueba;

import java.util.Arrays;
import pe.edu.uni.semana02.service.Problema1Service;

public class PruebaProblema1 {


    public static void main(String[] args) {
        // Variables
        int[] datos;
        int menor;
        boolean iguales;
        String repo;
        // Proceso
        Problema1Service service = new Problema1Service();
        datos = service.getDatos();
        menor = service.getMenor(datos);
        iguales = service.sonIguales(datos);
        repo = (iguales)?"Son iguales.":String.valueOf(menor);
        // Reporte
        System.out.println("Datos: " + Arrays.toString(datos));
        System.out.println("Menor: " + repo);
   }

    
}
