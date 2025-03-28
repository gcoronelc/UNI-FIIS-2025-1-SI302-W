
package pe.edu.uni.semana02.prueba;

import java.util.Arrays;
import pe.edu.uni.semana02.service.Problema1Service;
import pe.edu.uni.semana02.service.Problema6Service;

public class PruebaProblema6 {
    public static void main(String[] args) {
        //Variables
        int [] arreglo;
        int mayor;
        int menor;
        int central;
        //Proceso
        Problema6Service service = new Problema6Service();
        arreglo = service.getDatos();
        mayor = service.getMayor(arreglo);
        menor = service.getMenor(arreglo);
        central = service.getCentral(arreglo);
        //Reporte
        System.out.println("Datos: " + Arrays.toString(arreglo));
        System.out.println("Mayor: " + mayor);
        System.out.println("Menor: " + menor);
        System.out.println("Central: " + central);
        
    }
    
}
