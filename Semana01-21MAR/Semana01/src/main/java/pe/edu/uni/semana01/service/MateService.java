package pe.edu.uni.semana01.service;

public class MateService {

    public int sumar(int n1, int n2){
        // Variables
        int suma;
        // Proceso
        suma = n1 + n2;
        // Reporte
        return suma;
    }
    
    public float dividir(int n1, int n2){
        // Variables
        float division;
        // Proceso
        division = (float)n1/n2;
        // Reporte
        return division;
    }
    
    public int restar(int n1, int n2){
        // Variables
        int resta;
        // Proceso
        resta = n1 - n2;
        // Reporte
        return resta;
    }
    
    public int multiplicar(int n1,int n2){
        //Variable
        int producto;
        //Procesos
        producto=n1*n2;
        //Reporte
        return producto;
        
    }
	
	public double potencia(double base, int exponente) {
        double resultado = 1;  // Empezamos con 1 porque cualquier número elevado a 0 es 1

        // Usamos un bucle for para multiplicar la base por sí misma 'exponente' veces
        for (int i = 0; i < Math.abs(exponente); i++) {
            resultado *= base;  // Multiplicamos la base por sí misma
        }

        // Si el exponente es negativo, tomamos el inverso del resultado
        if (exponente < 0) {
            resultado = 1 / resultado;
        }

        return resultado;
    }
    
}
