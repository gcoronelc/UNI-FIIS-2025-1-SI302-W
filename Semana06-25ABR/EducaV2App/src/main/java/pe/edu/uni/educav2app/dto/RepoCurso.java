package pe.edu.uni.educav2app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronelc@gmail.com
 * @youtube www.youtube.com/DesarrollaSoftware
 * @facebook www.facebook.com/groups/desarrollasoftware/
 * @cursos gcoronelc.github.io
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepoCurso {
	
	private int codigo;
	private String nombre;
	private String tipo;
	private double precio;
	private int cuotas;
	private double abono;
	private double saldo;
	
}
