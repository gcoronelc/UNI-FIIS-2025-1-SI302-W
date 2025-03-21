package pe.edu.uni.semana01.prueba;

import pe.edu.uni.semana01.service.MateService;

public class PruebaPotencia02 {
    public static void main(String[] args) {
        //datos
        int base = 2;
        int exponente = -2;
        MateService mateService = new MateService();
        double potencia = mateService.potencia(base, exponente);
        System.out.println("la base es: " +base);
        System.out.println("el exponente es: "+exponente);
        System.out.println("la potencia es: "+potencia);
    }
}
