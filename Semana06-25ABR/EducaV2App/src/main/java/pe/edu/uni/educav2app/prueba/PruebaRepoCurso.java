package pe.edu.uni.educav2app.prueba;

import java.util.List;
import pe.edu.uni.educav2app.dto.RepoCurso;
import pe.edu.uni.educav2app.service.ConsultaService;

public class PruebaRepoCurso {

	public static void main(String[] args) {
		// Datos
		int codigo = 2;
		// Proceso
		ConsultaService service = new ConsultaService();
		List<RepoCurso> lista = service.obtenerRepoCurso(codigo);
		// Reporte
		for (RepoCurso dto : lista) {
			System.out.println(dto.toString());
		}
	}
}
