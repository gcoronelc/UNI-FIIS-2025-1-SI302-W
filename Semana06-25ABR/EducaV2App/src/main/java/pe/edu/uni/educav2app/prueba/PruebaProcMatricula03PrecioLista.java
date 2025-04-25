package pe.edu.uni.educav2app.prueba;

import pe.edu.uni.educav2app.dto.MatriculaDto;
import pe.edu.uni.educav2app.service.ProcesoService;

public class PruebaProcMatricula03PrecioLista {
    
    public static void main(String[] args) {
        // Datos
        MatriculaDto bean = new MatriculaDto();
        bean.setEmpleadoId(4);
        bean.setAlumnoId(1);
        bean.setCursoId(6);
        // Proceso
        ProcesoService service = new ProcesoService();
        bean = service.procMatricula(bean);
        // Reporte
        System.out.println("Estado: " + bean.getEstado());
        System.out.println("Mensaje: " + bean.getMensaje());
        System.out.println("Precio: " + bean.getPrecio());
    }
}
