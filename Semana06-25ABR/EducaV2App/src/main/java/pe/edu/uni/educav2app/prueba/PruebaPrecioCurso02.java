package pe.edu.uni.educav2app.prueba;

import pe.edu.uni.educav2app.service.ConsultaService;

public class PruebaPrecioCurso02 {

    public static void main(String[] args) {
        // Datos
        int cursoId = 20;
        // Proceso
        String mensaje = "Proceso ok.";
        try {
            ConsultaService service = new ConsultaService();
            double precio = service.obtenerPrecioCurso(cursoId);
            mensaje = "Precio: " + precio;
        } catch (Exception e) {
            mensaje = "ERROR: " + e.getMessage();
        }
        // Reporte
        System.out.println(mensaje);
    }
}
