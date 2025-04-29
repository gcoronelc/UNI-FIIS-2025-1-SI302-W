package pe.edu.uni.educav2app.prueba;

import java.util.List;
import pe.edu.uni.educav2app.dto.ResumenDto;
import pe.edu.uni.educav2app.service.ConsultaService;

public class PruebaObtenerResumen {

	public static void main(String[] args) {
		// Variables
		List<ResumenDto> lista;
		// Proceso
		ConsultaService service = new ConsultaService();
		lista = service.obtenerResumen();
		// Reporte
		for (ResumenDto dto : lista) {
			System.out.println(dto.toString());
		}
	}
}
