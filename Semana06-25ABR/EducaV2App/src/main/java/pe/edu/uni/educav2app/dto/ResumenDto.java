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
public class ResumenDto {
	
	private int codigo;
	private String nombre;
	private int matriculados;
	private double proyectado;
	private double real;

}
