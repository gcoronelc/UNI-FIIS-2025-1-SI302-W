package pe.edu.uni.semana02.prueba;

import java.util.Arrays;
import pe.edu.uni.semana02.service.Problema07Service;

public class PruebaProblema07 {

    public static void main(String[] args) {
        //datos
        double[] notas = {15, 12, 14, 13, 19};
        //proceso
        Problema07Service service = new Problema07Service();
        double promedio1 = service.promedio1(notas);
        double menor = service.menor(notas);
        double promedio2 = service.promedio2(notas);
        //reporte
        System.out.println("Notas: " + Arrays.toString(notas));
        System.out.println("Promedio1: " + promedio1);
        System.out.println("menor: " + menor);
        System.out.println("promedio2: " + promedio2);
    }
}
