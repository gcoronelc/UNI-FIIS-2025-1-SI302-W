package pe.edu.uni.educav2app.prueba;

import pe.edu.uni.educav2app.service.ConsultaService;

public class PruebaPrecioCurso01 {
    
    public static void main(String[] args) {
        // Datos
        int cursoId = 2;
        // Proceso
        ConsultaService service = new ConsultaService();
        double precio = service.obtenerPrecioCurso(cursoId);
        // Reporte
        System.out.println("Precio: " + precio);
    }
}
