package pe.edu.uni.proyecto1coronel.prueba;

import java.util.Arrays;
import pe.edu.uni.proyecto1coronel.dto.DemoDto;
import pe.edu.uni.proyecto1coronel.service.DemoService;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronelc@gmail.com
 * @youtube www.youtube.com/DesarrollaSoftware
 * @facebook www.facebook.com/groups/desarrollasoftware/
 * @cursos gcoronelc.github.io
 */
public class Prueba01 {
	
	public static void main(String[] args) {
		// Datos
		DemoDto bean = new DemoDto(3,1);
		// Proceso
		DemoService demoService = new DemoService();
		bean = demoService.procesar(bean);
		// Reporte
		System.out.println("Arreglo Origen: " + Arrays.toString(bean.getVectorOrigen()));
		System.out.println("Resultado: " + bean.getResultado());
	}

}
