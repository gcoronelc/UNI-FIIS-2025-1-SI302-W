package pe.edu.uni.semana02.service;

import java.util.Arrays;

public class Problema07Service {
    public double promedio1(double[] notas){
        //variable
        double pr;
        //proceso
        pr=(notas[0]+notas[1]+notas[2]+notas[3])/4;
        //reporte
        return pr;
    }
    public double menor(double[] notas){
        //variable
        double me;
        //proceso
        double[] arreglo={notas[0],notas[1],notas[2],notas[3]};
        Arrays.sort(arreglo);
        me=arreglo[0];
        //reporte
        return me;
    }
    public double promedio2(double[] notas){
        //variable
        double pr2;
        //proceso
        double suma=0;
        for (double nota : notas) {
            suma+=nota;
        }
        suma-=this.menor(notas);
        pr2=suma/4;
        //reporte
        return pr2;
    }
}
